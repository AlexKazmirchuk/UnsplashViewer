package com.alexkaz.pictureviewer.model;

import com.alexkaz.pictureviewer.model.entity.LikedPhotoDetails;
import retrofit2.Call;

public class LikePhotoServiceImpl extends BaseService implements LikePhotoService {

    private LikePhotoApi likePhotoApi;

    public LikePhotoServiceImpl() {
        likePhotoApi = getApi(LikePhotoApi.class);
    }

    @Override
    public Call<LikedPhotoDetails> likePhoto(String id) {
        return likePhotoApi.likePhoto(id);
    }

    @Override
    public Call<LikedPhotoDetails> unlikePhoto(String id) {
        return likePhotoApi.unlikePhoto(id);
    }
}
