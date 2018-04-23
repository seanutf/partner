package com.sean.partner.module.login.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

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

import static cn.bmob.v3.BmobUser.getCurrentUser;
import static com.sean.partner.module.login.view.activity.UserUnLoginActivity.USER_REGISTER_DATA;

/**
 * Created by sean on 2017/2/19.
 * 用户注册时的确认界面
 */

public class UserRegisterConfirmFragment extends MainFragment {

    BmobUser user;
    UserConfigures configures;
    Button btnRegister;
    TextView tvRegisterInfo;

    public static final String TAG = UserRegisterConfirmFragment.class.getSimpleName();


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void initView(View view) {
        btnRegister = (Button) view.findViewById(R.id.btn_register_confirm);
        tvRegisterInfo = (TextView) view.findViewById(R.id.tv_confirm_account);
    }

    private void toRegisterUser() {
        //注意：不能用save方法进行注册
        //todo 修改为手机号加密码登录，需要修改注册逻辑，见文档
        user.signUp(new SaveListener<BmobUser>() {
            @Override
            public void done(BmobUser s, BmobException e) {
                if (e == null && s != null) {
                    user.login(new SaveListener<User>() {
                        @Override
                        public void done(User user, BmobException e) {
                            if(e == null){
                                if(user != null){
                                    if(StringUtils.isNotBlank(user.getEmail())) {
                                        configures.setUserAccount(((PartnerApplication)activity.getApplication()).getVersion(), user.getEmail());
                                    } else if (StringUtils.isNotBlank(user.getMobilePhoneNumber())) {
                                        configures.setUserAccount(((PartnerApplication)activity.getApplication()).getVersion(), user.getMobilePhoneNumber());
                                    }

                                    if(getCurrentUser(User.class) != null){
                                        //登录成功，跳转页面
                                        if(activity instanceof UserUnLoginActivity)
                                            ((UserUnLoginActivity)activity).jumpHome();
                                    } else {
                                        //todo 提示登录错误，请重试
                                    }
                                }

                            } else {
                                //todo 输出异常到文件
                            }
                        }
                    });
                } else {
                    //todo 输出异常到文件
                }
            }
        });

    }

    @Override
    protected void initViewData(Bundle savedInstanceState) {
        configures = ((PartnerApplication)activity.getApplication()).getUserConfigures();
        Bundle bundle = getArguments();//从activity传过来的Bundle
        if (bundle != null) {
            user = (BmobUser) bundle.getSerializable(USER_REGISTER_DATA);
            if (user != null) {
                if (StringUtils.isNotBlank(user.getEmail())) {
                    tvRegisterInfo.setText("确定要使用" + user.getEmail() + "作为注册账户吗？");
                } else if (StringUtils.isNotBlank(user.getMobilePhoneNumber())) {
                    tvRegisterInfo.setText("确定要使用" + user.getMobilePhoneNumber() + "作为注册账户吗？");
                }
            }
        }
    }

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user_register_confirm, container, false);
    }

    @Override
    protected void setViewListener() {
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toRegisterUser();
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
