package com.alexkaz.pictureviewer.model;

import com.alexkaz.pictureviewer.model.entity.PhotoDetails;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RandomPhotoApi {

    @GET("photos/random")
    Call<PhotoDetails> getRandomPhoto();
}
