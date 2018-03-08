package com.sean.partner.module.login.abandon;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.sean.partner.R;
import com.sean.partner.utils.view.custom.CatchScrollLayout;
import com.sean.partner.utils.view.custom.SignUpContainer;

/**
 * Created by sean on 2016/9/14.
 * 来自开源项目：https://github.com/Yellow5A5/Material-Design-Login
 */
public class UserLoginActivity extends AppCompatActivity{

    private CatchScrollLayout mCatchScrollLayout;
    private SignUpContainer mSignUpContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        mCatchScrollLayout = (CatchScrollLayout) findViewById(R.id.catch_sroll_layout);
        mSignUpContainer = (SignUpContainer) findViewById(R.id.sign_up_container);

        mCatchScrollLayout.setIScrollCallBack(new CatchScrollLayout.IScrollCallBack() {
            @Override
            public void onScrollProcess(int process, boolean isLeft) {
                if (!isLeft){
                    process = 100 - process;
                }
                mSignUpContainer.setAnimProportion(process);
            }
        });

        mSignUpContainer.setIConfirmCallBack(new SignUpContainer.IConfirmCallBack() {
            @Override
            public void goNext() {
                //change this method and add the parameters you wanted
                //Add your task here.
            }
        });
    }
}
