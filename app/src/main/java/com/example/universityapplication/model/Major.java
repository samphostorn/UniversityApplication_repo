package com.example.universityapplication.model;

import com.google.gson.annotations.SerializedName;

public class Major {

    @SerializedName("major_id")
    private String major_id;
    @SerializedName("major_namekh")
    private  String major_namekh;
    @SerializedName("major_latin")
    private  String major_latin;
    @SerializedName("university_id")
    private int Univer_id;
    @SerializedName("major_price")
    private  float major_price;
    @SerializedName("use_id")
    private int use_id;

    public String getMajor_id() {
        return major_id;
    }

    public void setMajor_id(String major_id) {
        this.major_id = major_id;
    }

    public String getMajor_namekh() {
        return major_namekh;
    }

    public void setMajor_namekh(String major_namekh) {
        this.major_namekh = major_namekh;
    }

    public String getMajor_latin() {
        return major_latin;
    }

    public void setMajor_latin(String major_latin) {
        this.major_latin = major_latin;
    }

    public int getUniver_id() {
        return Univer_id;
    }

    public void setUniver_id(int univer_id) {
        Univer_id = univer_id;
    }

    public float getMajor_price() {
        return major_price;
    }

    public void setMajor_price(float major_price) {
        this.major_price = major_price;
    }

    public int getUse_id() {
        return use_id;
    }

    public void setUse_id(int use_id) {
        this.use_id = use_id;
    }

    @Override
    public String toString() {
        return "Major{" +
                "major_id='" + major_id + '\'' +
                ", major_namekh='" + major_namekh + '\'' +
                ", major_latin='" + major_latin + '\'' +
                ", Univer_id='" + Univer_id + '\'' +
                ", major_price=" + major_price +
                ", use_id=" + use_id +
                '}';
    }
}
