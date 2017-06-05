package com.alexkaz.pictureviewer.model.services.impl;

import com.alexkaz.pictureviewer.model.api.GetPhotosApi;
import com.alexkaz.pictureviewer.model.entity.PhotoDetails;
import com.alexkaz.pictureviewer.model.services.api.GetPhotosService;

import java.util.List;
import retrofit2.Call;

public class GetPhotosServiceImpl extends BaseService implements GetPhotosService {

    private GetPhotosApi getPhotosApi;

    public GetPhotosServiceImpl() {
        getPhotosApi = getApi(GetPhotosApi.class);
    }

    @Override
    public Call<List<PhotoDetails>> getPhotos() {
        return getPhotosApi.getPhotos();
    }

    @Override
    public Call<List<PhotoDetails>> getPhotos(Integer page,Integer perPage) {
        return getPhotosApi.getPhotos(page,perPage);
    }

    @Override
    public Call<List<PhotoDetails>> getPhotos(Integer page, Integer perPage, String orderBy) {
        return getPhotosApi.getPhotos(page,perPage,orderBy);
    }
}
