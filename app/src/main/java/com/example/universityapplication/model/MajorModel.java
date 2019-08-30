package com.example.universityapplication.model;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

@SuppressLint("ParcelCreator")
public class MajorModel implements Parcelable {

    @SerializedName("major_id")
    private int major_id;
    @SerializedName("major_namekh")
    private  String major_namekh;
    @SerializedName("major_latin")
    private  String major_latin;
    @SerializedName("major_price")
    private  float major_price;
    @SerializedName("use_id")
    private int use_id;


    public int getMajor_id() {
        return major_id;
    }

    public void setMajor_id(int major_id) {
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
        return "MajorModel{" +
                "major_id='" + major_id + '\'' +
                ", major_namekh='" + major_namekh + '\'' +
                ", major_latin='" + major_latin + '\'' +
                ", major_price=" + major_price +
                ", use_id=" + use_id +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}

