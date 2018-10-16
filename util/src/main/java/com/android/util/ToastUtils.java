package com.android.util;

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
     * created at 2018/6/15 17:30
     *
     * @param context 上下文对象
     * @param message 信息
     */
    public static void show(Context context, CharSequence message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * 短时间显示Toast
     * <p>
     * created at 2018/6/15 17:31
     *
     * @param context 上下文对象
     * @param resId   字符串id
     */
    public static void show(Context context, int resId) {
        Toast.makeText(context, resId, Toast.LENGTH_SHORT).show();
    }

    /**
     * 自定义显示Toast时间
     * <p>
     * created at 2018/6/15 17:31
     *
     * @param context  上下文对象
     * @param message  显示信息
     * @param duration 显示时长
     */
    public static void show(Context context, CharSequence message, int duration) {
        Toast.makeText(context, message, duration).show();
    }

    /**
     * 自定义显示Toast时间
     * <p>
     * created at 2018/6/15 17:31
     *
     * @param context  上下文对象
     * @param resId    显示信息id
     * @param duration 显示时长
     */
    public static void show(Context context, int resId, int duration) {
        Toast.makeText(context, resId, duration).show();
    }

    /**
     * 长时间显示Toast
     * <p>
     * created at 2018/6/15 17:32
     *
     * @param context 上下文对象
     * @param message 显示信息
     */
    public static void showLong(Context context, CharSequence message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    /**
     * 长时间显示Toast
     * <p>
     * created at 2018/6/15 17:32
     *
     * @param context 上下文对象
     * @param resId   显示信息id
     */
    public static void showLong(Context context, int resId) {
        Toast.makeText(context, resId, Toast.LENGTH_LONG).show();
    }
}
