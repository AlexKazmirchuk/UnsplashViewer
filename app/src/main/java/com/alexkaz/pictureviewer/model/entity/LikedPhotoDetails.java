package com.alexkaz.pictureviewer.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LikedPhotoDetails {

    @SerializedName("photo")
    @Expose
    private PhotoDetails photo;

    @SerializedName("user")
    @Expose
    private User user;

    public PhotoDetails getPhotoDetails() {
        return photo;
    }

    public void setPhotoDetails(PhotoDetails photo) {
        this.photo = photo;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
