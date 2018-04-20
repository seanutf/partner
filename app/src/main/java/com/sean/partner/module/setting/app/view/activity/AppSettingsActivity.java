package com.sean.partner.module.setting.app.view.activity;

import android.content.Intent;
import android.view.View;

import com.sean.partner.MainActivity;
import com.sean.partner.R;
import com.sean.partner.global.view.AppBarActivity;
import com.sean.partner.module.setting.app.view.fragment.AppSettingsFragment;

/**
 * 应用的设置页面
 * 初步约定从以下设置中选取几个作为设置选项：
 * 允许用户指定是否启用通知，
 * 允许用户指定是否启用同步，
 * 指定应用与云端同步数据的频率
 * 是否仅在有 Wi-Fi 时才执行上传/下载操作
 * */
public class AppSettingsActivity extends AppBarActivity {

    private static final String TAG = AppSettingsActivity.class.getSimpleName();

    public static final String KEY_ID_TEST = "key_id_test";

    public static void startActivity(MainActivity activity, String testId){
        Intent intent = new Intent(activity, AppSettingsActivity.class);
        intent.putExtra(KEY_ID_TEST, testId);
        activity.startActivity(intent);
    }


    @Override
    public int getChildViewResId() {
        return R.layout.activity_settings_app;
    }

    @Override
    public int getAppBarTitleResId() {
        return R.string.title_activity_settings_app;
    }

    @Override
    public void initChildView(View childView) {
        getSupportFragmentManager().beginTransaction().
                add(R.id.container, AppSettingsFragment.newInstance()).commitNowAllowingStateLoss();
    }
}
