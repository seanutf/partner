package com.sean.partner.global.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;

import com.sean.partner.MainActivity;
import com.sean.partner.R;

/**
 * Created by Sean on 2018/4/20.
 * 默认带有ToolBar的Activity
 * 且ToolBar带有返回键
 */

public abstract class AppBarActivity extends MainActivity {


    @Override
    public int getContentViewResourceId() {
        return R.layout.activity_layout_app_bar;
    }

    @Override
    public void initActivityView() {
        Toolbar mToolbar = (Toolbar) findViewById(R.id.activity_toolbar);
        mToolbar.setTitle(getAppBarTitleResId());
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        FrameLayout mActivityContainer = (FrameLayout)findViewById(R.id.activity_container);
        View childView = getLayoutInflater().inflate(getChildViewResId(),null, false);
        mActivityContainer.addView(childView);
        initChildView(childView);
    }

    public abstract int getAppBarTitleResId();

    public abstract int getChildViewResId();

    public abstract void initChildView(View childView);

    @Override
    public void setActivityViewInitStatus(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void getIntentData() {

    }

    @Override
    public void loadActivityData() {

    }

    @Override
    public void setActivityData() {

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}
