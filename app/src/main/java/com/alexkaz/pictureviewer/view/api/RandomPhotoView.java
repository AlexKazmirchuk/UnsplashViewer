package com.alexkaz.pictureviewer.view.api;

import com.alexkaz.pictureviewer.model.entity.PhotoDetails;

public interface RandomPhotoView extends View {
    void showRandomPhoto(PhotoDetails photo);
}
