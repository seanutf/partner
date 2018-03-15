package com.sean.partner.utils;

import android.content.Context;


/**
 * Created by sean on 2016/9/9.
 */
public class UserConfigures extends SharedPreferenceSupport {

    private static final String  PREFS_NAME = "user_configure";
    private static final String  key_user_account = "key_user_account";
    private static final String  key_user_guide = "key_user_guide";
    public UserConfigures(Context context) {
        super(context, PREFS_NAME);
    }

    public void setUserGuide(String versionCode, boolean value){
        setBoolean(versionCode + key_user_guide,value);
    }

    public boolean getUserGuide(String versionCode){
        return  getBoolean(versionCode + key_user_guide,false);
    }

    public void setUserAccount(String versionCode, String account){
        setString(versionCode + key_user_account, account);
    }

    public String getUserAccount(String versionCode){
        return  getString(versionCode + key_user_account, "");
    }
}
