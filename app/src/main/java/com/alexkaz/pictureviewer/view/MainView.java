package com.alexkaz.pictureviewer.view;

import java.util.List;

public interface MainView {
    void showPhotos(List<String> photos);
    void showErrorMessage(String message);
}
