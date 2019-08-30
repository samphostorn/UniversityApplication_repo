package com.example.universityapplication;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.universityapplication.adapter.UniversityAdapter;
import com.example.universityapplication.adapter.UniversityDetailAdapter;
import com.example.universityapplication.adapter.UniversityListAdapter;
import com.example.universityapplication.data.ServiceGenerator;
import com.example.universityapplication.data.service.UniversityService;
import com.example.universityapplication.model.UniversityDetail;
import com.google.gson.JsonArray;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UniversityDetailActivity extends AppCompatActivity {


private TextView tvDetail;
UniversityListAdapter universityListAdapter;
List<UniversityDetail> universityDetails =new ArrayList<>();
UniversityService universityService;
UniversityDetailAdapter universityDetailAdapter;
ImageView imageView;
private  RecyclerView rvListMajor;
public  static  int pri=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_university_detail);
        rvListMajor=findViewById(R.id.rvListMajor);
        imageView=findViewById(R.id.img_detail);
        rvListMajor.setLayoutManager(new LinearLayoutManager(this));

        universityDetailAdapter=new UniversityDetailAdapter(universityDetails,this);
        rvListMajor.setAdapter(universityDetailAdapter);
        initUI();
    }

    private void     initUI(){
       tvDetail=findViewById(R.id.tvDetail);



        universityService= ServiceGenerator.createService(UniversityService.class);

            Call<List<UniversityDetail>> call=universityService.getUniversityDetailByID(pri);
            Log.e("UniversityID","UniversityID"+UniversityListAdapter.UniverID);

            call.enqueue(new Callback<List<UniversityDetail>>() {
                @Override
                public void onResponse(Call<List<UniversityDetail>> call, Response<List<UniversityDetail>> response) {
                    universityDetails=response.body();
                    universityDetailAdapter.addMoreItems(universityDetails);
                    if (universityDetails.size()>0){
                        if(universityDetails.get(0).getImg_folder_name()==null){
                            imageView.setImageResource(R.drawable.home_univer);
                        }
                        else{
                            Glide
                                    .with(imageView.getContext())
                                    .load(ServiceGenerator.BASE_URL+""+ universityDetails.get(0).getImg_folder_name())

                                    .into(imageView);

                        }
                    }
                    else  imageView.setImageResource(R.drawable.home_univer);
                }

                @Override
                public void onFailure(Call<List<UniversityDetail>> call, Throwable t) {

                }
            });





}
}
