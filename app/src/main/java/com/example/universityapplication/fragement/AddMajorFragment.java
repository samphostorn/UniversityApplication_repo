package com.example.universityapplication.fragement;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.universityapplication.R;
import com.example.universityapplication.data.ServiceGenerator;
import com.example.universityapplication.data.service.UniversityService;
import com.example.universityapplication.model.Major;
import com.example.universityapplication.model.UniversityDetail;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddMajorFragment extends Fragment {

    UniversityService universityService;
    EditText edMajorKh,edMajorEng,edMajorPrice;
    Button btnSave;
    private UniversityDetail universityDetail;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_add_major,container,false);
        edMajorKh=view.findViewById(R.id.ed_Major_khmer);
        edMajorEng=view.findViewById(R.id.ed_major_eng);
        edMajorPrice=view.findViewById(R.id.ed_major_price);
        btnSave=view.findViewById(R.id.btnSave);
        universityService= ServiceGenerator.createService(UniversityService.class);
        return view;


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnSave.setOnClickListener(v->{
            Major major=new Major();
            major.setMajor_latin(edMajorEng.getText().toString());
            major.setMajor_namekh(edMajorKh.getText().toString());
            major.setMajor_price(Float.parseFloat(edMajorPrice.getText().toString()));
            major.setUniver_id(LoginFragment.useid);
            major.setUse_id(LoginFragment.useid);
            universityService.insertMajor(major)
                    .enqueue(new Callback<List<Major>>() {
                        @Override
                        public void onResponse(Call<List<Major>> call, Response<List<Major>> response) {
                                edMajorEng.setText("");
                                edMajorKh.setText("");
                                edMajorPrice.setText("");
                        }

                        @Override
                        public void onFailure(Call<List<Major>> call, Throwable t) {
                            edMajorEng.setText("");
                            edMajorKh.setText("");
                            edMajorPrice.setText("");

                        }
                    });
            alertOneButton("Success!");

        });

    }
    public void alertOneButton(String St) {
        AlertDialog.Builder b =new AlertDialog.Builder(getActivity());
        b.setTitle("Information!");
      //  b.setIcon(R.drawable.ic_warning_black_24dp);
        b.setMessage(St);
        b.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.d("msg","It is ok!");
            }
        });
        b.create()
                .show();
    }
}
