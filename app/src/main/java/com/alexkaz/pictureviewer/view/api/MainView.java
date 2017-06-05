package com.alexkaz.pictureviewer.view.api;

import com.alexkaz.pictureviewer.model.entity.PhotoDetails;

import java.util.List;

public interface MainView extends View {
    void updateItem(PhotoDetails photoDetails);
    void showNextPage(List<PhotoDetails> photos);
}
