package com.sean.partner.module.setting.view;

import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;
import com.sean.partner.R;

/**
 * Created by Sean on 2018/4/10.
 * 应用设置页面的fragment
 */

public class AppSettingsFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.app_setting_preferences);
    }

    public static AppSettingsFragment newInstance() {
        return new AppSettingsFragment();
    }
}
