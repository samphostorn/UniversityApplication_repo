package com.example.universityapplication.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class UniversityDetail implements Parcelable {

    @SerializedName("major_latin")
    private String MajorName_Eng;
    @SerializedName("major_namekh")
    private String MajorName_Kh;
    @SerializedName("major_price")
    private String Ma_Price;
    @SerializedName("major_id")
    private int major_id;
    @SerializedName("univer_id")
    private int university_id;
    @SerializedName("img_folder_name")
    private  String img_folder_name;

    public UniversityDetail() {
    }

    protected UniversityDetail(Parcel in) {
        MajorName_Eng = in.readString();
        MajorName_Kh = in.readString();
        Ma_Price = in.readString();
        major_id=in.readInt();
        university_id=in.readInt();
        img_folder_name=in.readString();
    }

    public static final Creator<UniversityDetail> CREATOR = new Creator<UniversityDetail>() {
        @Override
        public UniversityDetail createFromParcel(Parcel in) {
            return new UniversityDetail(in);
        }

        @Override
        public UniversityDetail[] newArray(int size) {
            return new UniversityDetail[size];
        }
    };

    public String getMajorName_Eng() {
        return MajorName_Eng;
    }

    public void setMajorName_Eng(String majorName_Eng) {
        MajorName_Eng = majorName_Eng;
    }

    public String getMajorName_Kh() {
        return MajorName_Kh;
    }

    public void setMajorName_Kh(String majorName_Kh) {
        MajorName_Kh = majorName_Kh;
    }

    public String getMa_Price() {
        return Ma_Price;
    }

    public void setMa_Price(String ma_Price) {
        Ma_Price = ma_Price;
    }

    public int getMajor_id() {
        return major_id;
    }

    public void setMajor_id(int major_id) {
        this.major_id = major_id;
    }

    public int getUniversity_id() {
        return university_id;
    }

    public void setUniversity_id(int university_id) {
        this.university_id = university_id;
    }

    public String getImg_folder_name() {
        return img_folder_name;
    }

    public void setImg_folder_name(String img_folder_name) {
        this.img_folder_name = img_folder_name;
    }

    @Override
    public String toString() {
        return "UniversityDetail{" +
                "MajorName_Eng='" + MajorName_Eng + '\'' +
                ", MajorName_Kh='" + MajorName_Kh + '\'' +
                ", Ma_Price='" + Ma_Price + '\'' +
                ", major_id=" + major_id +
                ", university_id=" + university_id +
                ", img_folder_name='" + img_folder_name + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(MajorName_Eng);
        dest.writeString(MajorName_Kh);
        dest.writeString(Ma_Price);
        dest.writeInt(major_id);
        dest.writeInt(university_id);
        dest.writeString(img_folder_name);

    }
}
