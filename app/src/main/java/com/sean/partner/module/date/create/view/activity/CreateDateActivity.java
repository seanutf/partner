package com.sean.partner.module.date.create.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.sean.partner.MainActivity;
import com.sean.partner.R;
/**
 * 1.检测是否为默认头像，不是，不做任何处理，是则
 * 2.检测是否给相机权限，没有，申请权限，允许则不做任何处理，拒绝，给提示，允许则不做任何处理，再拒绝
 * 则提示将创建失败；有权限不做任何处理
 * 点击头像时：
 * 2.检测是否给相机权限，没有，申请权限，允许则不做任何处理，拒绝，给提示，允许则不做任何处理，再拒绝
 * 则提示将创建失败；有权限不做任何处理
 * */
public class CreateDateActivity extends MainActivity {

    @Override
    public void setActivityData() {

    }

    @Override
    public void loadActivityData() {

    }

    @Override
    public void getIntentData() {

    }

    @Override
    public void setActivityViewInitStatus(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void initActivityView() {

    }

    @Override
    public int getContentViewResourceId() {
        return R.layout.activity_layout_create_date;
    }
}
