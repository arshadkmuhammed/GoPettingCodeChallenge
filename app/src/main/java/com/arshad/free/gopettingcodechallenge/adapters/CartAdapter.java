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

public class CartAdapter  extends RecyclerView.Adapter<CartAdapter.DataObjectHolder> {

    private ArrayList<UpcomingGuide> cartItems;
    private static MyClickListener myClickListener;
    private static Context context;

    public static class DataObjectHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        TextView txtName, txtEndDate;
        ImageView imgIcon, imgClose;


        public DataObjectHolder(View itemView) {
            super(itemView);
            txtName = (TextView) itemView.findViewById(R.id.txt_name);
            txtEndDate = (TextView) itemView.findViewById(R.id.txt_end_date);
            imgIcon = (ImageView) itemView.findViewById(R.id.image_view);
            imgClose = (ImageView) itemView.findViewById(R.id.img_close);
            imgClose.setOnClickListener(this);
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

    public CartAdapter(ArrayList<UpcomingGuide> list, Context context) {
        this.cartItems = list;
        this.context = context;
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent,
                                                                    int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_cart_item, parent, false);
        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(final DataObjectHolder holder, final int position) {

        holder.txtName.setText(cartItems.get(position).getName());
        holder.txtEndDate.setText("End Date: "+cartItems.get(position).getEndDate());
        Picasso.with(context)
                .load(cartItems.get(position).getIcon())
                .placeholder(R.drawable.dummy_logo)
                .error(R.drawable.dummy_logo)
                .into(holder.imgIcon);
    }

    public void addItem(UpcomingGuide dataObj, int index) {
        cartItems.add(dataObj);
        notifyItemInserted(index);
    }

    public void deleteItem(int index) {
        cartItems.remove(index);
        notifyItemRemoved(index);
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }

}