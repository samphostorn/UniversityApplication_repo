package com.example.universityapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.universityapplication.MajorDetailActivity;
import com.example.universityapplication.R;
import com.example.universityapplication.UniversityDetailActivity;
import com.example.universityapplication.data.ServiceGenerator;
import com.example.universityapplication.fragement.LoginFragment;
import com.example.universityapplication.fragement.MajorDetailFragment;
import com.example.universityapplication.fragement.UniversityDetailFragment;
import com.example.universityapplication.model.FragmentHelper;
import com.example.universityapplication.model.University;

import java.util.List;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;

public class UniversityListAdapter extends RecyclerView.Adapter<UniversityListAdapter.ViewHolder>{
  List<University> universityList;
   Context context;
   public static int UniverID=0;
    private OnAdapterItemLongClickListener listener;

    public UniversityListAdapter(List<University> universityList, Context context) {
        this.universityList = universityList;
        this.context = context;
//        this.listener=(OnAdapterItemLongClickListener) context;
//        this.callback=(UniversityCallback) context;

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
                inflate(R.layout.university_item_layout,
                        viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
       // University university=this.universityList.get(i);
        final University universityItem=universityList.get(viewHolder.getAdapterPosition());
        viewHolder.Unname_eng.setText(universityItem.getUn_name_latin());
        viewHolder.Unname_kh.setText(universityItem.getUn_namekh());
       // viewHolder.imgThumbnail.setImageResource(R.drawable.home_univer);
        if (universityItem.getImg_folder_name()==null){

            viewHolder.imgThumbnail.setImageResource(R.drawable.home_univer);
        }
        else {

            Glide
                    .with(context)
                    .load(ServiceGenerator.BASE_URL+""+universityItem.getImg_folder_name())
                    .into(viewHolder.imgThumbnail);
                   /*  Toast.makeText(context,"LinkImage: "+universityItem
                    .getImg_folder_name(),Toast.LENGTH_SHORT).show();*/

        }


        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               int pri=1;
               if(LoginFragment.useid!=0 && universityItem.getUniver_id()==LoginFragment.useid) {
                   Intent intent = new Intent(context, MajorDetailActivity.class);
                   UniverID = universityItem.getUniver_id();
                   MajorDetailActivity.pri=UniverID;
                   context.startActivity(intent);

               }else {

                   Intent intent = new Intent(context, UniversityDetailActivity.class);

                   UniverID = universityItem.getUniver_id();
                   UniversityDetailActivity.pri=UniverID;
                   Toast.makeText(context,"UniverID="+UniverID,Toast.LENGTH_SHORT).show();
                   context.startActivity(intent);
               }

            }

        });
        viewHolder.link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(universityItem.getUniversity_link()));
                context.startActivity(browserIntent);
            }
        });
    }



    @Override
    public int getItemCount() {
        return universityList.size();
    }


    class  ViewHolder extends RecyclerView.ViewHolder{

    private TextView Unname_eng,Unname_kh,link;
    private ImageView imgThumbnail;
    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        Unname_eng=itemView.findViewById(R.id.Un_name_eng);
        imgThumbnail = itemView.findViewById(R.id.imgUniversity_item);
        Unname_kh=itemView.findViewById(R.id.Un_name_kh);
        link=itemView.findViewById(R.id.tvUn_Link);
    }
}

    UniversityCallback callback;
    public interface UniversityCallback{
        void onDelete(University university, int pos);
        void onEdit(University university, int pos);
    }

    public interface OnAdapterItemLongClickListener{

        void onAdapterItemLongClicked(int position);
    }

    public void setListener(OnAdapterItemLongClickListener listener)
    {
        this.listener=listener;
    }
}
