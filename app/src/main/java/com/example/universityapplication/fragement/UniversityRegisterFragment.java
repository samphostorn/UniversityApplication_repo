package com.example.universityapplication.fragement;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.CursorLoader;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.universityapplication.MainActivity;
import com.example.universityapplication.R;
import com.example.universityapplication.adapter.UniversityAdapter;
import com.example.universityapplication.data.ServiceGenerator;
import com.example.universityapplication.data.service.UniversityService;
import com.example.universityapplication.model.FragmentHelper;
import com.example.universityapplication.model.Login;
import com.example.universityapplication.model.University;
import com.example.universityapplication.model.UniversityRegister;
import com.example.universityapplication.model.UploadImageResponse;

import java.io.File;
import java.net.URI;
import java.util.List;
import java.util.PrimitiveIterator;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UniversityRegisterFragment extends Fragment {

    private String ImageName="";
    private String str="";
    private static final int REQUEST_EXTERNAL_STORAGE=1;
    private static String[] PERMISSIONS_STORAGE={
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    UniversityService universityService;
    EditText edUNamekh,edUNameLatin,edImgFolderName,edUEmail,edUPhone,edLink;
    EditText edUserName,edUserPassword;
    Button btnRegister;
    List<University>university;
    UniversityAdapter universityAdapter;
    String imageUrl;
    String ImagePath;
    ImageView imageView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_university_register,container,false);
        edUNamekh=view.findViewById(R.id.ed_univer_khmer);
        edUNameLatin=view.findViewById(R.id.ed_univer_latin);
        edUEmail=view.findViewById(R.id.ed_univer_email);
        edUPhone=view.findViewById(R.id.ed_univer_phone);
        edUserName=view.findViewById(R.id.edUserName);
        edUserPassword=view.findViewById(R.id.edUsePassword);
        btnRegister=view.findViewById(R.id.btnRegister);
        edLink=view.findViewById(R.id.edUn_linkWebsite);
        imageView=view.findViewById(R.id.imageView1);
        universityService= ServiceGenerator.createService(UniversityService.class);
      //  Call<List<University>> call=universityService.getUniversityList();

        //  verifyStoragePermissions(getActivity());
        return  view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnRegister.setOnClickListener(v->{
            str="";
        if(edUNameLatin.getText().toString().isEmpty()){
            str+="-Please insert English name"+"\n";
        }
         if(edUNamekh.getText().toString().isEmpty()){
            str+="-Please Insert Name Khmer"+"\n";
        }
         if(edUserPassword.getText().toString().isEmpty()){
            str+="-Insert Password"+"\n";
        }
        else if (edUPhone.getText().toString().isEmpty()){
            str+="-Please Insert Phone"+"\n";
        }
         if(edUserName.getText().toString().isEmpty()){
            str+="-Please Insert UserName" +"\n";
        }
         if (edUEmail.getText().toString().isEmpty()){
            str+="-Please Insert Email"+"\n";
        }
         if(edLink.getText().toString().isEmpty()){
            str+="-Please Insert Link"+"\n";
        }
       /* if(ImageName==""){
            str+="-Please insert photo"+"\n";
        }
        Log.e("image","image:"+ImageName);*/
        if (str==""){
        // Log.e("register","register:");

           uploadImage(ImagePath);
            UniversityRegister register=new UniversityRegister();
            register.setUniver_nameLatin(edUNameLatin.getText().toString());
            register.setUniver_namekh(edUNamekh.getText().toString());
            register.setUniver_email(edUEmail.getText().toString());
            register.setUniver_phone(edUPhone.getText().toString());
            register.setImg_folder_name("Content/uploads/"+ImageName);
            register.setUse_name(edUserName.getText().toString());
            register.setUse_password(edUserPassword.getText().toString());
            register.setLink("https://"+edLink.getText().toString());

            universityService.InsertUniversity(register)
                    .enqueue(new Callback<List<UniversityRegister>>() {
                        @Override
                        public void onResponse(Call<List<UniversityRegister>> call, Response<List<UniversityRegister>> response) {
                            Toast.makeText(getContext(),"Success: ",Toast.LENGTH_SHORT).show();
                            edUNamekh.setText("");
                            edUEmail.setText("");
                            //   edImgFolderName.setText("");
                            edUNameLatin.setText("");
                            edUPhone.setText("");
                            edUserPassword.setText("");
                            edUserName.setText("");
                            edLink.setText("");
                            FragmentHelper.replaceFragment(R.id.frmId,new RecyclerView_List_University_Fragment(),true);
                            //getActivity().getSupportFragmentManager().popBackStack();
                        }

                        @Override
                        public void onFailure(Call<List<UniversityRegister>> call, Throwable t) {
                            edUNamekh.setText("");
                            edUEmail.setText("");
                            //   edImgFolderName.setText("");
                            edUNameLatin.setText("");
                            edUPhone.setText("");
                            edUserPassword.setText("");
                            edUserName.setText("");
                            edLink.setText("");
                            FragmentHelper.replaceFragment(R.id.frmId,new RecyclerView_List_University_Fragment(),true);

                        }
                    });
        }
        else
        {
            Log.e("university","university:"+str);

            // Toast.makeText(getContext(),"NULLVALUE",Toast.LENGTH_SHORT).show();
            alertOneButton(str);
        }



        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               buttonBrowse_onClick(view);

            }

        });



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


