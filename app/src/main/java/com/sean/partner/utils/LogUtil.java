package com.sean.partner.utils;

import android.content.Context;
import android.text.TextUtils;

import com.sean.partner.BuildConfig;

public class LogUtil {

	public static void initCrashHandler(Context context){
			CrashHandler.getInstance().init(context);
	}
	
	public static void e(String tag, Throwable tr){
		e(tag, null, tr);
	}
	
	public static void e(String tag, String msg){
		if(BuildConfig.DEBUG && !TextUtils.isEmpty(msg))
			android.util.Log.e(tag, msg);
	}
	
	public static void e(String tag, String msg, Throwable tr){
		if(BuildConfig.DEBUG && !TextUtils.isEmpty(msg))
			android.util.Log.e(tag, msg, tr);
	}

    public static void p(String tag, String msg){
        android.util.Log.i(tag, msg);
    }
	
	public static void i(String tag, String msg){
		if(BuildConfig.DEBUG && !TextUtils.isEmpty(msg))
			android.util.Log.i(tag, msg);
	}
	
	public static void i(String tag, String msg, Throwable tr){
		if(BuildConfig.DEBUG && !TextUtils.isEmpty(msg))
			android.util.Log.i(tag, msg, tr);
	}
	
	public static void d(String tag, String msg){
		if(BuildConfig.DEBUG && !TextUtils.isEmpty(msg))
			android.util.Log.d(tag, msg);
	}

	public static void d(String tag, String msg, Throwable tr){
		if(BuildConfig.DEBUG && !TextUtils.isEmpty(msg))
			android.util.Log.d(tag, msg, tr);
	}

	public static void v(String tag, String msg){
		if(BuildConfig.DEBUG && !TextUtils.isEmpty(msg))
			android.util.Log.v(tag, msg);
	}
	
	public static void v(String tag, String msg, Throwable tr){
		if(BuildConfig.DEBUG && !TextUtils.isEmpty(msg))
			android.util.Log.v(tag, msg, tr);
	}
	
	public static void w(String tag, String msg){
		if(BuildConfig.DEBUG && !TextUtils.isEmpty(msg))
			android.util.Log.w(tag, msg);
	}
	
	public static void w(String tag, String msg, Throwable tr){
		if(BuildConfig.DEBUG && !TextUtils.isEmpty(msg))
			android.util.Log.w(tag, msg, tr);
	}
	
}
