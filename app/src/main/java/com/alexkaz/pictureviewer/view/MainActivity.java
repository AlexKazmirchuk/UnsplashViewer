package com.alexkaz.pictureviewer.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.alexkaz.pictureviewer.R;
import com.alexkaz.pictureviewer.model.entity.PhotoDetails;
import com.alexkaz.pictureviewer.presenter.MainPresenter;
import com.alexkaz.pictureviewer.presenter.MainPresenterImpl;
import com.alexkaz.pictureviewer.utills.Constants;
import com.alexkaz.pictureviewer.view.adapters.RecyclerAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainView {

    public static final int AUTH_ACTIVITY_REQ_CODE = 1;

    private RecyclerView mRecyclerView;
    private RecyclerAdapter recyclerAdapter;
    private MainPresenter mainPresenter;

    private int forUpdateItemPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkAuthorization();
        configureRecycleView();
        mainPresenter = new MainPresenterImpl(this);
        mainPresenter.getPhotoList();
    }

    private void checkAuthorization(){
        SharedPreferences preferences = getSharedPreferences(Constants.APP_PREFS,MODE_PRIVATE);
        boolean isAuthenticated = preferences.getBoolean(Constants.AUTHENTICATED,false);
        if (!isAuthenticated){
            // todo call AuthActivity for result maybe
            Intent authIntent  = new Intent(this, AuthActivity.class);
            startActivity(authIntent);
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
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == AUTH_ACTIVITY_REQ_CODE){
            //TextView textView = (TextView) findViewById(R.id.mainTextView);
            if (resultCode == RESULT_OK){
               // textView.setTextSize(20);
                //textView.setText("You are authenticted succesfull!! " + getSharedPreferences(Constants.APP_PREFS,MODE_PRIVATE).getBoolean(Constants.AUTHENTICATED,false));
            } else {
                //textView.setTextSize(20);
                //textView.setText("Something it's wrong, try again");
            }

        } else {
            // todo make finish() temporary
        }
    }

    @Override
    public void showPhotos(List<PhotoDetails> photos) {
        recyclerAdapter.setPhotos(photos);
        recyclerAdapter.notifyDataSetChanged();
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
}
