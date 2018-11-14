package com.adair.util;

import java.util.UUID;

/**
 * UUID工具类
 * <p>
 * created at 2018/6/15 14:41
 *
 * @author XuShuai
 * @version v1.0
 */
public class UUIDUtils {
    /**
     * 获得一个UUID
     *
     * @return String UUID
     */
    public static String getUUID() {
        String s = UUID.randomUUID().toString();
        return s.replace("-", "");
    }

    /**
     * 获得指定数目的UUID
     *
     * @param number int 需要获得的UUID的长度
     * @return String[] UUID数组
     */
    public static String[] getUUID(int number) {
        if (number < 1) {
            return null;
        }
        String[] ss = new String[number];
        for (int i = 0; i < number; i++) {
            ss[i] = getUUID();
        }
        return ss;
    }
}
