package com.alexkaz.pictureviewer.presenter;

import com.alexkaz.pictureviewer.view.MainView;

public class MainPresenterImpl implements MainPresenter {

    private MainView mainView;

    public MainPresenterImpl(MainView mainView) {
        this.mainView = mainView;
    }

    @Override
    public void makeLike(String photoID) {

    }

    @Override
    public void refreshPhotoList() {

    }

    @Override
    public void getPhotoList() {

    }
}
