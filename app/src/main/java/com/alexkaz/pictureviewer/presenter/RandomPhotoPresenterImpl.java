package com.alexkaz.pictureviewer.presenter;

import com.alexkaz.pictureviewer.model.RandomPhotoService;
import com.alexkaz.pictureviewer.model.RandomPhotoServiceImpl;
import com.alexkaz.pictureviewer.model.entity.PhotoDetails;
import com.alexkaz.pictureviewer.view.RandomPhotoView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RandomPhotoPresenterImpl implements RandomPhotoPresenter {

    private RandomPhotoService randomPhotoService;
    private RandomPhotoView randomPhotoView;

    public RandomPhotoPresenterImpl(RandomPhotoView view) {
        randomPhotoView = view;
        randomPhotoService = new RandomPhotoServiceImpl();
    }

    @Override
    public void loadRandomPhoto() {
        Call<PhotoDetails> randomPhoto = randomPhotoService.getRandomPhoto();
        randomPhoto.enqueue(new Callback<PhotoDetails>() {
            @Override
            public void onResponse(Call<PhotoDetails> call, Response<PhotoDetails> response) {
                if (response.isSuccessful()){
                    randomPhotoView.showRandomPhoto(response.body());
                } else {
                    randomPhotoView.showErrorMessage(response.message());
                }
            }

            @Override
            public void onFailure(Call<PhotoDetails> call, Throwable t) {
                randomPhotoView.showErrorMessage(t.getMessage());
            }
        });
    }
}
