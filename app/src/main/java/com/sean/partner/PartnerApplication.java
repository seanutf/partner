package com.sean.partner;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

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
}
