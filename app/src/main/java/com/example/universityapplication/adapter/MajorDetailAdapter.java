package com.example.universityapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.universityapplication.EditMajorActivity;
import com.example.universityapplication.MainActivity;
import com.example.universityapplication.R;
import com.example.universityapplication.UniversityDetailActivity;
import com.example.universityapplication.data.ServiceGenerator;
import com.example.universityapplication.data.service.UniversityService;
import com.example.universityapplication.fragement.AddMajorFragment;
import com.example.universityapplication.fragement.RecyclerView_List_University_Fragment;
import com.example.universityapplication.fragement.UniversityRegisterFragment;
import com.example.universityapplication.model.FragmentHelper;
import com.example.universityapplication.model.UniversityDetail;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MajorDetailAdapter extends RecyclerView.Adapter<MajorDetailAdapter.ViewHolder>{

    List<UniversityDetail> universityDetailList;
  // Context context;
    AppCompatActivity context;

    public MajorDetailAdapter(List<UniversityDetail> universityDetailList,AppCompatActivity context) {
        this.universityDetailList = universityDetailList;
        this.context = context;
    }

    public void addMoreItems(List<UniversityDetail> universityDetails) {
        int previousPos=this.universityDetailList.size();

        this.universityDetailList.addAll(universityDetails);
        notifyItemRangeInserted(previousPos-1,this.universityDetailList.size());
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).
                inflate(R.layout.major_detail_layout,
                        viewGroup,false);

        return new MajorDetailAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {


        UniversityDetail university=this.universityDetailList.get(i);
        viewHolder.tvMajorKhmer.setText("Name Khmer : "+university.getMajorName_Kh());
        viewHolder.tvMajorLatin.setText("Name Latin : "+university.getMajorName_Eng());
        viewHolder.tvMajorPrice.setText("Price : "+university.getMa_Price());


        viewHolder.btnOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(context,viewHolder.btnOption);

                popup.inflate(R.menu.popup_menu);

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        switch (item.getItemId()) {

                            case R.id.edit:
                                Intent intent=new Intent(context, EditMajorActivity.class);
                                UniversityDetail listItem=universityDetailList.get(viewHolder.getAdapterPosition());

                                Bundle bundle=new Bundle();
                                bundle.putParcelable("data",listItem);
                                intent.putExtras(bundle);
                              // context.startActivity(intent);
                                context.startActivityForResult(intent,100);
                                return  true;

                            case R.id.remove:

                                UniversityService universityService= ServiceGenerator.createService(UniversityService.class);
                                UniversityDetail index=universityDetailList.get(viewHolder.getAdapterPosition());

                                Call<Integer> call=universityService.deleteMajor(index.getMajor_id());

                                call.enqueue(new Callback<Integer>() {
                                    @Override
                                    public void onResponse(Call<Integer> call, Response<Integer> response) {
                                        Toast.makeText(context,"Success",Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onFailure(Call<Integer> call, Throwable t) {

                                    }
                                });
                                universityDetailList.remove(viewHolder.getAdapterPosition());
                                notifyItemRemoved(viewHolder.getAdapterPosition());

                                return true;

                        }

                        return false;
                    }
                });

                popup.show();

            }
        });





    }

    @Override
    public int getItemCount() {
        return universityDetailList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvMajorKhmer,tvMajorLatin,tvMajorPrice;
        private ImageView btnOption;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMajorKhmer=itemView.findViewById(R.id.tvMajorNameKhmer);
            tvMajorLatin=itemView.findViewById(R.id.tvMaajorNameLatin);
            tvMajorPrice=itemView.findViewById(R.id.tvMajorPrice);
           btnOption=itemView.findViewById(R.id.btn_Option);
        }
    }
}
