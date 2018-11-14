package com.android.util;

import android.content.Context;
import android.os.Environment;
import android.support.annotation.RequiresPermission;

import java.io.File;

/**
 * 获取应用需要的各种文件路径
 * <p>
 * created at 2018/6/15 14:13
 *
 * @author XuShuai
 * @version v1.0
 */
public class FileDirectoryUtils {

    private FileDirectoryUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 应用私有存储目录
     * 获取内置存储下的文件目录，可以用来保存不能公开给其他应用的一些敏感数据如用户个人信息
     * 详细路径为：/data/data/应用包名/files/
     * 此路径不需要申请权限
     * 是Android 手机内置存储，如果手机没有获得root权限的话文件浏览器是无法访问的。
     * 这种存储会随之app被删除而被删除。
     *
     * @param context 上下文对象
     * @return 路径文件
     */
    public static File getFilesDir(Context context) {
        return context.getFilesDir();
    }

    /**
     * 应用私有存储目录
     * 获取内置存储下的缓存目录，可以用来保存一些缓存文件如图片，当内置存储的空间不足时将系统自动被清除
     * 详细路径为：/data/data/应用包名/cache/
     * 此路径不需要申请权限
     * 是Android 手机内置存储，如果手机没有获得root权限的话文件浏览器是无法访问的。
     * 这种存储会随之app被删除而被删除。
     *
     * @param context 上下文对象
     * @return 路径文件
     */
    public static File getCacheDir(Context context) {
        return context.getCacheDir();
    }

    /**
     * 获取应用数据库文件
     * 详细路径： /data/data/应用包名/databases/xxx.db
     *
     * @param context      上下文对象
     * @param databaseName 数据库名称
     * @return 数据库文件
     */
    public static File getDatabasePath(Context context, String databaseName) {
        return context.getDatabasePath(databaseName);
    }

    /**
     * 判断外置sd卡能否使用
     *
     * @return true为可用，false为不可用
     */
    public static boolean getExternalStorageState() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) ||
                !Environment.isExternalStorageRemovable();
    }

    /**
     * 获取SD卡上的文件目录,可以保存一些长时间保存文件
     * 详细路径为：SDCard/Android/data/应用包名/files/type/
     * 存储在sdcard上所以尽量不要存在敏感数据比如用户信息等。
     * 这里的文件也会随着app 被删除而被删除。
     * 文件对应 设置-应用-应用详情里面的“清除数据”选项
     * 该目录需要申请权限：
     * {@link android.Manifest.permission#WRITE_EXTERNAL_STORAGE}
     * {@link android.Manifest.permission#READ_EXTERNAL_STORAGE}
     *
     * @param context 上下文对象
     * @param type    子文件夹
     *                type可以为自定义文件夹名，这时如果APP被uninstall，则文件会被删除。
     *                type也可以为一下值：
     *                {@link Environment#DIRECTORY_MUSIC},
     *                {@link Environment#DIRECTORY_PODCASTS},
     *                {@link Environment#DIRECTORY_RINGTONES},
     *                {@link Environment#DIRECTORY_ALARMS},
     *                {@link Environment#DIRECTORY_NOTIFICATIONS},
     *                {@link Environment#DIRECTORY_PICTURES}, or
     *                {@link Environment#DIRECTORY_MOVIES}.
     * @return 目标文件
     */
    @RequiresPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
    public static File getExternalFilesDir(Context context, String type) {
        if (getExternalStorageState()) {
            return context.getExternalFilesDir(type);
        }
        return null;
    }

    /**
     * 获取SD卡上的缓存目录，可以用来保存一些缓存文件如图片
     * 详细路径为：SDCard/Android/data/应用包名/cache/
     * 存储在sdcard上所以尽量不要存在敏感数据比如用户信息等。
     * 这里的文件也会随着app 被删除而被删除。
     * 文件对应 设置-应用-应用详情里面的“清除缓存”选项。
     * 该目录需要申请权限：
     * {@link android.Manifest.permission#WRITE_EXTERNAL_STORAGE}
     * {@link android.Manifest.permission#READ_EXTERNAL_STORAGE}
     *
     * @param context 上下文对象
     * @return 目标文件
     */
    @RequiresPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
    public static File getExternalCacheDir(Context context) {
        if (getExternalStorageState()) {
            return context.getExternalCacheDir();
        }
        return null;
    }

    /**
     * 获取SD卡根目录
     * 详细路径为：SDCard/
     * 需要存储一些公共文件,这些文件能够不随着App被删除而被删除，例如我们录制的视频或者下载的音乐等。
     * 这个目录可以被任何app访问，我们在使用的时候是需要申请权限的。
     * 该目录需要申请权限：
     * {@link android.Manifest.permission#WRITE_EXTERNAL_STORAGE}
     * {@link android.Manifest.permission#READ_EXTERNAL_STORAGE}
     *
     * @return 目标文件
     */
    @RequiresPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
    public static File getExternalStorageDirectory() {
        if (getExternalStorageState()) {
            return Environment.getExternalStorageDirectory();
        }
        return null;
    }

    /**
     * 获取SD卡下一些公共目录，如Picture,Movies。Music...
     * 详细目录为：SDCard/type
     * 这些文件不会随APP uninstall而被删除，会被永久(除非手动删除)保存在SD上
     * 这个目录可以被任何app访问，我们在使用的时候是需要申请权限的。
     * 该目录需要申请权限：
     * {@link android.Manifest.permission#WRITE_EXTERNAL_STORAGE}
     * {@link android.Manifest.permission#READ_EXTERNAL_STORAGE}
     *
     * @param type The type of storage directory to return. Should be one of
     *             {@link Environment#DIRECTORY_MUSIC},音乐存放的标准目录。
     *             {@link Environment#DIRECTORY_PODCASTS},系统广播存放的标准目录。
     *             {@link Environment#DIRECTORY_RINGTONES},系统铃声存放的标准目录
     *             {@link Environment#DIRECTORY_ALARMS}, 系统提醒铃声存放的标准目录
     *             {@link Environment#DIRECTORY_NOTIFICATIONS},系统通知铃声存放的标准目录
     *             {@link Environment#DIRECTORY_PICTURES},图片存放的标准目录
     *             {@link Environment#DIRECTORY_MOVIES},电影存放的标准目录
     *             {@link Environment#DIRECTORY_DOWNLOADS},下载的标准目录
     *             {@link Environment#DIRECTORY_DCIM},or 相机拍摄照片和视频的标准目录
     *             {@link Environment#DIRECTORY_DOCUMENTS}. 文档标准目录
     *             May not be null.
     * @return 目标文件 可能为空
     */
    @RequiresPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
    public static File getExternalStoragePublicDirectory(String type) {
        if (getExternalStorageState()) {
            return Environment.getExternalStoragePublicDirectory(type);
        }
        return null;
    }


    /**
     * 获取手机缓存文件夹路径,如果SD可用，则获取SD路径，否则获取手机路径
     *
     * @param context    上下文对象
     * @param uniqueName 文件夹名称
     */
    public static File getDiskCacheDir(Context context, String uniqueName) {
        String cachePath;
        if (getExternalStorageState()) {
            File file = context.getExternalCacheDir();
            if (file != null) {
                cachePath = file.getAbsolutePath();
            } else {
                cachePath = context.getCacheDir().getAbsolutePath();
            }
        } else {
            cachePath = context.getCacheDir().getAbsolutePath();
        }
        return new File(cachePath + File.separator + uniqueName);
    }
}
