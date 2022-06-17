package com.mgtec.liao.recyclerviewdemo.view;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mgtec.liao.recyclerviewdemo.R;

public class ListContentViewHolder extends RecyclerView.ViewHolder {

    public ImageView imageView;
    public TextView textView;
    public HightLightView lightView;

    public ListContentViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.list_content_iv);
        textView = itemView.findViewById(R.id.list_content_tv);
        lightView = itemView.findViewById(R.id.highlight);
    }
}
