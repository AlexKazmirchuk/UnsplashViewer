package com.alexkaz.pictureviewer.presenter;

import android.util.Log;

import com.alexkaz.pictureviewer.model.GetPhotosService;
import com.alexkaz.pictureviewer.model.GetPhotosServiceImpl;
import com.alexkaz.pictureviewer.model.LikePhotoService;
import com.alexkaz.pictureviewer.model.LikePhotoServiceImpl;
import com.alexkaz.pictureviewer.model.entity.LikedPhotoDetails;
import com.alexkaz.pictureviewer.model.entity.PhotoDetails;
import com.alexkaz.pictureviewer.utills.Constants;
import com.alexkaz.pictureviewer.view.MainView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenterImpl implements MainPresenter {

    private MainView mainView;
    private GetPhotosService getPhotosService;
    private LikePhotoService likePhotoService;

    public MainPresenterImpl(MainView mainView) {
        this.mainView = mainView;
        getPhotosService = new GetPhotosServiceImpl();
        likePhotoService = new LikePhotoServiceImpl();
    }

    @Override
    public void makeLike(String photoID) {
        Call<LikedPhotoDetails> likedPhotoCall = likePhotoService.likePhoto(photoID);
        likedPhotoCall.enqueue(getUpdateItemCallback());
    }

    @Override
    public void makeUnLike(String photoID) {
        Call<LikedPhotoDetails> unLikedPhotoCall = likePhotoService.unlikePhoto(photoID);
        unLikedPhotoCall.enqueue(getUpdateItemCallback());
    }

    private Callback<LikedPhotoDetails> getUpdateItemCallback(){
        return new Callback<LikedPhotoDetails>() {
            @Override
            public void onResponse(Call<LikedPhotoDetails> call, Response<LikedPhotoDetails> response) {
                if (response.isSuccessful()){
                    mainView.updateItem(response.body().getPhotoDetails());
                } else {
                    mainView.showErrorMessage(response.message());
                }
            }

            @Override
            public void onFailure(Call<LikedPhotoDetails> call, Throwable t) {
                mainView.showErrorMessage(t.getMessage());
            }
        };
    }

    @Override
    public void loadPage(int page, int perPage) {
        loadPage(page,perPage, Constants.LATEST);
    }

    @Override
    public void loadPage(int page, int perPage, String orderBy) {
        Call<List<PhotoDetails>> photos = getPhotosService.getPhotos(page, perPage, orderBy);
        photos.enqueue(new Callback<List<PhotoDetails>>() {
            @Override
            public void onResponse(Call<List<PhotoDetails>> call, Response<List<PhotoDetails>> response) {
                if (response.isSuccessful()){
                    mainView.showNextPage(response.body());
                } else {
                    mainView.showErrorMessage(response.message());
                }
            }

            @Override
            public void onFailure(Call<List<PhotoDetails>> call, Throwable t) {
                mainView.showErrorMessage(t.getMessage());
            }
        });
    }
}
