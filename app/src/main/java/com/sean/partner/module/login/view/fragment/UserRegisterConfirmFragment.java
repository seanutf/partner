package com.sean.partner.module.login.view.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.sean.partner.R;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

import static com.sean.partner.module.login.view.activity.UserUnLoginActivity.USER_REGISTER_DATA;

/**
 * Created by sean on 2017/2/19.
 *
 */

public class UserRegisterConfirmFragment extends Fragment{

    BmobUser user;
    Activity activity;


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.activity = (Activity)context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user_register_confirm, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    private void initView(View view) {
        Button btnRegister = (Button)view.findViewById(R.id.btn_register_confirm);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toRegisterUser();
            }
        });
        TextView tvRegisterInfo = (TextView)view.findViewById(R.id.tv_confirm_account);
        Bundle bundle = getArguments();//从activity传过来的Bundle
        if(bundle!=null){
            user = (BmobUser)bundle.getSerializable(USER_REGISTER_DATA);
        }

        if(user != null){
            tvRegisterInfo.setText("确定要使用" + user.getEmail() + "作为注册账户吗？");
        }

    }

    private void toRegisterUser() {
        //注意：不能用save方法进行注册
        user.signUp(new SaveListener<BmobUser>() {
            @Override
            public void done(BmobUser s, BmobException e) {
                if(e==null){
                    //toast("注册成功:" +s.toString());
                }else{
                    //loge(e);
                }
            }
        });

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
