package com.example.universityapplication.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.universityapplication.R;
import com.example.universityapplication.model.Login;

import java.util.List;

public class UserLoginAdapter extends RecyclerView.Adapter<UserLoginAdapter.ViewHolder> {


    List<Login> loginList;
    Context context;

    public UserLoginAdapter(List<Login> logins1, Context context) {
        this.loginList = logins1;
        this.context = context;
    }

    public void addMoreItems(List<Login> logins1) {
        int previousPos=this.loginList.size();
        this.loginList.addAll(logins1);
        notifyItemRangeInserted(previousPos-1,this.loginList.size());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view= LayoutInflater.from(context).
                inflate(R.layout.fragment_login,
                        viewGroup,false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        Login login=this.loginList.get(i);
        viewHolder.edUserName.setText(login.getUserName());
        viewHolder.edUserPass.setText(login.getUserPassword());





    }

    @Override
    public int getItemCount() {
        return loginList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private EditText edUserName,edUserPass;
        private Button btnLogin;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            edUserName=itemView.findViewById(R.id.ed_UserName);
            edUserPass=itemView.findViewById(R.id.ed_Password);
            btnLogin=itemView.findViewById(R.id.btn_Login);
        }
    }

}
