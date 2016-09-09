package com.sean.partner.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by sean on 2016/9/9.
 */
public class SharedPreferenceSupport {

    private SharedPreferences sharedPreference;

    public SharedPreferenceSupport(Context context, String preferenceName) {
        this.sharedPreference = context.getSharedPreferences(preferenceName, Context.MODE_PRIVATE);
    }

    public SharedPreferenceSupport(Context context){
        this.sharedPreference = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public boolean setString(String key, String value){
        SharedPreferences.Editor editor = sharedPreference.edit();
        editor.putString(key, value);
        return editor.commit();
    }

    public boolean removeString(String key) {
        SharedPreferences.Editor editor = sharedPreference.edit();
        editor.remove(key);
        return editor.commit();
    }

    public boolean setBoolean(String key, boolean value){
        SharedPreferences.Editor editor = sharedPreference.edit();
        editor.putBoolean(key, value);
        return editor.commit();
    }

    public boolean setInt(String key, int value){
        SharedPreferences.Editor editor = sharedPreference.edit();
        editor.putInt(key, value);
        return editor.commit();
    }

    public boolean setLong(String key, long value){
        SharedPreferences.Editor editor = sharedPreference.edit();
        editor.putLong(key, value);
        return editor.commit();
    }

    public String getString(String key, String defaultValue){
        return sharedPreference.getString(key, defaultValue);
    }

    public boolean getBoolean(String key, boolean defaultValue){
        return sharedPreference.getBoolean(key, defaultValue);
    }

    public int getInt(String key, int defaultValue){
        return sharedPreference.getInt(key, defaultValue);
    }

    public long getLong(String key, long defaultValue){
        return sharedPreference.getLong(key, defaultValue);
    }

    public boolean remove(String key){
        SharedPreferences.Editor editor = sharedPreference.edit();
        editor.remove(key);
        return editor.commit();
    }
}
