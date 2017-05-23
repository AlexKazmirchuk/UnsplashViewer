package com.alexkaz.pictureviewer.model;

import com.alexkaz.pictureviewer.model.entity.PhotoDetails;

import retrofit2.Call;

public class LikePhotoServiceImpl extends BaseService implements LikePhotoService {

    private LikePhotoApi likePhotoApi;

    public LikePhotoServiceImpl() {
        likePhotoApi = getApi(LikePhotoApi.class);
    }

    @Override
    public Call<PhotoDetails> likePhoto(String id) {
        return likePhotoApi.likePhoto(id);
    }

    @Override
    public Call<PhotoDetails> unlikePhoto(String id) {
        return likePhotoApi.unlikePhoto(id);
    }
}
