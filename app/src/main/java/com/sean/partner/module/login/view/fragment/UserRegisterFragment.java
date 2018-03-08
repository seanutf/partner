package com.sean.partner.module.login.view.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.sean.partner.R;
import com.sean.partner.module.login.view.activity.UserUnLoginActivity;
import com.sean.partner.utils.StringUtils;

import cn.bmob.v3.BmobUser;

/**
 * Created by sean on 2017/1/7.
 * 用户注册页面
 */

public class UserRegisterFragment extends Fragment {
    Activity activity;
    View rootView;
    EditText etUserName, etUserPassword, etUserAccount;

    RegisterUserDataListener registerUserDataListener;



    public interface RegisterUserDataListener{
        void userData(BmobUser user);
    }

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
        rootView = inflater.inflate(R.layout.fragment_register, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    public void setToolBarTitle(){
        TextView tvLoginTitle = (TextView)activity.findViewById(R.id.unlogin_title);
        tvLoginTitle.setText("同趣—注册");
    }

    private void initView() {
        TextView tvLogin = (TextView)rootView.findViewById(R.id.tv_to_login);
        TextView tvLoginTitle = (TextView)activity.findViewById(R.id.unlogin_title);
        tvLoginTitle.setText("同趣—注册");
        etUserName = (EditText)rootView.findViewById(R.id.et_register_name);
        etUserPassword = (EditText)rootView.findViewById(R.id.et_register_password);
        etUserAccount = (EditText)rootView.findViewById(R.id.et_register_account);
        etUserName = (EditText)rootView.findViewById(R.id.et_register_name);
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((UserUnLoginActivity)getActivity()).viewPager.setCurrentItem(1);
            }
        });
        Button btnRegister = (Button)rootView.findViewById(R.id.btn_register);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tranDataToActivity();
            }
        });

        etUserName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if(!StringUtils.isLetterDigitOrChinese(s.toString())){

                    }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void tranDataToActivity() {
        String userName = etUserName.getText().toString().trim();
        String userPassword = etUserPassword.getText().toString().trim();
        String userAccount = etUserAccount.getText().toString().trim();
        if(!TextUtils.isEmpty(userName) && !TextUtils.isEmpty(userPassword) && !TextUtils.isEmpty(userAccount)){
            BmobUser bu = new BmobUser();
            bu.setUsername(userName);
            bu.setPassword(userPassword);
            //todo 账户因为可能手机注册不能写死到邮箱
            bu.setEmail(userAccount);
            if(registerUserDataListener != null) registerUserDataListener.userData(bu);
        }

    }

    public void setRegisterUserDataListener(RegisterUserDataListener registerUserDataListener){
        this.registerUserDataListener  = registerUserDataListener;
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
