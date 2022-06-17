package com.mgtec.liao.recyclerviewdemo.adater;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mgtec.liao.recyclerviewdemo.bean.Subjects;
import com.mgtec.liao.recyclerviewdemo.view.ListContentViewHolder;

import java.util.ArrayList;
import java.util.List;



public class ListContentAdapter extends RecyclerView.Adapter<ListContentViewHolder> {

    private static final String TAG = "ListContentAdapter";

    private List<Subjects> mDatas ;
    private int mLayoutId;
    private Handler mHandler;

    public int loadState = 2;
    private final int LOADING = 1;
    private final int LOAD_END = 2;

    public ContentCallBack getContentCallBack() {
        return contentCallBack;
    }

    public void setContentCallBack(ContentCallBack contentCallBack) {
        this.contentCallBack = contentCallBack;
    }

    private ContentCallBack contentCallBack;

    public interface ContentCallBack {
        void loadContent(List<Subjects> data,int page);
    }

    public Handler getmHandler() {
        return mHandler;
    }

    public void setmHandler(Handler mHandler) {
        this.mHandler = mHandler;
    }

    private Context mContext;

    public ListContentAdapter(List<Subjects> mDatas, int mLayoutId) {
        this.mDatas = mDatas;
        this.mLayoutId = mLayoutId;
    }

    @NonNull
    @Override
    public ListContentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View v = LayoutInflater.from(mContext).inflate(mLayoutId, parent, false);
        ListContentViewHolder holder = new ListContentViewHolder(v);
        holder.imageView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    if(null != mDatas) {
                        mDatas.get(holder.getAdapterPosition()).setHighLight(true);
                        reFreshUI(v,holder.getAdapterPosition());
                    }
                }else{
                    if(null != mDatas){
                        mDatas.get(holder.getAdapterPosition()).setHighLight(false);
                        reFreshUI(v,holder.getAdapterPosition());
                    }
                }
            }
        });

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.hasFocus()){
                    //跳转到详情页面
                }else {

                }
            }
        });
        return holder;
    }


    private void reFreshUI(View v,int position){
        v.post(new Runnable() {
            @Override
            public void run() {
                notifyItemChanged(position);
            }
        });
    }

    @Override
    public void onBindViewHolder(@NonNull ListContentViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: ");
        if(null != mDatas && mDatas.size() != 0){
            Glide.with(mContext).load(mDatas.get(position).getCover()).into(holder.imageView);
            holder.textView.setText(mDatas.get(position).getTitle());
            //焦点框是否隐藏
            if (mDatas.get(position).isHighLight()){
                holder.lightView.setVisibility(View.VISIBLE);
            }else {
                holder.lightView.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public int getItemCount() {
        return null == mDatas ? 0 : mDatas.size();
    }

    public void changeContentDatas(int page){
        contentCallBack.loadContent(mDatas,page);
    }

}
