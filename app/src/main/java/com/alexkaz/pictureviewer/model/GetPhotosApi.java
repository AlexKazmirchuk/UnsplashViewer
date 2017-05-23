package com.alexkaz.pictureviewer.model;

import com.alexkaz.pictureviewer.model.entity.PhotoDetails;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetPhotosApi {

    @GET("photos")
    Call<List<PhotoDetails>> getPhotos();

    @GET("photos")
    Call<List<PhotoDetails>> getPhotos(@Query("page") String page,
                                       @Query("per_page") String perPage);

    @GET("photos")
    Call<List<PhotoDetails>> getPhotos(@Query("page") String page,
                                       @Query("per_page") String perPage,
                                       @Query("order_by") String orderBy);
}
