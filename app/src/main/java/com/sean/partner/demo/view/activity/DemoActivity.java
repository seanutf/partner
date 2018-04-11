package com.sean.partner.demo.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.sean.partner.MainActivity;

/**
 * Created by Sean on 2018/4/11.
 * 基类{@link MainActivity}的子类创建示例DemoActivity
 */

public class DemoActivity extends MainActivity {

    public static void startActivity(MainActivity activity){
        Intent intent = new Intent(activity, DemoActivity.class);
        activity.startActivity(intent);
    }

    @Override
    public int getContentViewResourceId() {
        return 0;
    }

    @Override
    public void initActivityView() {

    }

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
}
