package com.alexkaz.pictureviewer.presenter;

import com.alexkaz.pictureviewer.model.GetPhotosService;
import com.alexkaz.pictureviewer.model.GetPhotosServiceImpl;
import com.alexkaz.pictureviewer.model.LikePhotoService;
import com.alexkaz.pictureviewer.model.LikePhotoServiceImpl;
import com.alexkaz.pictureviewer.model.entity.LikedPhotoDetails;
import com.alexkaz.pictureviewer.model.entity.PhotoDetails;
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
        likedPhotoCall.enqueue(new Callback<LikedPhotoDetails>() {
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
        });
    }

    @Override
    public void makeUnLike(String photoID) {
        Call<LikedPhotoDetails> unLikedPhotoCall = likePhotoService.unlikePhoto(photoID);
        unLikedPhotoCall.enqueue(new Callback<LikedPhotoDetails>() {
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
        });
    }

    @Override
    public void refreshPhotoList() {

    }

    @Override
    public void getPhotoList() {
        Call<List<PhotoDetails>> photoDetailsCall = getPhotosService.getPhotos();
        photoDetailsCall.enqueue(new Callback<List<PhotoDetails>>() {
            @Override
            public void onResponse(Call<List<PhotoDetails>> call, Response<List<PhotoDetails>> response) {
                if (response.isSuccessful()){
                    List<PhotoDetails> photos = response.body();
                    mainView.showPhotos(photos);
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
