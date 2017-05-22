package com.alexkaz.pictureviewer.view;

import com.alexkaz.pictureviewer.model.entity.PhotoDetails;

import java.util.List;

public interface MainView {
    void showPhotos(List<PhotoDetails> photos);
    void showErrorMessage(String message);
}
