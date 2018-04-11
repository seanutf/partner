package com.sean.partner;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import static android.os.Build.VERSION_CODES.KITKAT;

/**
 * Created by sean on 2016/9/14.
 * <p>
 * 各个Activity界面的父类
 */
public abstract class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewResourceId());
        setView(savedInstanceState);
        setData();
        //todo 方案还可以，就是性能上有待和其他方案对比，本身也有些性能问题需要优化
        if(Build.VERSION.SDK_INT == KITKAT)
            setStatusBar();
    }

    private void setStatusBar() {
        Window window = getWindow();
        ViewGroup mContentView = (ViewGroup) findViewById(Window.ID_ANDROID_CONTENT);

//First translucent status bar.
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        int statusBarHeight = getStatusBarHeight();

        View mChildView = mContentView.getChildAt(0);
        if (mChildView != null) {
            FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) mChildView.getLayoutParams();
            //如果已经为 ChildView 设置过了 marginTop, 再次调用时直接跳过
            if (lp != null && lp.topMargin < statusBarHeight && lp.height != statusBarHeight) {
                //不预留系统空间
                ViewCompat.setFitsSystemWindows(mChildView, false);
                lp.topMargin += statusBarHeight;
                mChildView.setLayoutParams(lp);
            }
        }

        View statusBarView = mContentView.getChildAt(0);
        if (statusBarView != null && statusBarView.getLayoutParams() != null && statusBarView.getLayoutParams().height == statusBarHeight) {
            //避免重复调用时多次添加 View
            statusBarView.setBackgroundColor(setStatusBarColor());
            return;
        }
        statusBarView = new View(this);
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, statusBarHeight);
        statusBarView.setBackgroundColor(setStatusBarColor());
//向 ContentView 中添加假 View
        mContentView.addView(statusBarView, 0, lp);
    }

    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    /**
     * 对页面中所使用的View进行初始化和设置
     */
    private void setView(@Nullable Bundle savedInstanceState) {
        initActivityView();
        setActivityViewInitStatus(savedInstanceState);
    }

    /**
     * 获取页面所需数据，并进行设置
     */
    private void setData() {
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
     * 对页面中已经初始化好了的View进行设置，包括：设置监听、模式、默认值等等
     */
    public abstract void initActivityView();

    public abstract void setActivityViewInitStatus(@Nullable Bundle savedInstanceState);

    public abstract void getIntentData();

    public abstract void loadActivityData();

    public abstract void setActivityData();

    public int setStatusBarColor(){
        return getResources().getColor(R.color.control_background);
    }

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
