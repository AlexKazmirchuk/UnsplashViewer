package com.alexkaz.pictureviewer.model;

import com.alexkaz.pictureviewer.model.entity.PhotoDetails;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

interface GetPhotosApi {

    @GET("photos")
    Call<List<PhotoDetails>> getPhotos();

    @GET("photos")
    Call<List<PhotoDetails>> getPhotos(@Query("page") Integer page,
                                       @Query("per_page") Integer perPage);

    @GET("photos")
    Call<List<PhotoDetails>> getPhotos(@Query("page") Integer page,
                                       @Query("per_page") Integer perPage,
                                       @Query("order_by") String orderBy);
}
