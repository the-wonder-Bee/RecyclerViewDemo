package com.mgtec.liao.recyclerviewdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.mgtec.liao.recyclerviewdemo.adater.RecyclerViewAdapter;
import com.mgtec.liao.recyclerviewdemo.bean.ItemBean;
import com.mgtec.liao.recyclerviewdemo.view.CenterLinearLayoutManager;
import com.mgtec.liao.recyclerviewdemo.view.RecyclerViewHolder;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    RecyclerViewAdapter adapter;

    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1001:
                    RecyclerViewHolder holder = (RecyclerViewHolder) msg.obj;
                    recyclerView.post(()->{
                        adapter.changeView(holder.getAdapterPosition());
                    });
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        recyclerView = findViewById(R.id.recycler_view);
        List<ItemBean> datas = new ArrayList<>();
        for (int i = 0; i < 200; i++) {
            datas.add(new ItemBean(i,"tom"+i));
        }
        adapter = new RecyclerViewAdapter(datas);
        adapter.setMhandler(mHandler);
        adapter.setLayout(R.layout.item);
        adapter.setmContext(this);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager manager = new CenterLinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(manager);
    }





}