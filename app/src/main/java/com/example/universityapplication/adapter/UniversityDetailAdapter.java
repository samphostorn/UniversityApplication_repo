package com.example.universityapplication.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.universityapplication.R;
import com.example.universityapplication.model.University;
import com.example.universityapplication.model.UniversityDetail;

import java.util.List;

public class UniversityDetailAdapter extends RecyclerView.Adapter<UniversityDetailAdapter.ViewHolder> {

    List<UniversityDetail> universityDetailList;
    Context context;
    //AppCompatActivity context;

    public UniversityDetailAdapter(List<UniversityDetail> universityDetailList, Context context) {
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
                inflate(R.layout.major_list_item_layout,
                        viewGroup,false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        UniversityDetail university=this.universityDetailList.get(i);
        viewHolder.tvNameKhmer.setText("Name Khmer : "+university.getMajorName_Kh());
        viewHolder.tvNameLatin.setText("Name Latin : "+university.getMajorName_Eng());
        viewHolder.tvPrice.setText("Price : "+university.getMa_Price());


    }

    @Override
    public int getItemCount() {
        return universityDetailList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tvNameKhmer,tvNameLatin,tvPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNameKhmer=itemView.findViewById(R.id.tvNameKhmer);
            tvNameLatin=itemView.findViewById(R.id.tvNameLatin);
            tvPrice=itemView.findViewById(R.id.tvPrice);
        }
    }
}
