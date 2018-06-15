package com.android.util.constant;

import android.support.annotation.IntDef;

import com.android.MathUtils;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 内存换算中的各种单位
 * <p>
 * created at 2018/6/15 10:25
 *
 * @author XuShuai
 * @version v1.0
 */
public class MemoryConstant {

    /**
     * byte与byte的换算关系,1byte = 1byte
     */
    public static final int BYTE = 1;

    /**
     * KB与byte的换算关系,1KB = 1024byte
     */
    public static final int KB = 1024;

    /**
     * MB与byte的换算关系,1MB = 1048576byte
     */
    public static final int MB = 1048576;

    /**
     * GB与byte的换算关系,1GB = 1073741824 byte
     */
    public static final int GB = 1073741824;

    /**
     * 单位枚举
     */
    @IntDef({BYTE, KB, MB, GB})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Unit {

    }

    /**
     * 单位换算,返回带单位的字符串，保留2位小数点
     * <p>
     * created at 2018/6/15 10:59
     *
     * @param byteLong 文件长度
     * @return java.lang.String
     */
    public static String convert(long byteLong) {
        if (byteLong < KB) {
            return byteLong + "B";
        } else if (byteLong < MB) {
            return MathUtils.div(byteLong, KB, 2) + "KB";
        } else if (byteLong < GB) {
            return MathUtils.div(byteLong, MB, 2) + "MB";
        } else {
            return MathUtils.div(byteLong, GB, 2) + "GB";
        }
    }

    /**
     * 单位换算,返回带单位的字符串，保留2位小数点,定义换算成的单位
     * <p>
     * created at 2018/6/15 11:11
     *
     * @param byteLong 文件长度
     * @param unit     换算后的单位
     * @return java.lang.String
     */
    public static String convert(long byteLong, @Unit int unit) {
        switch (unit) {
            case BYTE:
                return byteLong + "B";
            case KB:
                return MathUtils.div(byteLong, KB) + "KB";
            case MB:
                return MathUtils.div(byteLong, MB) / 100f + "MB";
            case GB:
                return MathUtils.div(byteLong, GB) + "GB";
            default:
                return byteLong + "B";
        }

    }
}
