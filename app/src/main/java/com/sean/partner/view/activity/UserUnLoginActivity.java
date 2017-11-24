package com.sean.partner.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.sean.partner.R;
import com.sean.partner.view.fragment.UserLoginFragment;
import com.sean.partner.view.fragment.UserRegisterConfirmFragment;
import com.sean.partner.view.fragment.UserRegisterFragment;

import java.util.ArrayList;

import cn.bmob.v3.BmobUser;


/**
 * Created by sean on 2017/1/7.
 */

public class UserUnLoginActivity extends AppCompatActivity implements UserRegisterFragment.RegisterUserDataListener{


    public ViewPager viewPager;
    UserLoginFragment userLoginFragment;
    UserRegisterFragment userRegisterFragment;
    ArrayList fragmentList;

    public static final String  USER_REGISTER_DATA = "USER_REGISTER_DATA";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_un_login);
        initView();

        fragmentList = new ArrayList<Fragment>();
        fragmentList.add(userRegisterFragment);
        fragmentList.add(userLoginFragment);


        viewPager.setAdapter(new LoginViewPagerAdapter(getSupportFragmentManager()));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position == 0){
                    UserRegisterFragment userRegisterFragment = (UserRegisterFragment)fragmentList.get(position);//获取被切换到的页面
                    userRegisterFragment.setToolBarTitle();
                }else if(position == 1){
                    UserLoginFragment userLoginFragment = (UserLoginFragment)fragmentList.get(position);//获取被切换到的页面
                    userLoginFragment.setToolBarTitle();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        // todo 根据设备号是否登录注册过来区分是显示登录还是注册
    }

    private void initView() {
        viewPager = (ViewPager) findViewById(R.id.vp_login);
        userLoginFragment = new UserLoginFragment();
        userRegisterFragment = new UserRegisterFragment();
        userRegisterFragment.setRegisterUserDataListener(this);
    }


    public class LoginViewPagerAdapter extends FragmentStatePagerAdapter {
        public LoginViewPagerAdapter(FragmentManager fm) {
            super(fm);
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
            return "";
        }


    }

    @Override
    public void finish() {
        super.finish();
        startActivity(new Intent(this,HomeActivity.class));
        overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
    }

    @Override
    public void userData(BmobUser user) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putSerializable(USER_REGISTER_DATA, user);
        UserRegisterConfirmFragment userRegisterConfirmFragment = new UserRegisterConfirmFragment();
        userRegisterConfirmFragment.setArguments(bundle);
        transaction.add(R.id.root_register_confirm_fragment, userRegisterConfirmFragment);
        transaction.commitNow();
    }
}
