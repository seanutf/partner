package com.sean.partner.module.login.view.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import com.sean.partner.PartnerApplication;
import com.sean.partner.R;
import com.sean.partner.global.meta.User;
import com.sean.partner.module.main.HomeActivity;
import com.sean.partner.utils.UserConfigures;

import cn.bmob.v3.Bmob;

import static com.sean.partner.utils.KeyUtils.BOMB_APP_ID;

/**
     *
     * 启动页面
     * */
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_splash);
        getWindow().setBackgroundDrawable(null);
        initBmob();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                //发现有展示过引导页面，直接去登录等等，没有展示过，去展示
                if(getGuide()){
                    if(((PartnerApplication)getApplication()).getCurrentUser()){
                        finish();
                        startActivity(new Intent(SplashActivity.this,HomeActivity.class));

                    } else {
                        //展示登录注册页面
                        finish();
                        startActivity(new Intent(SplashActivity.this,UserUnLoginActivity.class));
                    }
                } else {
                    //引导页面
                    finish();
                    startActivity(new Intent(SplashActivity.this,UserGuideActivity.class));

                }
            }
        },1500);
    }

    private boolean getGuide() {
        UserConfigures configures = ((PartnerApplication)getApplication()).getUserConfigures();
        boolean result = configures.getUserGuide(((PartnerApplication)getApplication()).getVersion());
        return result;
    }

    /** Bmob后端云服务平台初始化
     * */
    private void initBmob() {
        //提供以下两种方式进行初始化操作：

        //第一：默认初始化
        Bmob.initialize(this, BOMB_APP_ID);

        //第二：自v3.4.7版本开始,设置BmobConfig,允许设置请求超时时间、文件分片上传时每片的大小、文件的过期时间(单位为秒)，
        //BmobConfig config =new BmobConfig.Builder(this)
        ////设置appkey
        //.setApplicationId("Your Application ID")
        ////请求超时时间（单位为秒）：默认15s
        //.setConnectTimeout(30)
        ////文件分片上传时每片的大小（单位字节），默认512*1024
        //.setUploadBlockSize(1024*1024)
        ////文件的过期时间(单位为秒)：默认1800s
        //.setFileExpiration(2500)
        //.build();
        //Bmob.initialize(config);
    }
}
