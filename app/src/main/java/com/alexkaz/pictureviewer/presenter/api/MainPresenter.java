package com.alexkaz.pictureviewer.presenter.api;

public interface MainPresenter {
    void makeLike(String photoID);
    void makeUnLike(String photoID);
    void loadPage(int page, int perPage);
    void loadPage(int page, int perPage, String orderBy);
}
