package com.mgtec.liao.recyclerviewdemo.adater;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mgtec.liao.recyclerviewdemo.R;
import com.mgtec.liao.recyclerviewdemo.bean.ItemBean;
import com.mgtec.liao.recyclerviewdemo.view.RecyclerViewHolder;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {

    private List<ItemBean> datas;

    private int layout;

    //刷新局部UI
    private Handler mhandler;

    private Context mContext;

    public Context getmContext() {
        return mContext;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }

    public List<ItemBean> getDatas() {
        return datas;
    }

    public void setDatas(List<ItemBean> datas) {
        this.datas = datas;
    }

    public Handler getMhandler() {
        return mhandler;
    }

    public void setMhandler(Handler mhandler) {
        this.mhandler = mhandler;
    }

    public int getLayout() {
        return layout;
    }

    public void setLayout(int layout) {
        this.layout = layout;
    }

    public RecyclerViewAdapter(List<ItemBean> datas) {
        this.datas = datas;
    }


    /**
     * 绑定布局和监听事件
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(getLayout(), parent, false);
        RecyclerViewHolder holder = new RecyclerViewHolder(view);
        //绑定监听事件
        holder.itemView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    //更新数据
                    datas.get(holder.getAdapterPosition()).setHighLight(true);
                    //post进行异步刷新
                    reFreshUI(holder);
                }else {
                    //更新数据
                    datas.get(holder.getAdapterPosition()).setHighLight(false);
                    reFreshUI(holder);
                }
            }
        });
        return holder;
    }

    /**
     * 让handler去更新UI
     * @param holder
     */
    private void reFreshUI(RecyclerViewHolder holder ){
        Message msg = Message.obtain();
        msg.what = 1001;
        msg.obj = holder;
        mhandler.sendMessage(msg);
    }

    /**
     * 刷新局部
     * @param position
     */
    public void changeView(int position){
        //刷新指定位置UI
        notifyItemChanged(position);
    }

    /**
     * 和datas绑定数据
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        if(datas.get(position).isHighLight()){
            holder.itemView.setBackgroundColor(Color.RED);
        }else {
            holder.itemView.setBackgroundColor(Color.GREEN);
        }
        holder.textView.setText(datas.get(position).getMsg());

        Glide.with(holder.imageView.getContext())
                .load(holder.imageView.getContext().getDrawable(R.drawable.liu))
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return null == datas ? 0 : datas.size() ;
    }
}
