package com.adair.util;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.content.FileProvider;
import android.support.v4.content.pm.PackageInfoCompat;

import java.io.File;
import java.util.List;

/**
 * App操作类
 * <p>
 * created at 2018/6/15 13:57
 *
 * @author XuShuai
 * @version v1.0
 */
public class AppUtils {

    /**
     * cannot be instantiated
     */
    private AppUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * <p>
     * created at 2018/6/15 17:23
     *
     * @param context 上下文对象
     * @return java.lang.String App名称
     */
    public static String getAppName(Context context) {
        String appName = null;
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            int labelRes = packageInfo.applicationInfo.labelRes;
            appName = context.getResources().getString(labelRes);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return appName;
    }

    /**
     * 获取应用程序版本名称信息
     * <p>
     * created at 2018/6/15 17:23
     *
     * @param context 上下文对象
     * @return java.lang.String App当前版本名称
     */
    public static String getVersionName(Context context) {
        String versionName = null;
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            versionName = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }

    /**
     * 获取App版本号
     * <p>
     * created at 2018/6/15 17:24
     *
     * @param context 上下文对象
     * @return int App当前版本号
     */
    public static long getVersionCode(Context context) {
        long versionCode = -1;
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            versionCode = PackageInfoCompat.getLongVersionCode(packageInfo);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    /**
     * 安装apk,用于更新时下载Apk并安装
     * <p>
     * created at 2018/6/15 17:24
     *
     * @param context   上下文对象
     * @param path      Apk文件路径
     * @param authority 授权文件名称
     */
    public static void installApk(Context context, String path, String authority) {
        File file = new File(path);
        Intent intent = new Intent();
        // 执行动作
        Uri data;
        intent.setAction(Intent.ACTION_VIEW);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            // "com.xxx.fileProvider"即是在清单文件中配置的authorities
            data = FileProvider.getUriForFile(context, authority, file);
            // 给目标应用一个临时授权
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        } else {
            data = Uri.fromFile(file);
        }
        // 执行的数据类型
        intent.setDataAndType(data, "application/vnd.android.package-archive");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    /**
     * 跳转到系统应用设置页面
     *
     * @param activity    activity
     * @param requestCode 请求码
     */
    public static void go2Setting(Activity activity, int requestCode) {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", activity.getPackageName(), null);
        intent.setData(uri);
        activity.startActivityForResult(intent, requestCode);
    }

    /**
     * 打开网络设置界面
     *
     * @param activity activity
     */
    public static void openNetworkSetting(Activity activity) {
        Intent intent = new Intent();
        if (Build.VERSION.SDK_INT > 10) {
            //跳转到设置界面
            intent.setAction(Settings.ACTION_SETTINGS);
        } else {
            //现在会跳转到移动网络设置界面
            ComponentName componentName = new ComponentName("com.android.settting", "com.android.setting.WirelessSettings");
            intent.setComponent(componentName);
            intent.setAction("android.intent.action.View");
        }
        activity.startActivity(intent);
    }

    /**
     * 判断当前app是否在后台运行
     *
     * @param context 上下文对象
     * @return true 在后台运行，false不在后台运行
     */
    public static boolean isRunBackground(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        if (activityManager != null) {
            List<RunningAppProcessInfo> appProcessInfoList = activityManager.getRunningAppProcesses();
            String packageName = context.getPackageName();
            for (RunningAppProcessInfo appProcessInfo : appProcessInfoList) {
                if (appProcessInfo.processName.equals(packageName)) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        return appProcessInfo.importance == RunningAppProcessInfo.IMPORTANCE_CACHED;
                    } else {
                        return appProcessInfo.importance == RunningAppProcessInfo.IMPORTANCE_BACKGROUND;
                    }
                }
            }
        }
        return false;
    }

    /**
     * 判断当前app是否在前台运行
     *
     * @param context 上下文对象
     * @return true 在前台运行，false不在前台运行
     */
    public static boolean isRunForeground(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        if (activityManager != null) {
            List<RunningAppProcessInfo> appProcessInfoList = activityManager.getRunningAppProcesses();
            String packageName = context.getPackageName();
            for (RunningAppProcessInfo appProcessInfo : appProcessInfoList) {
                if (appProcessInfo.processName.equals(packageName)) {
                    return appProcessInfo.importance == RunningAppProcessInfo.IMPORTANCE_FOREGROUND;
                }
            }
        }
        return false;
    }
}
