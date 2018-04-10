package com.sean.partner.module.setting.view;

import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sean.partner.MainFragment;
import com.sean.partner.R;

/**
 * Created by Administrator on 2018/4/10.
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
