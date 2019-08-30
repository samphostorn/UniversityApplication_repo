package com.example.universityapplication.model;

import com.google.gson.annotations.SerializedName;

public class UniversityRegister {

    @SerializedName("univer_nameLatin")
    private String univer_nameLatin;
    @SerializedName("univer_namekh")
    private  String univer_namekh;

    @SerializedName("univer_email")
    private String univer_email;
    @SerializedName("univer_phone")
    private String univer_phone;
    @SerializedName("img_folder_name")
    private String img_folder_name;

    @SerializedName("use_name")
    private String use_name;
    @SerializedName("use_password")
    private String use_password;
    @SerializedName("link")
    private String link;


    public String getUniver_nameLatin() {
        return univer_nameLatin;
    }

    public void setUniver_nameLatin(String univer_nameLatin) {
        this.univer_nameLatin = univer_nameLatin;
    }

    public String getUniver_namekh() {
        return univer_namekh;
    }

    public void setUniver_namekh(String univer_namekh) {
        this.univer_namekh = univer_namekh;
    }

    public String getUniver_email() {
        return univer_email;
    }

    public void setUniver_email(String univer_email) {
        this.univer_email = univer_email;
    }

    public String getUniver_phone() {
        return univer_phone;
    }

    public void setUniver_phone(String univer_phone) {
        this.univer_phone = univer_phone;
    }

    public String getImg_folder_name() {
        return img_folder_name;
    }

    public void setImg_folder_name(String img_folder_name) {
        this.img_folder_name = img_folder_name;
    }

    public String getUse_name() {
        return use_name;
    }

    public void setUse_name(String use_name) {
        this.use_name = use_name;
    }

    public String getUse_password() {
        return use_password;
    }

    public void setUse_password(String use_password) {
        this.use_password = use_password;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "UniversityRegister{" +
                "univer_nameLatin='" + univer_nameLatin + '\'' +
                ", univer_namekh='" + univer_namekh + '\'' +
                ", univer_email='" + univer_email + '\'' +
                ", univer_phone='" + univer_phone + '\'' +
                ", img_folder_name='" + img_folder_name + '\'' +
                ", use_name='" + use_name + '\'' +
                ", use_password='" + use_password + '\'' +
                ", link='" + link + '\'' +
                '}';
    }
}
