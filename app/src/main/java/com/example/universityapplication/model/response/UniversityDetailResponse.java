package com.example.universityapplication.model.response;

import com.example.universityapplication.model.UniversityDetail;

import java.util.List;

public class UniversityDetailResponse {


    private List<UniversityDetail> universityDetails;

    public List<UniversityDetail> getUniversityDetails() {
        return universityDetails;
    }

    public void setUniversityDetails(List<UniversityDetail> universityDetails) {
        this.universityDetails = universityDetails;
    }

    @Override
    public String toString() {
        return "UniversityDetailResponse{" +
                "universityDetails=" + universityDetails +
                '}';
    }
}
