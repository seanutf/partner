package com.sean.partner.Utils;

import android.content.Context;

import com.sean.partner.R;


/**
 * Created by sean on 2016/9/9.
 */
public class UserConfigures extends SharedPreferenceSupport{

    private static final String  PREFS_NAME = "user_configure";
    public UserConfigures(Context context) {
        super(context, PREFS_NAME);
    }

    public void setUserGuide(String versionCode,boolean value){
        setBoolean(versionCode,value);
    }

    public boolean getUserGuide(String versionCode){
        return  getBoolean(versionCode,false);
    }
}
