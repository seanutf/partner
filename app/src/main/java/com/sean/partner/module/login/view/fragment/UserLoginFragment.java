package com.sean.partner.module.login.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sean.partner.MainFragment;
import com.sean.partner.R;
import com.sean.partner.module.login.view.activity.UserUnLoginActivity;

/**
 * Created by sean on 2017/1/7.
 * 用户登录界面
 */

public class UserLoginFragment  extends MainFragment {

    TextView tvLoginTitle, tvRegister;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void setToolBarTitle(){
        tvLoginTitle.setText(getString(R.string.title_fragment_login));
    }

    @Override
    protected void initViewData(Bundle savedInstanceState) {

    }

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login_in, container, false);
    }

    @Override
    protected void setViewListener() {
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((UserUnLoginActivity)getActivity()).viewPager.setCurrentItem(0);
            }
        });
    }

    @Override
    protected void initView(View view) {
        tvLoginTitle = (TextView)activity.findViewById(R.id.unlogin_title);
        tvRegister = (TextView)view.findViewById(R.id.tv_to_register);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}