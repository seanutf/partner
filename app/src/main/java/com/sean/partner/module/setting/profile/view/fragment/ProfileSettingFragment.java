package com.sean.partner.module.setting.profile.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sean.partner.MainFragment;
import com.sean.partner.R;

/**
 * Created by Sean on 2018/4/16.
 * 个人资料的设置页面
 */

public class ProfileSettingFragment extends MainFragment {

    public static ProfileSettingFragment newInstance(){
        return new ProfileSettingFragment();
    }

    @Override
    protected void initViewData(Bundle savedInstanceState) {

    }

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_settings_profile, container, false);
    }

    @Override
    protected void setViewListener() {

    }

    @Override
    protected void initView(View view) {

    }
}
