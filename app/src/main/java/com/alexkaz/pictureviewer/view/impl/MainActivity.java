package com.alexkaz.pictureviewer.view.impl;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.alexkaz.pictureviewer.R;
import com.alexkaz.pictureviewer.model.entity.PhotoDetails;
import com.alexkaz.pictureviewer.presenter.api.MainPresenter;
import com.alexkaz.pictureviewer.presenter.impl.MainPresenterImpl;
import com.alexkaz.pictureviewer.utills.Constants;
import com.alexkaz.pictureviewer.view.adapters.RecyclerAdapter;
import com.alexkaz.pictureviewer.view.api.MainView;
import com.paginate.Paginate;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends WithRefreshIconAnimActivity implements MainView {

    public static final int AUTH_ACTIVITY_REQ_CODE = 1;
    private static final int PAGE_SIZE = 10;
    public static final int START_PAGE = 1;

    private RecyclerView mRecyclerView;
    private RecyclerAdapter recyclerAdapter;

    private MainPresenter mainPresenter;
    private int currentPage = START_PAGE;
    private int forUpdateItemPosition;
    private boolean loadingInProgress;
    private String orderBy = Constants.LATEST;
    private int checkedRadioButtonId = R.id.orderByNew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkAuthorization();
        mainPresenter = new MainPresenterImpl(this);
        configureRecycleView();
        showProgressBar();
        if (!isOnline()){
            showAlertMessage();
            hideProgressBar();
        }
    }

    private void checkAuthorization(){
        SharedPreferences preferences = getSharedPreferences(Constants.APP_PREFS,MODE_PRIVATE);
        boolean isAuthenticated = preferences.getBoolean(Constants.AUTHENTICATED,false);
        if (!isAuthenticated){
            Intent authIntent  = new Intent(this, AuthActivity.class);
            startActivityForResult(authIntent, AUTH_ACTIVITY_REQ_CODE);
        }
    }

    private void configureRecycleView(){
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerAdapter = new RecyclerAdapter() {
            @Override
            public void likePhoto(String photoId, int position) {
                if (isOnline()){
                    forUpdateItemPosition = position;
                    mainPresenter.makeLike(photoId);
                } else {
                    showErrorMessage(getString(R.string.no_connection_message));
                }
            }

            @Override
            public void unLikePhoto(String photoId, int position) {
                if (isOnline()){
                    forUpdateItemPosition = position;
                    mainPresenter.makeUnLike(photoId);
                } else {
                    showErrorMessage(getString(R.string.no_connection_message));
                }
            }
        };
        mRecyclerView.setAdapter(recyclerAdapter);

        Paginate.Callbacks callbacks = new Paginate.Callbacks() {
            @Override
            public void onLoadMore() {
                loadNextPage();
            }

            @Override
            public boolean isLoading() {
                return loadingInProgress;
            }

            @Override
            public boolean hasLoadedAllItems() {
                return false;
            }
        };

        Paginate.with(mRecyclerView,callbacks)
                .setLoadingTriggerThreshold(2)
                .addLoadingListItem(false)
                .build();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == AUTH_ACTIVITY_REQ_CODE){
            if (resultCode == RESULT_OK){
                loadStartPage();
            } else {
                finish();
            }
        }
    }

    @Override
    public void updateItem(PhotoDetails photoDetails) {
        recyclerAdapter.getPhotos().get(forUpdateItemPosition).setLikes(photoDetails.getLikes());
        recyclerAdapter.getPhotos().get(forUpdateItemPosition).setLikedByUser(photoDetails.isLikedByUser());
        recyclerAdapter.notifyDataSetChanged();
    }

    @Override
    public void showNextPage(List<PhotoDetails> photos) {
        recyclerAdapter.getPhotos().addAll(photos);
        recyclerAdapter.notifyDataSetChanged();
        loadingInProgress = false;
        enableRadioButtons(true);
        hideProgressBar();
    }

    public void changeListOrder(View v){
        if (v.getId() != checkedRadioButtonId){
            switch (v.getId()){
                case R.id.orderByNew:
                    orderBy = Constants.LATEST;
                    break;
                case R.id.orderByOld:
                    orderBy = Constants.OLDEST;
                    break;
                case R.id.orderByPopular:
                    orderBy = Constants.POPULAR;
                    break;
            }
            checkedRadioButtonId = v.getId();
            if (isOnline()){
                loadStartPage();
            } else {
                showErrorMessage(getString(R.string.no_connection_message));
            }
        }
    }

    private void loadStartPage(){
        currentPage = 1;
        loadingInProgress = true;
        recyclerAdapter.setPhotos(new ArrayList<PhotoDetails>());
        recyclerAdapter.notifyDataSetChanged();
        loadNextPage();
    }

    private void loadNextPage(){
        if (isOnline()){
            mainPresenter.loadPage(currentPage, PAGE_SIZE,orderBy);
            currentPage++;
            enableRadioButtons(false);
            showProgressBar();
            hideAlertMessage();
        } else {
            showErrorMessage(getString(R.string.no_connection_message));
        }
        loadingInProgress = true;
    }

    private void enableRadioButtons(boolean enabled){
        findViewById(R.id.orderByNew).setEnabled(enabled);
        findViewById(R.id.orderByOld).setEnabled(enabled);
        findViewById(R.id.orderByPopular).setEnabled(enabled);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (isOnline()){
            if (item.getItemId() == R.id.action_refresh){
                addAnimToActionRefreshItem(item);
                loadStartPage();
            }
            if (item.getItemId() == R.id.action_random_photo){
                startActivity(new Intent(this,RandomPhotoActivity.class));
            }
            return true;
        } else {
            showErrorMessage(getString(R.string.no_connection_message));
        }
        return super.onOptionsItemSelected(item);
    }
}
