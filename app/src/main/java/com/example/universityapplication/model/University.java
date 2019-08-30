package com.example.universityapplication.model;

import com.google.gson.annotations.SerializedName;

public class University {
    @SerializedName("univer_id")
    private int univer_id;
    @SerializedName("university_name")
    private  String un_namekh;
    @SerializedName("university_latin")
   private  String un_name_latin;
    @SerializedName("img_folder_name")
    private String img_folder_name;
    @SerializedName("university_link")
    private String university_link;

    public int getUniver_id() {
        return univer_id;
    }

    public void setUniver_id(int univer_id) {
        this.univer_id = univer_id;
    }

    public String getImg_folder_name() {
        return img_folder_name;
    }

    public void setImg_folder_name(String img_folder_name) {
        this.img_folder_name = img_folder_name;
    }

    public String getUn_namekh() {
        return un_namekh;
    }

    public void setUn_namekh(String un_namekh) {
        this.un_namekh = un_namekh;
    }

    public String getUn_name_latin() {
        return un_name_latin;
    }

    public void setUn_name_latin(String un_name_latin) {
        this.un_name_latin = un_name_latin;
    }

    public String getUniversity_link() {
        return university_link;
    }

    public void setUniversity_link(String university_link) {
        this.university_link = university_link;
    }

    @Override
    public String toString() {
        return "University{" +
                "univer_id=" + univer_id +
                ", un_namekh='" + un_namekh + '\'' +
                ", un_name_latin='" + un_name_latin + '\'' +
                ", img_folder_name='" + img_folder_name + '\'' +
                ", university_link='" + university_link + '\'' +
                '}';
    }
}
