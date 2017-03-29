package com.arshad.free.gopettingcodechallenge.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.arshad.free.gopettingcodechallenge.R;
import com.arshad.free.gopettingcodechallenge.models.UpcomingGuide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by arshad on 29/3/17.
 */

public class UpcomingGuideAdapter extends RecyclerView.Adapter<UpcomingGuideAdapter.DataObjectHolder> {

    private ArrayList<UpcomingGuide> upcomingGuides;
    private static MyClickListener myClickListener;
    private static Context context;

    public static class DataObjectHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        TextView txtName, txtEndDate;
        ImageView imgIcon;


        public DataObjectHolder(View itemView) {
            super(itemView);
            txtName = (TextView) itemView.findViewById(R.id.txt_name);
            txtEndDate = (TextView) itemView.findViewById(R.id.txt_end_date);
            imgIcon = (ImageView) itemView.findViewById(R.id.image_view);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            myClickListener.onItemClick(getPosition(), v);
        }
    }

    public void setOnItemClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

    public UpcomingGuideAdapter(ArrayList<UpcomingGuide> list, Context context) {
        this.upcomingGuides = list;
        this.context = context;
    }

    public void updateData(ArrayList<UpcomingGuide> list){
        this.upcomingGuides = list;
        notifyDataSetChanged();
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_list_item, parent, false);
        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(final DataObjectHolder holder, final int position) {

        holder.txtName.setText(upcomingGuides.get(position).getName());
        holder.txtEndDate.setText("End Date: "+upcomingGuides.get(position).getEndDate());
        Picasso.with(context)
                .load(upcomingGuides.get(position).getIcon())
                .placeholder(R.drawable.dummy_logo)
                .error(R.drawable.dummy_logo)
                .into(holder.imgIcon);
    }

    public void addItem(UpcomingGuide dataObj, int index) {
        upcomingGuides.add(dataObj);
        notifyItemInserted(index);
    }

    public void deleteItem(int index) {
        upcomingGuides.remove(index);
        notifyItemRemoved(index);
    }

    @Override
    public int getItemCount() {
        return upcomingGuides.size();
    }

    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }

}