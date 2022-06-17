package com.mgtec.liao.recyclerviewdemo.adater;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.mgtec.liao.recyclerviewdemo.R;
import com.mgtec.liao.recyclerviewdemo.bean.MenuItem;
import com.mgtec.liao.recyclerviewdemo.view.MenuRecyclerViewHolder;

import java.util.List;

public class MenuRecyclerViewAdapter extends RecyclerView.Adapter<MenuRecyclerViewHolder> {

    private List<MenuItem> datas;
    private Context mContext;
    private int mLayoutId;
    private int currentPosition = 0;

    public Handler getmHandler() {
        return mHandler;
    }

    public void setmHandler(Handler mHandler) {
        this.mHandler = mHandler;
    }

    private Handler mHandler;

    public MenuRecyclerViewAdapter(List<MenuItem> datas, int mLayoutId) {
        this.datas = datas;
        this.mLayoutId = mLayoutId;
    }

    public List<MenuItem> getDatas() {
        return datas;
    }

    public void setDatas(List<MenuItem> datas) {
        this.datas = datas;
    }

    public Context getmContext() {
        return mContext;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }

    public int getmLayoutId() {
        return mLayoutId;
    }

    public void setmLayoutId(int mLayoutId) {
        this.mLayoutId = mLayoutId;
    }

    @NonNull
    @Override
    public MenuRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View layout = LayoutInflater.from(mContext).inflate(mLayoutId, parent, false);
        MenuRecyclerViewHolder holder = new MenuRecyclerViewHolder(layout);
        holder.textView.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    v.setBackground(mContext.getDrawable(R.drawable.item));
                    if(holder.getAdapterPosition() != currentPosition)
                        reFreshData(holder.getAdapterPosition());
                }else {
                    v.setBackground(mContext.getDrawable(R.drawable.ic_action_name));
                }
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MenuRecyclerViewHolder holder, int position) {
        holder.textView.setText(datas.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return null == datas ? 0 : datas.size();
    }



    private void reFreshData(int position) {
        currentPosition = position;
        Message msg = Message.obtain();
        msg.what = 001;
        msg.obj = position;
        mHandler.sendMessage(msg);
    }
}
