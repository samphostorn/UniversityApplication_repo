package com.example.universityapplication.fragement;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.universityapplication.R;
import com.example.universityapplication.data.ServiceGenerator;
import com.example.universityapplication.data.service.UniversityService;
import com.example.universityapplication.model.University;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UniversityAddFragment extends Fragment {


    UniversityService universityService;
    EditText edUniver_namelatin,edUniver_namekh;
    Button btnSave;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_add_university,container,false);
        edUniver_namelatin=view.findViewById(R.id.edNameLatin);
        edUniver_namekh=view.findViewById(R.id.edUniversity_namekh);
        btnSave=view.findViewById(R.id.btnAdd);
        universityService = ServiceGenerator.createService(UniversityService.class);
       // Call<List<University>> call = universityService.getUniversityList();
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnSave.setOnClickListener(v->{
            University university=new University();
                university.setUn_namekh(edUniver_namekh.getText().toString());
                university.setUn_name_latin(edUniver_namelatin.getText().toString());
                universityService.create(university)
                        .enqueue(new Callback<University>() {
                            @Override
                            public void onResponse(Call<University> call, Response<University> response) {
                               // Toast.makeText(UniversityAddFragment, "created", Toast.LENGTH_SHORT).show();
                                 //   Toast.makeText(UniversityAddFragment.this,"University Created",Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onFailure(Call<University> call, Throwable t) {

                            }
                        });
        });
    }
}

