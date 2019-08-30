package com.example.universityapplication.model.response;

import com.example.universityapplication.model.University;
import com.example.universityapplication.model.UniversityDetail;

import java.util.List;

public class Response {

private List<University> universities;


    public List<University> getUniversities() {
        return universities;
    }

    public void setUniversities(List<University> universities) {
        this.universities = universities;
    }

    @Override
    public String toString() {
        return "Response{" +
                "universities=" + universities +
                '}';
    }
//
//    private List<UniversityDetail> universityDetails;
//
//    public List<UniversityDetail> getUniversityDetails() {
//        return universityDetails;
//    }
//
//    public void setUniversityDetails(List<UniversityDetail> universityDetails) {
//        this.universityDetails = universityDetails;
//    }
}
