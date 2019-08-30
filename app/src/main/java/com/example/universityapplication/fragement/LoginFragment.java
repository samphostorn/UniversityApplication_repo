package com.example.universityapplication.fragement;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.Toast;

import com.example.universityapplication.MainActivity;
import com.example.universityapplication.R;
import com.example.universityapplication.adapter.UniversityListAdapter;
import com.example.universityapplication.adapter.UserLoginAdapter;
import com.example.universityapplication.data.ServiceGenerator;
import com.example.universityapplication.data.service.UniversityService;
import com.example.universityapplication.model.FragmentHelper;
import com.example.universityapplication.model.Login;
import com.example.universityapplication.utils.CustomDialogFragment;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginFragment extends Fragment {

    private String str="";
    public static String upass="",useName="";
    public static int useid=0,logStatus=0;
    UniversityService universityService;
    UserLoginAdapter loginAdapter;
      List<Login> logins=new ArrayList<>();
    EditText edUser,edPassword;
    Button btnLogin;
    RecyclerView rvList;
    CustomDialogFragment dialog;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Toast.makeText(getContext(),"login",Toast.LENGTH_SHORT).show();
       View view=inflater.inflate(R.layout.fragment_login,container,false);
       edUser=view.findViewById(R.id.ed_UserName);
       edPassword=view.findViewById(R.id.ed_Password);
       btnLogin=view.findViewById(R.id.btn_Login);
       universityService= ServiceGenerator.createService(UniversityService.class);
        Call<List<Login>> call=universityService.getLogin();
        Toast.makeText(getContext(),"logined"+ logins,Toast.LENGTH_SHORT).show();
//        dialog=new CustomDialogFragment(getContext());
        return view;
    }

    public void alertOneButton(String St) {
        AlertDialog.Builder b =new AlertDialog.Builder(getActivity());
        b.setTitle("Warning!");
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


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loginAdapter=new UserLoginAdapter(logins,getContext());
        getUser();

            btnLogin.setOnClickListener(v->{
                str="";
                if (edUser.getText().toString().isEmpty() & edPassword.getText().toString().isEmpty()) {
                    str+="Please enter username and password"+"\n";
                }
                if (str==""){
                    if (logins.size() > 0) {
                        Toast.makeText(getContext(),"logins"+logins.size(),Toast.LENGTH_SHORT).show();
                        String s="",u="";
                        s=edUser.getText().toString();
                        u=edPassword.getText().toString();
                        for (int i = 0; i <= logins.size()-1; i++) {

                            if (u.equals(logins.get(i).getUserPassword())
                                    & s.equals(logins.get(i).getUserName())){
                                Log.e(TAG, "correct user:"+logins.get(i).getUserName()+logins.get(i).getUserPassword());

                                useName = logins.get(i).getUserName();
                                upass = logins.get(i).getUserPassword();
                                useid = Integer.parseInt(logins.get(i).getUseId());
                            }
                            //i=i+1;
                        }

                        if (useName == "" & upass == "") {
                            str += "UserName and Password are wrong" + "\n";
                            //Toast.makeText(getContext(),"WrongPassword",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            if (logStatus == 1) {
                                // Toast.makeText(getContext(),"AddMajor"+useName,Toast.LENGTH_SHORT).show();
                                FragmentHelper.replaceFragment(R.id.frmId, new AddMajorFragment(), true);


                            } else if (logStatus == 0) {
                                FragmentHelper.replaceFragment(R.id.frmId, new RecyclerView_List_University_Fragment(), true);

                            }

                        }
                    }

                    else {
                        str+="Wrong UserName and Password"+"\n";
                        // Toast.makeText(getContext(),"No data",Toast.LENGTH_SHORT).show();
                    }
                }

                if (str!="") {
                    alertOneButton(str);
                }





            });


    }

    private static final String TAG = "LoginFragment";
    private  void   getUser(){
   Call<List<Login>> call=universityService.getLogin();
  // Toast.makeText(getContext(),logins.toString(),Toast.LENGTH_SHORT).show();
   call.enqueue(new Callback<List<Login>>() {
       @Override
       public void onResponse(Call<List<Login>> call, Response<List<Login>> response) {
           //Log.e(TAG, "university" + response.body());
           logins=response.body();
           //Log.e(TAG, "logined" + logins);
           loginAdapter.addMoreItems(logins);
       }

       @Override
       public void onFailure(Call<List<Login>> call, Throwable t) {

       }
     });
    }






}
