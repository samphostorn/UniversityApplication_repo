package com.example.universityapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.universityapplication.data.ServiceGenerator;
import com.example.universityapplication.data.service.UniversityService;
import com.example.universityapplication.model.MajorModel;
import com.example.universityapplication.model.UniversityDetail;
import com.example.universityapplication.model.UniversityRegister;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditMajorActivity extends AppCompatActivity {

    private EditText edMajorKhmer,edMajorLatin,edMajorPrice;
    private Button btnSave;
    private UniversityDetail listItem;
    private int major_id;

    MajorModel major;
    UniversityService universityService;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_major);
        InitUI();
        btnSave.setOnClickListener(v->{
            Updated();
        });
    }

    private  void InitUI(){
        edMajorKhmer=findViewById(R.id.ed_Edit_Major_khmer);
        edMajorLatin=findViewById(R.id.ed_Edit_major_eng);
        edMajorPrice=findViewById(R.id.ed_Edit_major_price);
        btnSave=findViewById(R.id.btnUpdate);

        if(getIntent()!=null){
            listItem=getIntent().getParcelableExtra("data");
            edMajorKhmer.setText(listItem.getMajorName_Kh());
            edMajorLatin.setText(listItem.getMajorName_Eng());
            edMajorPrice.setText(listItem.getMa_Price());
            major_id=listItem.getMajor_id();
          //  Toast.makeText(this,"mm: "+major_id,Toast.LENGTH_SHORT).show();

        }
    }

    private void  Updated(){
        major=new MajorModel();
        major.setMajor_id(major_id);
        //Log.e("Major","Major"+major_id);
        major.setMajor_namekh(edMajorKhmer.getText().toString());
        major.setMajor_latin(edMajorLatin.getText().toString());
        major.setMajor_price(Float.parseFloat(edMajorPrice.getText().toString()));
        universityService= ServiceGenerator.createService(UniversityService.class);
        Call<List<MajorModel>> call=universityService.UpdateMajor(major);
        call.enqueue(new Callback<List<MajorModel>>() {
            @Override
            public void onResponse(Call<List<MajorModel>> call, Response<List<MajorModel>> response) {


            }

            @Override
            public void onFailure(Call<List<MajorModel>> call, Throwable t) {

            }
        });
       /* major.setMajor_id(major_id);
        Log.e("Major","Major"+major.getMajor_id());
        major.setMajor_namekh(edMajorKhmer.getText().toString());
        major.setMajor_latin(edMajorLatin.getText().toString());
        major.setMajor_price(Float.parseFloat(edMajorPrice.getText().toString()));*/
       Intent intent=new Intent();
        //Bundle bundle=new Bundle();
        //bundle.putParcelable("data",major);
        if(major!=null){
            //intent.putExtras(bundle);
            // intent.putExtra("Index",)
            intent.putExtra("data", new Gson().toJson(major));
            setResult(RESULT_OK, intent);
           // Log.e("Major","Major"+major.getMajor_id());

             finish();
        }

    }
}
