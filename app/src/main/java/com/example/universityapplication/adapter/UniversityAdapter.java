package com.example.universityapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.universityapplication.MajorDetailActivity;
import com.example.universityapplication.R;
import com.example.universityapplication.UniversityDetailActivity;
import com.example.universityapplication.data.ServiceGenerator;
import com.example.universityapplication.fragement.LoginFragment;
import com.example.universityapplication.model.University;

import java.util.ArrayList;
import java.util.List;

public class UniversityAdapter extends RecyclerView.Adapter<UniversityAdapter.ViewHolder>{
    List<University> universityList;
    Context context;
    public static int UniverID=0;
    private UniversityAdapter.OnAdapterItemLongClickListener listener;

    public UniversityAdapter(List<University> universityList, Context context) {
        this.universityList = universityList;
        this.context = context;
        this.callback=(UniversityCallback) context;

    }


    public void addMoreItems(List<University> universities) {
        int previousPos=this.universityList.size();

        this.universityList.addAll(universities);
        notifyItemRangeInserted(previousPos-1,this.universityList.size());
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view =LayoutInflater.from(context).
                inflate(R.layout.university_list_item_layout,
                        viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
      final   University university=this.universityList.get(viewHolder.getAdapterPosition());
        viewHolder.title.setText(university.getUn_name_latin());
        viewHolder.tvTitleKhmer.setText(university.getUn_namekh());
        if (university.getImg_folder_name()==null){

           viewHolder.imgThumbnail.setImageResource(R.drawable.home_univer);
        }
        else {

            Glide
                    .with(context)
                   .load(ServiceGenerator.BASE_URL+""+university.getImg_folder_name())
                    //.placeholder(context.getResources().getDrawable(R.drawable.ic_insert_link_black_24dp))
                   //.override(350,350)
                    .into(viewHolder.imgThumbnail);
           // Toast.makeText(context,"LinkImage: "+university.getImg_folder_name(),Toast.LENGTH_SHORT).show();

       }

       viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               if(LoginFragment.useid!=0 && university.getUniver_id()==LoginFragment.useid){
                   Intent intent = new Intent(context, MajorDetailActivity.class);
                   UniverID = university.getUniver_id();
                   MajorDetailActivity.pri=UniverID;
                   context.startActivity(intent);

               }
               else{
                   Intent intent= new Intent(context, UniversityDetailActivity.class);
                   UniverID=university.getUniver_id();
                   UniversityDetailActivity.pri=UniverID;
                   context.startActivity(intent);
               }


           }
       });

        viewHolder.tvLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(university.getUniversity_link()));
                 context.startActivity(browserIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return universityList.size();
    }


    class  ViewHolder extends RecyclerView.ViewHolder{

    private TextView title,tvTitleKhmer,tvLink;
    private ImageView imgThumbnail;
    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        title=itemView.findViewById(R.id.tvTitle);
        tvTitleKhmer=itemView.findViewById(R.id.tvTitleKhmer);
        tvLink=itemView.findViewById(R.id.tvUnover_Link);
        imgThumbnail = itemView.findViewById(R.id.imgUniver);
    }
}

    UniversityCallback callback;
    public interface UniversityCallback{
        void onDelete(University university,int pos);
        void onEdit(University university,int pos);
    }

    public interface OnAdapterItemLongClickListener{

        void onAdapterItemLongClicked(int position);
    }

    public void setListener(UniversityAdapter.OnAdapterItemLongClickListener listener)
    {
        this.listener=listener;
    }
}
