package com.alexkaz.pictureviewer.model;

import com.alexkaz.pictureviewer.model.entity.PhotoDetails;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface LikePhotoApi {

    @POST("photos/{id}/like")
    Call<PhotoDetails> likePhoto(@Path("id") String id);

    @DELETE("photos/{id}/like")
    Call<PhotoDetails> unlikePhoto(@Path("id") String id);

}
