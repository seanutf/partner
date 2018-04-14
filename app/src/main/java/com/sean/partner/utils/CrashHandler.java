package com.sean.partner.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.os.Process;

import com.sean.partner.BuildConfig;
import com.sean.partner.utils.file.FileUtils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Sean on 2018/4/13.
 */

public class CrashHandler implements Thread.UncaughtExceptionHandler {

    private static final String TAG = CrashHandler.class.getSimpleName();
    private Thread.UncaughtExceptionHandler mDefaultCrashHandler;
    private Context context;

    private CrashHandler() {
    }

    /**
     * 静态内部类
     */
    private static class CrashHandlerHolder {
        private static final CrashHandler instance = new CrashHandler();
    }

    public static CrashHandler getInstance() {
        return CrashHandlerHolder.instance;
    }

    public void init(Context context) {
        this.context = context.getApplicationContext();
        mDefaultCrashHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }


    @Override
    public void uncaughtException(Thread t, Throwable exception) {
        try {
            //导出异常信息到SD卡中
            dumpExceptionToSDCard(exception);
            uploadExceptionToServer();
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        exception.printStackTrace();
        //如果系统提供了默认的异常处理器，则教给系统去结束程序，否则就自己结束自己。
        if (mDefaultCrashHandler != null) {
            mDefaultCrashHandler.uncaughtException(t, exception);
        } else {
            Process.killProcess(Process.myPid());
            System.exit(0);
        }
    }

    private void uploadExceptionToServer() {
        //todo 上传应用crash信息到服务器
    }

    private void dumpExceptionToSDCard(Throwable exception) throws IOException {
        //如果SD卡不存在或无法使用，则无法把异常信息写入SD卡
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            if (BuildConfig.DEBUG) {
                LogUtil.w(TAG, "sdcard unmounted, skip dump exception");
                return;
            }
        }
        long currentTime = System.currentTimeMillis();
        String timeStr = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date(currentTime));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(FileUtils.getCrashFile(context, timeStr)));
        try {
            PrintWriter printWriter = new PrintWriter(bufferedWriter);
            printWriter.println(timeStr);
            dumpPhoneInfo(printWriter);
            printWriter.println();
            exception.printStackTrace(printWriter);
            printWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.e(TAG, "dump crash info failed");
        }
    }

    private void dumpPhoneInfo(PrintWriter printWriter) throws PackageManager.NameNotFoundException {
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), PackageManager.GET_ACTIVITIES);

        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();

        //application版本号
        printWriter.print("App Version : ");
        printWriter.print(packageInfo.versionName);
        printWriter.print('_');
        printWriter.println(packageInfo.versionCode);

        //android版本号
        printWriter.print("OS Version : ");
        printWriter.print(Build.VERSION.RELEASE);
        printWriter.print('_');
        printWriter.println(Build.VERSION.SDK_INT);

        //phone厂商
        printWriter.print("Vendor : ");
        printWriter.println(Build.MANUFACTURER);

        //phone型号
        printWriter.print("Model : ");
        printWriter.println(Build.MODEL);

        //cpu型号
        printWriter.print("CPU ABI: ");
        printWriter.println(Build.CPU_ABI);

        //Memory当时剩余总运行内存
        am.getMemoryInfo(memoryInfo);
        printWriter.print("Memory: ");
        printWriter.println(memoryInfo.totalMem/1024/1024 + "M");//需要API16以上
    }
}
