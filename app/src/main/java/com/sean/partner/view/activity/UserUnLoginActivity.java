package com.sean.partner.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.sean.partner.R;
import com.sean.partner.view.fragment.UserLoginFragment;

/**
 * Created by sean on 2017/1/7.
 */

public class UserUnLoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_un_login);
        // todo 根据设备号是否登录注册过来区分是显示登录还是注册
        FragmentManager  fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_root_login, new UserLoginFragment());
        fragmentTransaction.commit();
    }
}
