package com.example.universityapplication;

import android.app.Activity;
import android.bluetooth.BluetoothClass;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.universityapplication.adapter.MajorDetailAdapter;
import com.example.universityapplication.adapter.UniversityDetailAdapter;
import com.example.universityapplication.adapter.UniversityListAdapter;
import com.example.universityapplication.data.ServiceGenerator;
import com.example.universityapplication.data.service.UniversityService;
import com.example.universityapplication.fragement.AddMajorFragment;
import com.example.universityapplication.model.Major;
import com.example.universityapplication.model.MajorModel;
import com.example.universityapplication.model.University;
import com.example.universityapplication.model.UniversityDetail;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MajorDetailActivity extends AppCompatActivity {

    private TextView tvDetail;
    private ImageView imgUniversity;
    UniversityListAdapter universityListAdapter;
    List<UniversityDetail> universityDetails =new ArrayList<>();
    UniversityService universityService;
    University university;
    MajorDetailAdapter majorDetailAdapter;
    private RecyclerView rvListMajor;
    private int itemPosition;
    public static int pri;
    public static  final int CODE_REQUEST=99;
    public static  final int EDIT_REQUEST_CODE=100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_major_detail);
        rvListMajor=findViewById(R.id.rvListMajorDetail);
        rvListMajor.setLayoutManager(new LinearLayoutManager(this));
        majorDetailAdapter=new MajorDetailAdapter(universityDetails,this);
        rvListMajor.setAdapter(majorDetailAdapter);
        initUI();
    }


    private void     initUI(){
        tvDetail=findViewById(R.id.tvDetail);
        imgUniversity=findViewById(R.id.img_detail);
        getData();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==100 && resultCode==RESULT_OK){
            Log.e("TEST", "onActivityResult: " + data.getStringExtra("data") );
            MajorModel mod = new Gson().fromJson(data.getStringExtra("data"), MajorModel.class);
            Log.e("UNIVER",universityDetails.toString());
            int j =0;
            for (int i=0;i<universityDetails.size();i++)
            {
               if(universityDetails.get(i).getMajor_id()== mod.getMajor_id())
               {
                   j=i;
               }
            }
            universityDetails.get(j).setMajorName_Eng(mod.getMajor_latin());
            universityDetails.get(j).setMajorName_Kh(mod.getMajor_namekh());
            universityDetails.get(j).setMa_Price(mod.getMajor_price()+"");
            majorDetailAdapter.notifyDataSetChanged();

        }
    }

    private  void getData(){
        universityService= ServiceGenerator.createService(UniversityService.class);

        Call<List<UniversityDetail>> call=universityService.getUniversityDetailByID(pri);


        call.enqueue(new Callback<List<UniversityDetail>>() {
            @Override
            public void onResponse(Call<List<UniversityDetail>> call, Response<List<UniversityDetail>> response) {
                universityDetails=response.body();
                majorDetailAdapter.addMoreItems(universityDetails);
                //universityDetails.get(0).getMajor_id();
                if (universityDetails.size()>0){
                    if (universityDetails.get(0).getImg_folder_name()==null){

                        imgUniversity.setImageResource(R.drawable.home_univer);
                    }
                    else {

                        Glide
                                .with(imgUniversity.getContext())
                                .load(ServiceGenerator.BASE_URL+""+ universityDetails.get(0).getImg_folder_name())
                               // .placeholder(imgUniversity.getResources().getDrawable(R.drawable.home_univer))
                                //.override(350,350)
                                .into(imgUniversity);


                    }
                }

                else{
                    imgUniversity.setImageResource(R.drawable.home_univer);

                }

            }

            @Override
            public void onFailure(Call<List<UniversityDetail>> call, Throwable t) {

            }
        });
    }
}
