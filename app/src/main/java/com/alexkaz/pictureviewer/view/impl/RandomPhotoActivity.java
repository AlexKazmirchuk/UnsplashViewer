package com.alexkaz.pictureviewer.view.impl;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.alexkaz.pictureviewer.R;
import com.alexkaz.pictureviewer.model.entity.PhotoDetails;
import com.alexkaz.pictureviewer.presenter.api.RandomPhotoPresenter;
import com.alexkaz.pictureviewer.presenter.impl.RandomPhotoPresenterImpl;
import com.alexkaz.pictureviewer.view.api.RandomPhotoView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class RandomPhotoActivity extends BaseActivity implements RandomPhotoView,Callback {

    private ImageView imageView;
    private RandomPhotoPresenter randomPhotoPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_photo);

        imageView = (ImageView) findViewById(R.id.randomPhoto);
        randomPhotoPresenter = new RandomPhotoPresenterImpl(this);
        loadPhoto();
        showProgressBar();
    }

    private void loadPhoto(){
        randomPhotoPresenter.loadRandomPhoto();
    }

    @Override
    public void showRandomPhoto(PhotoDetails photo) {
        Picasso.with(this).load(photo.getUrls().getRegular()).into(imageView,this);
    }

    @Override
    public void onSuccess() {
        hideProgressBar();
    }

    @Override
    public void onError() {
        showErrorMessage("Some error happens");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.random_photo_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (isOnline()){
            if (item.getItemId() == R.id.action_next_photo){
                loadPhoto();
                showProgressBar();
                return true;
            }
        } else {
            showErrorMessage(getString(R.string.no_connection_message));
        }

        return super.onOptionsItemSelected(item);
    }
}
