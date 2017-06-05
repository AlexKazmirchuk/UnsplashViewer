package com.alexkaz.pictureviewer.view.impl;

import android.os.Bundle;
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
}
