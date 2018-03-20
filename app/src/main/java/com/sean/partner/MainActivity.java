package com.sean.partner;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by sean on 2016/9/14.
 *
 * 各个Activity界面的父类
 */
public abstract class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewResourceId());
        setView(savedInstanceState);
        setData();
    }

    /**
     * 对页面中所使用的View进行初始化和设置
     * */
    private void setView(@Nullable Bundle savedInstanceState) {
        initActivityView();
        setActivityViewInitStatus(savedInstanceState);
    }

    /**
     * 获取页面所需数据，并进行设置
     * */
    private void setData(){
        getIntentData();
        loadActivityData();
        setActivityData();
    }

    /**
     * 获取根布局ResourceId
     *
     * @return 根布局ResourceId
     */
    public abstract int getContentViewResourceId();

    /**
     *
     * 对页面中已经初始化好了的View进行设置，包括：设置监听、模式、默认值等等
     * */
    public abstract void initActivityView();

    public abstract  void setActivityViewInitStatus(@Nullable Bundle savedInstanceState);

    public abstract void getIntentData();

    public abstract void loadActivityData();

    public abstract void setActivityData();

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }
}
