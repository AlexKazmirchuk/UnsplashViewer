package com.alexkaz.pictureviewer.model.services.impl;

import com.alexkaz.pictureviewer.model.api.RandomPhotoApi;
import com.alexkaz.pictureviewer.model.entity.PhotoDetails;
import com.alexkaz.pictureviewer.model.services.api.RandomPhotoService;

import retrofit2.Call;

public class RandomPhotoServiceImpl extends BaseService implements RandomPhotoService {

    private RandomPhotoApi randomPhotoApi;

    public RandomPhotoServiceImpl() {
        randomPhotoApi = getApi(RandomPhotoApi.class);
    }

    @Override
    public Call<PhotoDetails> getRandomPhoto() {
        return randomPhotoApi.getRandomPhoto();
    }
}
