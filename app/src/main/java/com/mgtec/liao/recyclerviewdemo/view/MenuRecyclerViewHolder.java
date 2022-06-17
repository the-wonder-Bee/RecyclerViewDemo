package com.mgtec.liao.recyclerviewdemo.view;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mgtec.liao.recyclerviewdemo.R;

public class MenuRecyclerViewHolder extends RecyclerView.ViewHolder {

    public TextView textView;

    public MenuRecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.menu_item);
    }
}
