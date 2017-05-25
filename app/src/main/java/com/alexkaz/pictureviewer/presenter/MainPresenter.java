package com.alexkaz.pictureviewer.presenter;

public interface MainPresenter {
    void makeLike(String photoID);
    void makeUnLike(String photoID);
    void refreshPhotoList();
    void getPhotoList();
    void loadPage(int page, int perPage);
}
