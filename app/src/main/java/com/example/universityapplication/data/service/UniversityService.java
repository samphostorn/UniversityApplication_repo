package com.example.universityapplication.data.service;

import com.example.universityapplication.model.Login;
import com.example.universityapplication.model.Major;
import com.example.universityapplication.model.MajorModel;
import com.example.universityapplication.model.University;
import com.example.universityapplication.model.UniversityDetail;
import com.example.universityapplication.model.UniversityRegister;
import com.example.universityapplication.model.UploadImageResponse;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface UniversityService {


    @GET("univer")
    Call<List<University>> getUniversityList();


    @POST("univer/InsertUniver")
    Call<University> create(@Body University university);

    @GET("univer/GetUniversityDetail")
    Call<List<UniversityDetail>> getUniversityDetail();

 /*   @GET("univer/GetUniversityDetailByID?id=id")
    Call<List<UniversityDetail>> getUniversityDetailByID(int id);*/
 @GET("univer/GetUniversityDetailByID")
 Call<List<UniversityDetail>> getUniversityDetailByID(@Query("id") int id);

    @GET("univer/GetUser")
    Call<List<Login>> getLogin();

    @POST("univer/RegUniver")
    Call<List<UniversityRegister>> InsertUniversity(@Body UniversityRegister universityRegister);

    @POST("univer/InsertMajor")
    Call<List<Major>> insertMajor(@Body Major major);

    @GET("Image/UniversityPhoto/img1.jpg")
    Call<List<University>> getImage();

    @POST("univer/DeleteMajor")
    Call<Integer> deleteMajor(@Query("id") int id);
    @POST("univer/updateMajor")
    Call<List<MajorModel>> UpdateMajor(@Body MajorModel majorModel);

    @Multipart
    @POST("univer/upload")
    Call<String> uploadImage(@Part MultipartBody.Part file);
//    @Multipart
//    @POST("univer/upload")
//    Call<ResponseBody> Upload(
//        @Part MultipartBody.Part photo,@Part("description") RequestBody description
//    );
}