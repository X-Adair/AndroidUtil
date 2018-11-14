package com.adair.util;

import android.content.Context;
import android.media.MediaScannerConnection;
import android.net.Uri;

/**
 * 让系统扫描文件,使其将多媒体文件写入数据库
 * <p>
 * created at 2018/8/30 10:47
 *
 * @author XuShuai
 * @version v1.0
 */
public class MediaScanner implements MediaScannerConnection.MediaScannerConnectionClient {

    private MediaScannerConnection mMediaScanConn = null;

    /**
     * 文件路径集合
     **/
    private String[] filePaths;
    /**
     * 文件MimeType集合
     **/
    private String[] mimeTypes;
    /**
     * 扫描计数,扫描完成结束扫描
     */
    private int scanTimes = 0;

    public MediaScanner(Context context) {
        mMediaScanConn = new MediaScannerConnection(context.getApplicationContext(), this);
    }

    /**
     * 扫描文件
     *
     * @param filePaths 文件路径
     * @param mimeTypes 文件MimeType
     */
    public void scanFiles(String[] filePaths, String[] mimeTypes) {
        this.filePaths = filePaths;
        this.mimeTypes = mimeTypes;
        mMediaScanConn.connect();
    }

    @Override
    public void onMediaScannerConnected() {
        for (int i = 0; i < filePaths.length; i++) {
            mMediaScanConn.scanFile(filePaths[i], mimeTypes[i]);//服务回调执行扫描
        }
    }

    @Override
    public void onScanCompleted(String path, Uri uri) {
        scanTimes++;
        if (scanTimes == filePaths.length) {//如果扫描完了全部文件
            mMediaScanConn.disconnect();//断开扫描服务
            scanTimes = 0;//复位计数
            filePaths = null;
            mimeTypes = null;
        }
    }
}
