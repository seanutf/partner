package com.sean.partner.module.setting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sean.partner.MainActivity;
import com.sean.partner.R;
import com.sean.partner.module.setting.view.AppSettingsFragment;

public class AppSettingsActivity extends AppCompatActivity {

    private static final String TAG = AppSettingsActivity.class.getSimpleName();

    String testId;

    public static final String KEY_ID_TEST = "key_id_test";

    public static void startActivity(MainActivity activity, String testId){
        Intent intent = new Intent(activity, AppSettingsActivity.class);
        intent.putExtra(KEY_ID_TEST, testId);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        if(getIntent() != null){
            Intent intent = getIntent();
            testId = intent.getStringExtra(KEY_ID_TEST);
            if(testId != null && !testId.equals("")){
                testId += " good job!";
            }
        }

        getSupportFragmentManager().beginTransaction().
                add(R.id.container, AppSettingsFragment.newInstance()).commitNowAllowingStateLoss();
    }
}