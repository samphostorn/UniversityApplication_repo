package com.example.universityapplication.fragement;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.universityapplication.MajorDetailActivity;
import com.example.universityapplication.R;
import com.example.universityapplication.adapter.MajorDetailAdapter;
import com.example.universityapplication.adapter.UniversityDetailAdapter;
import com.example.universityapplication.data.ServiceGenerator;
import com.example.universityapplication.data.service.UniversityService;
import com.example.universityapplication.model.UniversityDetail;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MajorDetailFragment extends Fragment {
    UniversityService universityService;
    RecyclerView rvList;
    List<UniversityDetail> universities=new ArrayList<>();
    MajorDetailAdapter majorDetailAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_recyclerview_list_university,container,false);
        rvList = view.findViewById(R.id.rvListUniversity);



        universityService = ServiceGenerator.createService(UniversityService.class);

        Call<List<UniversityDetail>> call=universityService.getUniversityDetail();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvList.setLayoutManager(new LinearLayoutManager(getContext()));

        majorDetailAdapter = new MajorDetailAdapter(universities,(AppCompatActivity) getContext());
        rvList.setAdapter(majorDetailAdapter);
        getUniversityDetail();
    }

    private void getUniversityDetail(){

        Call<List<UniversityDetail>> call=universityService.getUniversityDetail();


        call.enqueue(new Callback<List<UniversityDetail>>() {
            @Override
            public void onResponse(Call<List<UniversityDetail>> call, Response<List<UniversityDetail>> response) {

                universities=response.body();
                majorDetailAdapter.addMoreItems(universities);
            }

            @Override
            public void onFailure(Call<List<UniversityDetail>> call, Throwable t) {

            }
        });
    }


}
