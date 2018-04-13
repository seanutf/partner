package com.sean.partner.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.util.Log;

import com.sean.partner.PartnerApplication;
import com.sean.partner.utils.file.FileUtils;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CrashHandler implements UncaughtExceptionHandler {  
    
    public static final String TAG = CrashHandler.class.getName();  
      
    //系统默认的UncaughtException处理类   
    private UncaughtExceptionHandler mDefaultHandler;
    
    //CrashHandler实例  
    private static CrashHandler instance = null;
    
    //程序的Context对象  
    private Context mContext;
    
    //用来存储设备信息和异常信息  
    private Map<String, String> infos = new HashMap<String, String>();  
  
    //用于格式化日期,作为日志文件名的一部分  
    private DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");  
  
    private CrashHandler(Context context) {
        mContext = context;  
        //获取系统默认的UncaughtException处理器  
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();  
    }  
  
    public static CrashHandler getInstance(Context context) {
        if(instance == null)
        	instance = new CrashHandler(context);
        return instance;
    }  
  
    /** 
     * 当UncaughtException发生时会转入该函数来处理 
     */  
    @Override  
    public void uncaughtException(Thread thread, Throwable ex) {
        //todo 以后根据情况来判断是否处理debug模式
//        if (BuildConfig.DEBUG) {
//            //收集设备参数信息
//            collectDeviceInfo(mContext);
//
//            //保存日志文件
//            saveCrashInfo2File(ex);
//
//        }
            //收集设备参数信息
            collectDeviceInfo(mContext);
            //保存日志文件
            saveCrashInfo2File(ex);
//        mDefaultHandler.uncaughtException(thread, ex);
        //退出程序
        android.os.Process.killProcess(android.os.Process.myPid());
//        if (ex != null){
//        }else {
//            android.os.Process.killProcess(android.os.Process.myPid());
//        }
        //继续用原来的Handler处理
//        mDefaultHandler.uncaughtException(thread, ex);
        
//            //退出程序  
//            android.os.Process.killProcess(android.os.Process.myPid());  
//            System.exit(1);  
    }  
  
    /** 
     * 收集设备参数信息 
     * @param ctx 
     */  
    public void collectDeviceInfo(Context ctx) {
        try {  
            PackageManager pm = ctx.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(ctx.getPackageName(), PackageManager.GET_ACTIVITIES);
            if (pi != null) {  
                String versionName = pi.versionName == null ? "null" : pi.versionName;  
                String versionCode = pi.versionCode + "";  
                infos.put("versionName", versionName);  
                infos.put("versionCode", versionCode);  
            }  
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }  
        
        Field[] fields = Build.class.getDeclaredFields();
        for (Field field : fields) {  
            try {  
                field.setAccessible(true);  
                infos.put(field.getName(), field.get(null).toString());  
            } catch (Exception e) {  
                e.printStackTrace();
            }  
        }  
    }  
  
    /** 
     * 保存错误信息到文件中 
     *  
     * @param ex 
     * @return  返回文件名称,便于将文件传送到服务器 
     */  
    private String saveCrashInfo2File(Throwable ex) {  
          
        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, String> entry : infos.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            sb.append(key + "=" + value + "\n");
        }

        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
    	ex.printStackTrace(printWriter);
    	printWriter.flush();

        printWriter.close();

        sb.append(writer.toString());
        try {
            long timestamp = System.currentTimeMillis();
            String time = formatter.format(new Date());
            String fileName = "crash-" + time + "-" + timestamp + ".txt";
            File crashFile = new File(FileUtils.getAppCacheDir(PartnerApplication.instance),fileName);
            if(!crashFile.exists())
            	crashFile.createNewFile();

            FileUtils.writeStringToFile(sb.toString(), crashFile);

            return fileName;
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return null;  
    }
}  
