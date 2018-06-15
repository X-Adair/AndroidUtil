package com.android;

import android.content.Context;
import android.widget.Toast;

/**
 * Toast工具类
 * <p>
 * created at 2018/6/15 14:40
 *
 * @author XuShuai
 * @version v1.0
 */
public class ToastUtils {

    private ToastUtils() {
        /*cannot be instantiated**/
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 短时间显示Toast
     */
    public static void show(Context context, CharSequence message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * 短时间显示Toast
     */
    public static void show(Context context, int resId) {
        Toast.makeText(context, resId, Toast.LENGTH_SHORT).show();
    }

    /**
     * 自定义显示Toast时间
     */
    public static void show(Context context, CharSequence message, int duration) {
        Toast.makeText(context, message, duration).show();
    }

    /**
     * 自定义显示Toast时间
     */
    public static void show(Context context, int resId, int duration) {
        Toast.makeText(context, resId, duration).show();
    }

    /**
     * 长时间显示Toast
     */
    public static void showLong(Context context, CharSequence message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    /**
     * 长时间显示Toast
     */
    public static void showLong(Context context, int resId) {
        Toast.makeText(context, resId, Toast.LENGTH_LONG).show();
    }
}
