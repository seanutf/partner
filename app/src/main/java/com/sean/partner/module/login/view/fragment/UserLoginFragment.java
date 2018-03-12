package com.sean.partner.module.login.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sean.partner.MainFragment;
import com.sean.partner.PartnerApplication;
import com.sean.partner.R;
import com.sean.partner.meta.User;
import com.sean.partner.module.login.view.activity.UserUnLoginActivity;
import com.sean.partner.utils.StringUtils;
import com.sean.partner.utils.UserConfigures;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by sean on 2017/1/7.
 * 用户登录界面
 */

public class UserLoginFragment  extends MainFragment {

    TextView tvLoginTitle, tvRegister;
    TextInputLayout tilUserAccount, tilUserPwd;
    EditText etUserAccount, etUserPwd;
    Button btnUserLogin;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void setToolBarTitle(){
        tvLoginTitle.setText(getString(R.string.title_fragment_login));
    }

    @Override
    protected void initViewData(Bundle savedInstanceState) {
        String lastAccount = getUserAccount();
        if(StringUtils.isNotBlank(lastAccount)){
            etUserAccount.setText(lastAccount);
        }
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

        btnUserLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doLogin();
            }
        });
    }

    private void doLogin() {
        String userPassword = etUserPwd.getText().toString().trim();
        String userAccount = etUserAccount.getText().toString().trim();
        if (!TextUtils.isEmpty(userPassword) && !TextUtils.isEmpty(userAccount)) {
            BmobUser user = new BmobUser();
            user.setPassword(userPassword);
            //现判定是否是邮箱，如果不是邮箱默认手机
            if(userAccount.contains("@") || userAccount.contains("\\.")){
                if(StringUtils.isConformEmailAddr(userAccount)){
                    user.setEmail(userAccount);
                } else {
                    Toast.makeText(activity,getString(R.string.toast_fragment_register_user_email),Toast.LENGTH_SHORT).show();
                    return ;
                }
            }else {
                if(StringUtils.isMobileNumber(userAccount)){
                    user.setMobilePhoneNumber(userAccount);
                } else {
                    Toast.makeText(activity,getString(R.string.toast_fragment_register_user_phone),Toast.LENGTH_SHORT).show();
                    return ;
                }
            }
            btnUserLogin.setEnabled(false);
            user.login(new SaveListener<User>() {
                @Override
                public void done(User user, BmobException e) {
                    if(e != null){
                        //todo 登录成功，跳转页面
                    } else {
                        //todo 输出异常到文件
                    }
                }
            });
        } else {
            if(TextUtils.isEmpty(userPassword)){
                Toast.makeText(activity,getString(R.string.toast_fragment_user_password_null),Toast.LENGTH_SHORT).show();
                return ;
            }
            if(TextUtils.isEmpty(userAccount)){
                Toast.makeText(activity,getString(R.string.toast_fragment_user_account_null),Toast.LENGTH_SHORT).show();
                return ;
            }

        }
    }

    @Override
    protected void initView(View view) {
        tvLoginTitle = (TextView)activity.findViewById(R.id.unlogin_title);
        tilUserAccount = (TextInputLayout)view.findViewById(R.id.til_account);
        tilUserPwd = (TextInputLayout)view.findViewById(R.id.til_pwd);
        etUserAccount = (EditText)view.findViewById(R.id.et_account);
        etUserPwd = (EditText)view.findViewById(R.id.et_pwd);
        btnUserLogin = (Button)view.findViewById(R.id.btn_login);
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

    /**
     * 取之前登录成功的帐号
     * */
    private String getUserAccount() {
        UserConfigures configures = ((PartnerApplication)activity.getApplication()).getUserConfigures();
        return configures.getUserAccount(((PartnerApplication)activity.getApplication()).getVersion());
    }
}