package com.example.universityapplication.model;

import com.google.gson.annotations.SerializedName;

public class Login {


    @SerializedName("use_name")
    private String UserName;
    @SerializedName("use_password")
    private String UserPassword;
    @SerializedName("use_id")
    private  String UseId;

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUserPassword() {
        return UserPassword;
    }

    public void setUserPassword(String userPassword) {
        UserPassword = userPassword;
    }

    public String getUseId() {
        return UseId;
    }

    public void setUseId(String useId) {
        UseId = useId;
    }

    @Override
    public String toString() {
        return "Login{" +
                "UserName='" + UserName + '\'' +
                ", UserPassword='" + UserPassword + '\'' +
                ", UseId='" + UseId + '\'' +
                '}';
    }
}
