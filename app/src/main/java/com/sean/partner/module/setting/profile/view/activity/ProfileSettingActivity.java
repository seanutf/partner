package com.sean.partner.module.setting.profile.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.sean.partner.MainActivity;
import com.sean.partner.R;
import com.sean.partner.global.view.AppBarActivity;
import com.sean.partner.module.setting.profile.view.fragment.ProfileSettingFragment;

/**
 * Created by Sean on 2018/4/16.
 * 个人资料的设置页面
 */

public class ProfileSettingActivity extends AppBarActivity {

    public static void startActivityForResult(MainActivity activity, int requestCode){
        Intent intent = new Intent(activity, ProfileSettingActivity.class);
        activity.startActivityForResult(intent, requestCode);
    }

    @Override
    public int getAppBarTitleResId() {
        return R.string.title_activity_settings_profile;
    }

    @Override
    public int getChildViewResId() {
        return R.layout.activity_settings_profile;
    }

    @Override
    public void initChildView(View childView) {
        getSupportFragmentManager().beginTransaction().
                add(R.id.container, ProfileSettingFragment.newInstance()).commitNowAllowingStateLoss();
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
