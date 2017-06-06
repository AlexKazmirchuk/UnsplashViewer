package com.alexkaz.pictureviewer.view.impl;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alexkaz.pictureviewer.R;
import com.alexkaz.pictureviewer.model.entity.PhotoDetails;
import com.alexkaz.pictureviewer.presenter.api.RandomPhotoPresenter;
import com.alexkaz.pictureviewer.presenter.impl.RandomPhotoPresenterImpl;
import com.alexkaz.pictureviewer.utills.CircleTransform;
import com.alexkaz.pictureviewer.view.api.RandomPhotoView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.Locale;

public class RandomPhotoActivity extends BaseActivity implements RandomPhotoView,Callback {

    private ImageView userPhoto;
    private TextView userName;
    private TextView amountOfViews;
    private ImageView randomPhoto;
    private RandomPhotoPresenter randomPhotoPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_photo);

        userPhoto = (ImageView) findViewById(R.id.userPhotoImgView);
        userName = (TextView) findViewById(R.id.userNameTxtView);
        randomPhoto = (ImageView) findViewById(R.id.randomPhoto);
        amountOfViews = (TextView) findViewById(R.id.viewsAmountTxtView);

        randomPhotoPresenter = new RandomPhotoPresenterImpl(this);

        if (isOnline()){
            loadPhoto();
            showProgressBar();
        } else {
            hideLayoutWithRandomPhoto();
            showAlertMessage();
        }
    }

    private void loadPhoto(){
        randomPhotoPresenter.loadRandomPhoto();
    }

    @Override
    public void showRandomPhoto(PhotoDetails photo) {
        Picasso.with(this).load(photo.getUser().getProfileImage().getMedium()).transform(new CircleTransform()).into(userPhoto);
        userName.setText(photo.getUser().getName());
        Picasso.with(this).load(photo.getUrls().getRegular()).into(randomPhoto,this);
        amountOfViews.setText(String.format(Locale.getDefault(),"%d", photo.getViews()));
    }

    @Override
    public void onSuccess() {
        hideProgressBar();
    }

    @Override
    public void onError() {
        showErrorMessage("Some error happens");
        hideProgressBar();
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
                showLayoutWithRandomPhoto();
                hideAlertMessage();
                return true;
            }
        } else {
            showErrorMessage(getString(R.string.no_connection_message));
        }

        return super.onOptionsItemSelected(item);
    }

    private void showLayoutWithRandomPhoto(){
        findViewById(R.id.randomPhotoDetails).setVisibility(View.VISIBLE);
    }

    private void hideLayoutWithRandomPhoto(){
        findViewById(R.id.randomPhotoDetails).setVisibility(View.INVISIBLE);
    }
}
