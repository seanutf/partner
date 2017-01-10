package com.sean.partner.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.sean.partner.R;
import com.sean.partner.view.fragment.UserLoginFragment;
import com.sean.partner.view.fragment.UserRegisterFragment;

import static com.sean.partner.R.id.fragment_root_register;

/**
 * Created by sean on 2017/1/7.
 */

public class UserUnLoginActivity extends AppCompatActivity implements UserLoginFragment.ToRegisterListener {

    private static final String LOGIN_FRAGMENT_TAG = "LOGIN_FRAGMENT";
    private static final String REGISTER_FRAGMENT_TAG = "REGISTER_FRAGMENT";

    FragmentManager fragmentManager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_un_login);
        // todo 根据设备号是否登录注册过来区分是显示登录还是注册
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_root_login, new UserLoginFragment(), LOGIN_FRAGMENT_TAG);

        fragmentTransaction.commit();
    }

    @Override
    public void toRegister() {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_root_register,new UserRegisterFragment(),REGISTER_FRAGMENT_TAG);
        fragmentTransaction.commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
        UserLoginFragment userLoginFragment = (UserLoginFragment)fragmentManager.findFragmentByTag(LOGIN_FRAGMENT_TAG);
        userLoginFragment.setRegisterListener(this);
    }
}
