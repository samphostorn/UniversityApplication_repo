package com.example.universityapplication.model;

import com.google.gson.annotations.SerializedName;

public class UniversityImage {
    @SerializedName("img_folder_name")
    private  String img_folder_name;

    public String getImg_folder_name() {
        return img_folder_name;
    }

    public void setImg_folder_name(String img_folder_name) {
        this.img_folder_name = img_folder_name;
    }
}