private  void   buttonBrowse_onClick(View view){
   Intent intent=new Intent(Intent.ACTION_PICK);
   intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,"image/*");
   startActivityForResult(intent,10);

}

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==10){
            if(resultCode== Activity.RESULT_OK){

              Uri uri=data.getData();
             String[] columnInfo={MediaStore.Images.Media.DATA};
              Cursor cursor=getContext().getContentResolver().query(
                      uri,columnInfo,null,
                     null,
                     null
                      );
              cursor.moveToFirst();
              int columnIndex=cursor.getColumnIndex(columnInfo[0]);
             // ImageName=cursor.getString(columnIndex);
              //Log.e("pickImage","fileName"+filename);
           /*   Bitmap bitmap=BitmapFactory.decodeFile(filename);
              imageView.setImageBitmap(bitmap);*/
             ImagePath=getRealPathFromURI(uri);
             imageView.setImageURI(uri);
             imageView.setVisibility(View.VISIBLE);

            }
        }
    }

    //upload image to server
 private void uploadImage(String filename){

        MultipartBody.Part image=createMultipartBody(filename);
        Call<String> call=universityService.uploadImage(image);


     Log.e("APITest","APITest"+image);
        UploadImageResponse uploadImageResponse=new UploadImageResponse();
         uploadImageResponse.getPhoto();
        call.enqueue(new Callback<String>() {

            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.e("RESPONSE", "onResponse: " + response );
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e("FAILED", "onFailure: " + t.toString() );
            }
        });
}


//Create multipartBody.Part
private MultipartBody.Part createMultipartBody(String filename){
        //URI uri=URI.create(filename);
        File  file=new File(filename);
        ImageName=file.getName();
        RequestBody requestBody=RequestBody.create(MediaType.parse("multipart/form-data"),file);

        return  MultipartBody.Part.createFormData(
                "photo", file.getName(), requestBody);
}
    private String getRealPathFromURI(Uri contentUri){
        String[] proj={MediaStore.Images.Media.DATA};
        CursorLoader loader=new CursorLoader(getActivity().getApplicationContext(),contentUri,proj,
                null,null,null);
        Cursor cursor=loader.loadInBackground();
        int column_index=
                cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result=cursor.getString(column_index);
        cursor.close();
        return  result;

    }

    private  static  void verifyStoragePermissions(Activity activity){
        int permission= ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if(permission!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(activity,
                    PERMISSIONS_STORAGE,REQUEST_EXTERNAL_STORAGE
                    );
        }
    }

}

