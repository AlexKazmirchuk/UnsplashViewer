package com.alexkaz.pictureviewer.model;

import com.alexkaz.pictureviewer.model.entity.LikedPhotoDetails;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.POST;
import retrofit2.http.Path;

interface LikePhotoApi {

    @POST("photos/{id}/like")
    Call<LikedPhotoDetails> likePhoto(@Path("id") String id);

    @DELETE("photos/{id}/like")
    Call<LikedPhotoDetails> unlikePhoto(@Path("id") String id);

}
