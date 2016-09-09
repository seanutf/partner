package com.sean.partner;

import android.app.Application;

import com.sean.partner.Utils.UserConfigures;

/**
 * Created by sean on 2016/9/9.
 */
public class PartnerApplication  extends Application{
    UserConfigures userConfigures;
    @Override
    public void onCreate() {
        super.onCreate();
    }

    public UserConfigures getUserConfigures(){
        userConfigures = new UserConfigures(this);
        return userConfigures;
    }
}
