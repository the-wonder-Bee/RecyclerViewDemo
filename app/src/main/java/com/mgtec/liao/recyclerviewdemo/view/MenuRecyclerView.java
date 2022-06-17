package com.mgtec.liao.recyclerviewdemo.view;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MenuRecyclerView extends RecyclerView {

    private static final String TAG = "MenuRecyclerView";
    private static  int currentPosition = -1;


    public MenuRecyclerView(@NonNull Context context) {
        super(context);
    }

    public MenuRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MenuRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public void requestChildFocus(View child, View focused) {
        Log.d(TAG, "requestChildFocus: ");
        super.requestChildFocus(child, focused);
        currentPosition = getChildViewHolder(child).getAdapterPosition();
    }

    @Override
    public void addFocusables(ArrayList<View> views, int direction) {
        super.addFocusables(views, direction);
    }

    //记住上一次item的焦点
    @Override
    public void addFocusables(ArrayList<View> views, int direction, int focusableMode) {
        View view = getLayoutManager().findViewByPosition(currentPosition);
        if (this.hasFocus() || currentPosition < 0 || view == null) {
            super.addFocusables(views,direction,focusableMode);
        }else if (view.isFocusable()){
            views.add(view);
        }else {
            super.addFocusables(views,direction,focusableMode);
        }
    }
}
