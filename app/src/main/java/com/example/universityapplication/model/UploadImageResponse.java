package com.example.universityapplication.model;

import com.google.gson.annotations.SerializedName;

public class UploadImageResponse {
   @SerializedName("photo")
    private String photo;

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
