package com.android;

import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * SharePreference工具类
 * <p>
 * created at 2018/6/15 14:43
 *
 * @author XuShuai
 * @version v1.0
 */
public class SPUtils {

    private static final String NAME = "share_data";

    /**
     * 打开SharedPreferences Editor
     * <p>
     * created at 2018/6/15 17:27
     *
     * @param context 上下文对象
     * @return android.content.SharedPreferences.Editor
     */
    public static SharedPreferences.Editor OpenEditor(Context context) {
        SharedPreferences sp = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        return sp.edit();
    }

    /**
     * 打开SharedPreferences
     * <p>
     * created at 2018/6/15 17:27
     *
     * @param context 上下文对象
     * @return android.content.SharedPreferences
     */
    public static SharedPreferences Open(Context context) {
        return context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
    }

    /**
     * sharePreference 不支持存储double,要存储double，可以将double转为Json(可以不失去精度)存储
     *
     * @param context 上下文对象
     * @param key     key
     * @param value   存储目标数据
     */
    public static void putDouble(Context context, String key, double value) {
        SharedPreferences sp = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        JSONObject object = new JSONObject();
        try {
            object.put("double", value);
            sp.edit().putString(key, object.toString()).apply();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * sharePreference 不支持存储double,要存储double，可以将double转为Json(可以不失去精度)存储
     * <p>
     * created at 2018/6/15 15:03
     *
     * @param context 上下文对象
     * @param key     key
     * @param value   数据
     * @return boolean
     */
    public static boolean putDoubleWithResult(Context context, String key, double value) {
        SharedPreferences sp = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        JSONObject object = new JSONObject();
        try {
            object.put("double", value);
            return sp.edit().putString(key, object.toString()).commit();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 取出存储的double数据
     * <p>
     * created at 2018/6/15 15:04
     *
     * @param context      上下文对象
     * @param key          key
     * @param defaultValue 如果没有值返回默认值
     * @return double
     */
    public static double getDouble(Context context, String key, double defaultValue) {
        SharedPreferences sp = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        String jsonString = sp.getString(key, "");
        double value;
        if (StringUtils.isEmpty(jsonString)) {
            value = defaultValue;
        } else {
            try {
                JSONObject object = new JSONObject(jsonString);
                value = object.getDouble("double");
            } catch (JSONException e) {
                value = defaultValue;
            }
        }
        return value;
    }
}


