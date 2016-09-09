package com.sean.partner.Utils;

import android.content.Context;

import com.sean.partner.R;


/**
 * Created by sks on 2016/9/9.
 */
public class UserConfigures extends SharedPreferenceSupport{

    private static final String  PREFS_NAME = "user_configure";
    public UserConfigures(Context context) {
        super(context, PREFS_NAME);
    }
}
