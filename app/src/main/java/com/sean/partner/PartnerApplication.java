package com.sean.partner;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.widget.Toast;

import com.sean.partner.utils.KeyUtils;
import com.sean.partner.utils.LogUtil;
import com.sean.partner.utils.UserConfigures;
import com.sean.partner.global.meta.User;
import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;

import cn.bmob.v3.BmobUser;

import static com.umeng.commonsdk.UMConfigure.DEVICE_TYPE_PHONE;

/**
 * Created by sean on 2016/9/9.
 */
public class PartnerApplication  extends Application{
    UserConfigures userConfigures;
    public static PartnerApplication instance;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        LogUtil.initCrashHandler(this);
        initThirdPartySDK();
    }

    private void initThirdPartySDK() {
        initStatistics();
    }

    private void initStatistics() {
        UMConfigure.init(this, KeyUtils.UMENG_APP_KEY, KeyUtils.APP_CHANNEL_TYPE_GOOGLE, DEVICE_TYPE_PHONE, null);
        MobclickAgent.setScenarioType(instance, MobclickAgent.EScenarioType.E_UM_NORMAL);// 场景类型设置
        UMConfigure.setLogEnabled(BuildConfig.DEBUG);// 设置开启日志,发布时请关闭日志
    }

    public UserConfigures getUserConfigures(){
        if(userConfigures == null)
            userConfigures = new UserConfigures(this);
        return userConfigures;
    }

    public String getVersion() {
          try {
               PackageManager manager = this.getPackageManager();
               PackageInfo info = manager.getPackageInfo(this.getPackageName(), 0);
               String version = info.versionName;
               return "" + version;
          } catch (Exception e) {
           e.printStackTrace();
           return "";
        }
    }

    /**
     * 获取当前本地用户
     */
    public boolean getCurrentUser() {
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
