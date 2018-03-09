package com.sean.partner.module.login.view.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
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
import android.widget.Toast;

import com.sean.partner.MainFragment;
import com.sean.partner.R;
import com.sean.partner.module.login.view.activity.UserUnLoginActivity;
import com.sean.partner.utils.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.bmob.v3.BmobUser;

/**
 * Created by sean on 2017/1/7.
 * 用户注册页面
 */

public class UserRegisterFragment extends MainFragment {
    Activity activity;
    View rootView;
    TextView tvLogin;
    EditText etUserName, etUserPassword, etUserAccount;
    TextInputLayout tilUserName, tilUserPwd, tilUserAccount;

    RegisterUserDataListener registerUserDataListener;


    public interface RegisterUserDataListener {
        void userData(BmobUser user);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.activity = (Activity) context;
    }


    @Override
    protected void initViewData(Bundle savedInstanceState) {

    }

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_register, container, false);
        return rootView;
    }

    @Override
    protected void setViewListener() {
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((UserUnLoginActivity) getActivity()).viewPager.setCurrentItem(1);
            }
        });
        Button btnRegister = (Button) rootView.findViewById(R.id.btn_register);
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
                //s判空是因为清空editText后s为"";此时应为正常状态
                if (StringUtils.isNotBlank(s.toString()) && !StringUtils.isLetterDigitOrChinese(s.toString())) {
                    tilUserName.setError(getString(R.string.error_fragment_register_user_name));
                } else {
                    tilUserName.setError("");// 必须加上这个，否则会导致内容删除后，error信息显示为空白
                    tilUserName.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        etUserName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //s判空是因为清空editText后s为"";此时应为正常状态
                if (StringUtils.isNotBlank(s.toString()) && !StringUtils.isLetterDigitOrChinese(s.toString())) {
                    tilUserName.setError(getString(R.string.error_fragment_register_user_name));
                } else {
                    tilUserName.setError("");// 必须加上这个，否则会导致内容删除后，error信息显示为空白
                    tilUserName.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        etUserName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //s判空是因为清空editText后s为"";此时应为正常状态
                if (StringUtils.isNotBlank(s.toString()) && !StringUtils.isLetterDigitOrChinese(s.toString())) {
                    tilUserName.setError(getString(R.string.error_fragment_register_user_name));
                } else {
                    tilUserName.setError("");// 必须加上这个，否则会导致内容删除后，error信息显示为空白
                    tilUserName.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    protected void initView(View view) {
        tvLogin = (TextView) rootView.findViewById(R.id.tv_to_login);
        TextView tvLoginTitle = (TextView) activity.findViewById(R.id.unlogin_title);
        tvLoginTitle.setText("同趣—注册");
        etUserName = (EditText) rootView.findViewById(R.id.et_register_name);
        etUserPassword = (EditText) rootView.findViewById(R.id.et_register_password);
        etUserAccount = (EditText) rootView.findViewById(R.id.et_register_account);
        etUserName = (EditText) rootView.findViewById(R.id.et_register_name);
        tilUserName = rootView.findViewById(R.id.til_name);
        tilUserPwd = rootView.findViewById(R.id.til_pwd);
        tilUserAccount = rootView.findViewById(R.id.til_account);
    }


    public void setToolBarTitle() {
        TextView tvLoginTitle = (TextView) activity.findViewById(R.id.unlogin_title);
        tvLoginTitle.setText("同趣—注册");
    }

    private void tranDataToActivity() {
        String userName = etUserName.getText().toString().trim();
        String userPassword = etUserPassword.getText().toString().trim();
        String userAccount = etUserAccount.getText().toString().trim();
        if (!TextUtils.isEmpty(userName) && !TextUtils.isEmpty(userPassword) && !TextUtils.isEmpty(userAccount)) {
            BmobUser bu = new BmobUser();
            bu.setUsername(userName);
            bu.setPassword(userPassword);
            //现判定是否是邮箱，如果不是邮箱默认手机
            if(userAccount.contains("@") || userAccount.contains("\\.")){
                if(StringUtils.isConformEmailAddr(userAccount)){
                    bu.setEmail(userAccount);
                } else {
                    Toast.makeText(activity,getString(R.string.toast_fragment_register_user_email),Toast.LENGTH_SHORT).show();
                }
            }else {
                if(StringUtils.isMobileNumber(userAccount)){
                    bu.setMobilePhoneNumber(userAccount);
                }
            }
            if (registerUserDataListener != null) registerUserDataListener.userData(bu);
        }

    }

    public void setRegisterUserDataListener(RegisterUserDataListener registerUserDataListener) {
        this.registerUserDataListener = registerUserDataListener;
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