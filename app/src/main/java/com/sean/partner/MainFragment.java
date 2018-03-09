package com.sean.partner;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by sean on 2018/3/9.
 */
public abstract class MainFragment extends Fragment {

    public Activity activity;
    public MainFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.activity = (Activity) context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return createView(inflater, container, savedInstanceState);
    }

    /**
     * 这个方法传递进来的View就是{@link #onCreateView}方法中返回的View
     * */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewCreated(view, savedInstanceState);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
    }

    /**
     * 当Fragment已经绑定在Activity上，并创建View成功时，我们对其进行初始化设置
     * */
    private void viewCreated(View view, Bundle savedInstanceState){
        initView(view);
        setViewListener();
        initViewData(savedInstanceState);
    }

    /**
     * 对View设置初始数据
     * */
    protected abstract void initViewData(Bundle savedInstanceState);

    /**
     * 加载布局
     * */
    protected abstract View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    /**
     * 对View设置Listener
     * */
    protected abstract void setViewListener();

    /**
     * 初始化View
     * */
    protected abstract void initView(View view);

}
