package com.android;

import com.android.util.constant.MemoryConstant;

import java.text.DecimalFormat;

/**
 * 文件操作类
 * <p>
 * created at 2018/6/15 14:18
 *
 * @author XuShuai
 * @version v1.0
 */
public class FileUtils {
    /**
     * 文件大小单位转换
     *
     * @param size 文件大小，byte
     * @return 转换后的文件大小，带单位
     */
    public static String getDataSize(long size) {
        if (size < 0) {
            return "size error!";
        }
        DecimalFormat format = new DecimalFormat("#######.##");
        if (size < MemoryConstant.KB) {
            return size + "bytes";
        } else if (size < MemoryConstant.MB) {
            float kbSize = size * 1f / MemoryConstant.KB;
            return format.format(kbSize) + "KB";
        } else if (size < MemoryConstant.GB) {
            float mbSize = size * 1f / MemoryConstant.MB;
            return format.format(mbSize) + "MB";
        } else {
            float gbSize = size * 1f / MemoryConstant.GB;
            return format.format(gbSize) + "GB";
        }
    }
}
