package com.sean.partner.utils;

import android.content.Context;


/**
 * Created by sean on 2016/9/9.
 */
public class UserConfigures extends SharedPreferenceSupport {

    private static final String  PREFS_NAME = "user_configure";
    public UserConfigures(Context context) {
        super(context, PREFS_NAME);
    }

    public void setUserGuide(String versionCode, boolean value){
        setBoolean(versionCode,value);
    }

    public boolean getUserGuide(String versionCode){
        return  getBoolean(versionCode,false);
    }

    public void setUserAccount(String versionCode, String account){
        setString(versionCode,account);
    }

    public String getUserAccount(String versionCode){
        return  getString(versionCode,"");
    }
}
