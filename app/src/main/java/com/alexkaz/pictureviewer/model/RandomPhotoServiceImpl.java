package com.alexkaz.pictureviewer.model;

import com.alexkaz.pictureviewer.model.entity.PhotoDetails;
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
