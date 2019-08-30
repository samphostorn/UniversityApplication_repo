package com.example.universityapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.universityapplication.adapter.UniversityAdapter;
import com.example.universityapplication.data.ServiceGenerator;
import com.example.universityapplication.data.service.UniversityService;
import com.example.universityapplication.fragement.AddMajorFragment;
import com.example.universityapplication.fragement.LoginFragment;
import com.example.universityapplication.fragement.RecyclerView_List_University_Fragment;
import com.example.universityapplication.fragement.UniversityAddFragment;
import com.example.universityapplication.fragement.UniversityDetailFragment;
import com.example.universityapplication.fragement.UniversityListFragment;
import com.example.universityapplication.fragement.UniversityRegisterFragment;
import com.example.universityapplication.model.FragmentHelper;
import com.example.universityapplication.model.University;
import com.example.universityapplication.utils.CustomDialogFragment;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity
        implements UniversityAdapter.UniversityCallback,
        NavigationView.OnNavigationItemSelectedListener {


    UniversityService universityService;
    RecyclerView rvList;
    List<University> universities = new ArrayList<>();
    UniversityAdapter universityAdapter;
    String test;
    CustomDialogFragment dialog;
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private ActionBarDrawerToggle toggle;
    private FrameLayout frmId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        universityService = ServiceGenerator.createService(UniversityService.class);
        Call<List<University>> call = universityService.getUniversityList();

        initView();
        //getUniversity();
        FragmentHelper.addFragment(this,R.id.frmId,new RecyclerView_List_University_Fragment());
        //addFragment(R.id.frmId,new RecyclerView_List_University_Fragment());
       // Toast.makeText(this, "un" + universities.toString(), Toast.LENGTH_SHORT).show();

    }


    private void initView() {
       /*rvList = findViewById(R.id.rvListUniversity);
        rvList.setLayoutManager(new LinearLayoutManager(this));

        universityAdapter = new UniversityAdapter(universities, this);
        rvList.setAdapter(universityAdapter);*/
        dialog=new CustomDialogFragment(this);
        drawerLayout = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolbar_main);
        navigationView = findViewById(R.id.nav);
        frmId=findViewById(R.id.frmId);
        //setup toolbar
        toolbar.setTitle("Home");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.nav_toggle_open,
                R.string.nav_toggle_close
        );
        drawerLayout.addDrawerListener(toggle);

        //add event listener to navigation view
        navigationView.setNavigationItemSelectedListener(this);
    }

    private static final String TAG = "MainActivity";

    private void getUniversity() {

        Call<List<University>> call = universityService.getUniversityList();
        //Toast.makeText(this, "getun" + universityService.getUniversityList(), Toast.LENGTH_SHORT).show();
        call.enqueue(new Callback<List<University>>() {
            @Override
            public void onResponse(Call<List<University>> call, retrofit2.Response<List<University>> response) {
                //Log.e(TAG, "university" + response.body());
                universities = response.body();
                universityAdapter.addMoreItems(universities);

            }

            @Override
            public void onFailure(Call<List<University>> call, Throwable t) {
               // Log.e(TAG, "University: " + t.toString());
            }
        });
    }

    @Override
    public void onDelete(University university, int pos) {

    }

    @Override
    public void onEdit(University university, int pos) {

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.btnHome:
                //replaceFragment(R.id.frmId,new RecyclerView_List_University_Fragment());
                FragmentHelper.replaceFragment(R.id.frmId,new RecyclerView_List_University_Fragment(),false);
                setToolbarTittle("Home");
                return true;
            case R.id.btnRegister:
                //replaceFragment(R.id.frmId,new UniversityRegisterFragment());
                FragmentHelper.replaceFragment(R.id.frmId,new UniversityRegisterFragment(),false);

                setToolbarTittle("Register");
//                rvList.setVisibility(View.GONE);
                return true;

            case R.id.btnUnList:
                FragmentHelper.replaceFragment(R.id.frmId,new UniversityListFragment(),false);

                setToolbarTittle("List University");
                return true;

            case R.id.btnLogin:
                FragmentHelper.replaceFragment(R.id.frmId,new LoginFragment(),false);

                setToolbarTittle("Login");
                return true;
            case R.id.btnAddMajor:
                if (LoginFragment.useName!="" & LoginFragment.upass!=""){
                    LoginFragment.logStatus=1;
                    FragmentHelper.replaceFragment(R.id.frmId,new AddMajorFragment(),false);

                    setToolbarTittle("Add Major For Your own University");
                    Toast.makeText(this,"Login success",Toast.LENGTH_SHORT).show();

                    return true;
                }

                else{

                    FragmentHelper.replaceFragment(R.id.frmId,new LoginFragment(),false);

                    setToolbarTittle("Login");
                    return true;

                }
            case R.id.btnLogOut:
                LoginFragment.useName="";
                LoginFragment.useid=0;
                alertOneButton("Success!");
               /* FragmentHelper.addFragment(this,R.id.frmId,new LoginFragment());
                return true;*/

        }

        return false;

    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        toggle.syncState();
    }


    private void setToolbarTittle(String title) {
        getSupportActionBar().setTitle(title);
        drawerLayout.closeDrawer(GravityCompat.START);
    }


    public  static void replaceFragment(@IdRes int contianer, Fragment fragment, boolean addToBackStack){


        if(fragment !=null){

        }
    }
    private void replaceFragment(@IdRes int container, Fragment fragment) {
        if (fragment != null) {
            FragmentTransaction t = getSupportFragmentManager().beginTransaction();
            t.replace(container, fragment);
            t.commit();
        }

    }

 /*   public  static  void replaceFragment2(@IdRes int contianer,Fragment fragment,boolean addToBackStack){
        if(fragment !=null){
            FragmentTransaction t= Context.getS.beginTransaction();

        }
    }*/
    private void addFragment(@IdRes int container, Fragment fragment) {
        if (fragment != null) {
            FragmentTransaction t = getSupportFragmentManager().beginTransaction();
            t.add(container, fragment);
            t.commit();
        }

    }

    public void alertOneButton(String St) {
        AlertDialog.Builder b =new AlertDialog.Builder(this);
        b.setTitle("Information!");
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