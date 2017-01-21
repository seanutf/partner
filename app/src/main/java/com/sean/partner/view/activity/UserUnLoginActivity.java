package com.sean.partner.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.sean.partner.R;
import com.sean.partner.view.fragment.UserLoginFragment;
import com.sean.partner.view.fragment.UserRegisterFragment;

import java.util.ArrayList;


/**
 * Created by sean on 2017/1/7.
 */

public class UserUnLoginActivity extends AppCompatActivity {


    public ViewPager viewPager;
    UserLoginFragment userLoginFragment;
    UserRegisterFragment userRegisterFragment;
    ArrayList fragmentList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_un_login);
        initView();

        fragmentList = new ArrayList<Fragment>();

        fragmentList.add(userLoginFragment);
        fragmentList.add(userRegisterFragment);

        viewPager.setAdapter(new LoginViewPagerAdapter(getSupportFragmentManager()));

        // todo 根据设备号是否登录注册过来区分是显示登录还是注册
    }

    private void initView() {
        viewPager = (ViewPager) findViewById(R.id.vp_login);
        userLoginFragment = new UserLoginFragment();
        userRegisterFragment = new UserRegisterFragment();
    }


    public class LoginViewPagerAdapter extends FragmentPagerAdapter {
        public LoginViewPagerAdapter(FragmentManager fm) {
            super(fm);
            // TODO Auto-generated constructor stub
        }

        @Override
        public Fragment getItem(int arg0) {
            return (Fragment)fragmentList.get(arg0);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            // TODO Auto-generated method stub
            return "";
        }


    }

    @Override
    public void onBackPressed() {
        finish();
        startActivity(new Intent(this,MainActivity.class));
    }
}
