package com.mgtec.liao.recyclerviewdemo.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.SearchView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.mgtec.liao.recyclerviewdemo.R;
import com.mgtec.liao.recyclerviewdemo.adater.ListContentAdapter;
import com.mgtec.liao.recyclerviewdemo.adater.MenuRecyclerViewAdapter;
import com.mgtec.liao.recyclerviewdemo.adater.RecyclerViewAdapter;
import com.mgtec.liao.recyclerviewdemo.bean.MenuItem;
import com.mgtec.liao.recyclerviewdemo.bean.Subjects;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.ls.LSException;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MenuActivity extends AppCompatActivity implements ListContentAdapter.ContentCallBack {

    public static final String TAG = "MenuActivity";


    private RecyclerView mRecycler,mContentRecycler;
    MenuRecyclerViewAdapter mAdapter;
    ListContentAdapter mListContentAdapter;
    List<MenuItem> datas;
    List<Subjects> mContentDatas = new ArrayList<>();;


    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 001:
                    int page = (int)msg.obj;
                    mContentRecycler.post(()->{
                        mListContentAdapter.changeContentDatas(page*40);
                    });
                    break;
                case 002:
                    mContentRecycler.post(()->{
                        mListContentAdapter.notifyDataSetChanged();
                    });
                    break;
                default:
                    break;
            }

        }
    };

    private void loadData() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_background);
        initData();
        initView();
        initListener();
    }

    private void initListener() {
        mListContentAdapter.setContentCallBack(this);
    }


    private void initView(){
        SearchView searchView = (SearchView)findViewById(R.id.search_view);
        searchView.setQueryHint("??????");
        mRecycler = findViewById(R.id.munu_rv);
        mRecycler.setHasFixedSize(true); //????????????
        mContentRecycler = findViewById(R.id.list_rv_content);
        mAdapter = new MenuRecyclerViewAdapter(datas,R.layout.menu_item);
        mAdapter.setmHandler(mHandler);
        mListContentAdapter = new ListContentAdapter(mContentDatas,R.layout.list_content);
        mListContentAdapter.setmHandler(mHandler);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,5);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mContentRecycler.setHasFixedSize(true);
        mContentRecycler.setAdapter(mListContentAdapter);
        mContentRecycler.setLayoutManager(gridLayoutManager);
        mRecycler.setAdapter(mAdapter);
        mRecycler.setLayoutManager(manager);
        //?????????????????????recyclerview??????notifyItemChanged()???????????????
        ((SimpleItemAnimator)mContentRecycler.getItemAnimator()).setSupportsChangeAnimations(false);
    }

    private void initData(){
        datas = new ArrayList<>();
        for (int i=0;i<10;i++){
            MenuItem menuItem = new MenuItem();
            menuItem.setPosition(i);
            datas.add(menuItem);
        }
        datas.get(0).setContent("????????????");
        datas.get(1).setContent("????????????");
        datas.get(2).setContent("????????????");
        datas.get(3).setContent("????????????");
        datas.get(4).setContent("????????????");
        datas.get(5).setContent("????????????");
        datas.get(6).setContent("????????????");
        datas.get(7).setContent("????????????");
        datas.get(8).setContent("????????????");
        datas.get(9).setContent("????????????");
        loadContent(mContentDatas,0);
    }


    @Override
    public void loadContent(List<Subjects> data, int page) {
        Thread thread = new Thread(()->{
            String url = "https://movie.douban.com/j/search_subjects?type=movie&tag=%E7%83%AD%E9%97%A8&sort=recommend&page_limit=40&page_start="+page;
            OkHttpClient client =  new OkHttpClient.Builder().build();
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(@NotNull Call call, @NotNull IOException e) {
                    Log.d(TAG, "onFailure: ????????????????????????");
                }

                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                    String responData = response.body().string();
                    JsonObject jsonObject = new JsonParser().parse(responData).getAsJsonObject();
                    JsonArray  jsonArray = jsonObject.getAsJsonArray("subjects");
                    Log.d(TAG, "onResponse: ???????????????"+jsonArray.toString());
                    Gson gson = new Gson();
                    Type type = new TypeToken<Subjects>() {}.getType();
                    data.clear();
                    for (JsonElement jsonElement : jsonArray) {
                        Subjects subjects = gson.fromJson(jsonElement,type );
                        data.add(subjects);
                    }
                    mHandler.sendEmptyMessage(002);
                    Log.d(TAG, "onResponse: ????????????size:"+data.size());
                }
            });
        });
        thread.start();
    }
}