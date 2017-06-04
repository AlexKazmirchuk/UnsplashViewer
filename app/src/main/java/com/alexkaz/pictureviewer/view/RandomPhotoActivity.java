package com.alexkaz.pictureviewer.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.alexkaz.pictureviewer.R;
import com.alexkaz.pictureviewer.model.entity.PhotoDetails;
import com.alexkaz.pictureviewer.presenter.RandomPhotoPresenter;
import com.alexkaz.pictureviewer.presenter.RandomPhotoPresenterImpl;
import com.squareup.picasso.Picasso;

public class RandomPhotoActivity extends AppCompatActivity implements RandomPhotoView {

    private ImageView imageView;
    private RandomPhotoPresenter randomPhotoPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_photo);

        imageView = (ImageView) findViewById(R.id.randomPhoto);
        randomPhotoPresenter = new RandomPhotoPresenterImpl(this);
        loadPhoto();
    }

    private void loadPhoto(){
        randomPhotoPresenter.loadRandomPhoto();
    }

    @Override
    public void showRandomPhoto(PhotoDetails photo) {
        Picasso.with(this).load(photo.getUrls().getRegular()).into(imageView);
    }

    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }
}
