package com.alexkaz.pictureviewer.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.alexkaz.pictureviewer.R;
import com.alexkaz.pictureviewer.model.entity.PhotoDetails;
import com.alexkaz.pictureviewer.presenter.MainPresenter;
import com.alexkaz.pictureviewer.presenter.MainPresenterImpl;
import com.alexkaz.pictureviewer.utills.Constants;
import com.alexkaz.pictureviewer.view.adapters.RecyclerAdapter;
import com.paginate.Paginate;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainView {

    public static final int AUTH_ACTIVITY_REQ_CODE = 1;

    private RecyclerView mRecyclerView;
    private RecyclerAdapter recyclerAdapter;
    private MainPresenter mainPresenter;

    private int currentPage = 1;
    private int pageSize = 10;
    private int forUpdateItemPosition;
    private boolean loadingInProgress;
    private String orderBy = Constants.LATEST;
    private int checkedRadioButtonId = R.id.orderByNew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setElevation(0);
        checkAuthorization();
        mainPresenter = new MainPresenterImpl(this);
        configureRecycleView();
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
                // todo call presenter method for make like
                forUpdateItemPosition = position;
                mainPresenter.makeLike(photoId);
            }

            @Override
            public void unLikePhoto(String photoId, int position) {
                // todo call presenter method for make like
                forUpdateItemPosition = position;
                mainPresenter.makeUnLike(photoId);
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
                .addLoadingListItem(true)
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
    public void showErrorMessage(String message) {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }

    @Override
    public void updateItem(PhotoDetails photoDetails) {
        recyclerAdapter.getPhotos().get(forUpdateItemPosition).setLikes(photoDetails.getLikes());
        recyclerAdapter.getPhotos().get(forUpdateItemPosition).setLikedByUser(photoDetails.isLikedByUser());
        recyclerAdapter.notifyDataSetChanged();
    }

    private void loadNextPage(){
        if (isOnline()){
            mainPresenter.loadPage(currentPage,pageSize,orderBy);
            currentPage++;
            loadingInProgress = true;
            enableRadioButtons(false);
        } else {
            showErrorMessage("No internet!");
        }
    }

    @Override
    public void showNextPage(List<PhotoDetails> photos) {
        recyclerAdapter.getPhotos().addAll(photos);
        recyclerAdapter.notifyDataSetChanged();
        loadingInProgress = false;
        enableRadioButtons(true);
    }

    public void changeListOrder(View v){
        if (v.getId() != checkedRadioButtonId){
            switch (v.getId()){
                case R.id.orderByNew:
                    Toast.makeText(this,"New clicked",Toast.LENGTH_SHORT).show();
                    orderBy = Constants.LATEST;
                    break;
                case R.id.orderByOld:
                    Toast.makeText(this,"Old clicked",Toast.LENGTH_SHORT).show();
                    orderBy = Constants.OLDEST;
                    break;
                case R.id.orderByPopular:
                    Toast.makeText(this,"Popular clicked",Toast.LENGTH_SHORT).show();
                    orderBy = Constants.POPULAR;
                    break;
            }
            loadStartPage();
            checkedRadioButtonId = v.getId();
        }
    }

    private void loadStartPage(){
        currentPage = 1;
        loadingInProgress = true;
        recyclerAdapter.setPhotos(new ArrayList<PhotoDetails>());
        recyclerAdapter.notifyDataSetChanged();
        loadNextPage();
    }

    private void enableRadioButtons(boolean enabled){
        findViewById(R.id.orderByNew).setEnabled(enabled);
        findViewById(R.id.orderByOld).setEnabled(enabled);
        findViewById(R.id.orderByPopular).setEnabled(enabled);
    }

    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_refresh){
            loadStartPage();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }

    }
}
