package com.example.universityapplication.fragement;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.universityapplication.R;
import com.example.universityapplication.adapter.UniversityAdapter;
import com.example.universityapplication.data.ServiceGenerator;
import com.example.universityapplication.data.service.UniversityService;
import com.example.universityapplication.model.University;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class RecyclerView_List_University_Fragment extends Fragment {


    UniversityService universityService;
    RecyclerView rvList;
    List<University> universities = new ArrayList<>();
    UniversityAdapter universityAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recyclerview_list_university,container,false);
        rvList = view.findViewById(R.id.rvListUniversity);
        universityService = ServiceGenerator.createService(UniversityService.class);
        Call<List<University>> call = universityService.getUniversityList();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        rvList = findViewById(R.id.rvListUniversity);
        rvList.setLayoutManager(new LinearLayoutManager(getContext()));

        universityAdapter = new UniversityAdapter(universities, getContext());
        rvList.setAdapter(universityAdapter);
        getUniversity();

    }
    private void getUniversity() {


        Call<List<University>> call = universityService.getUniversityList();
//        Toast.makeText(this, "getun" + universityService.getUniversityList(), Toast.LENGTH_SHORT).show();
        call.enqueue(new Callback<List<University>>() {
            @Override
            public void onResponse(Call<List<University>> call, retrofit2.Response<List<University>> response) {
                universities = response.body();
                universityAdapter.addMoreItems(universities);

            }

            @Override
            public void onFailure(Call<List<University>> call, Throwable t) {
               // Log.e(TAG, "University: " + t.toString());
            }
        });
    }

    }
