package com.mgtec.liao.recyclerviewdemo.view;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mgtec.liao.recyclerviewdemo.R;

public class RecyclerViewHolder extends RecyclerView.ViewHolder {

    public ImageView imageView ;
    public TextView textView;


    public RecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.item_iv);
        textView = itemView.findViewById(R.id.item_tv);
    }

}
