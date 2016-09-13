package com.sean.partner.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.Toast;

import com.sean.partner.PartnerApplication;
import com.sean.partner.R;
import com.sean.partner.Utils.UserConfigures;
import com.sean.partner.meta.User;

import org.json.JSONArray;
import org.json.JSONObject;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
       //setTheme(R.style.m);
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);
        getWindow().setBackgroundDrawable(null);
        initBmob();

        //发现有展示过引导页面，直接去登录等等，没有展示过，去展示
        if(getGuide()){
            Toast.makeText(this,"不展示引导页面",Toast.LENGTH_SHORT).show();
            if(getCurrentUser()){
                //todo 直接展示主界面
            } else {
                //todo 展示登录注册页面
            }
        } else {
            //todo 引导页面,并且在引导页面检查用户登录情况
            Toast.makeText(this,"展示引导页面",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this,UserGuideActivity.class));
            finish();

//            if(getCurrentUser()){
//
//            }
        }
    }

    private boolean getGuide() {
        UserConfigures configures = ((PartnerApplication)getApplication()).getUserConfigures();
        boolean result = configures.getUserGuide(((PartnerApplication)getApplication()).getVersion());
        return result;
    }

    /** Bmob后端云服务平台初始化
     * */
    private void initBmob() {
        //提供以下两种方式进行初始化操作：

        //第一：默认初始化
        Bmob.initialize(this, getString(R.string.bmob_application_id));

        //第二：自v3.4.7版本开始,设置BmobConfig,允许设置请求超时时间、文件分片上传时每片的大小、文件的过期时间(单位为秒)，
        //BmobConfig config =new BmobConfig.Builder(this)
        ////设置appkey
        //.setApplicationId("Your Application ID")
        ////请求超时时间（单位为秒）：默认15s
        //.setConnectTimeout(30)
        ////文件分片上传时每片的大小（单位字节），默认512*1024
        //.setUploadBlockSize(1024*1024)
        ////文件的过期时间(单位为秒)：默认1800s
        //.setFileExpiration(2500)
        //.build();
        //Bmob.initialize(config);
    }

    /**
     * 获取当前本地用户
     */
    private boolean getCurrentUser() {
		User myUser = BmobUser.getCurrentUser(User.class);
		if (myUser != null) {
//			log("本地用户信息:objectId = " + myUser.getObjectId() + ",name = " + myUser.getUsername()
//					+ ",age = "+ myUser.getAge());
            Toast.makeText(this,"本地用户信息:objectId = " + myUser.getObjectId() + ",name = " + myUser.getUsername()
                    + ",age = "+ myUser.getAge(),Toast.LENGTH_LONG).show();
            return true;
		} else {
			//toast("本地用户为null,请登录。");
            Toast.makeText(this,"本地用户为null,请登录。",Toast.LENGTH_SHORT).show();
		}
        //V3.4.5版本新增加getObjectByKey方法获取本地用户对象中某一列的值
//        String username = (String) BmobUser.getObjectByKey("username");
//        Integer age = (Integer) BmobUser.getObjectByKey("age");
//        Boolean sex = (Boolean) BmobUser.getObjectByKey("sex");
//        JSONArray hobby= (JSONArray) BmobUser.getObjectByKey("hobby");
//        JSONArray cards= (JSONArray) BmobUser.getObjectByKey("cards");
//        JSONObject banker= (JSONObject) BmobUser.getObjectByKey("banker");
//        JSONObject mainCard= (JSONObject) BmobUser.getObjectByKey("mainCard");
//        log("username："+username+",\nage："+age+",\nsex："+ sex);
//        log("hobby:"+(hobby!=null?hobby.toString():"为null")+"\ncards:"+(cards!=null ?cards.toString():"为null"));
//        log("banker:"+(banker!=null?banker.toString():"为null")+"\nmainCard:"+(mainCard!=null ?mainCard.toString():"为null"));
        return false;
    }
}
