package com.zhy.zhylib.base;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.support.multidex.MultiDex;

import com.zhy.zhylib.cache.DiskCache;
import com.zhy.zhylib.exception.CrashHandler;
import com.zhy.zhylib.utils.Utils;

import java.io.File;

/**
 * Created by zhangyong
 */
public abstract class BaseApplication extends Application {

    private DiskCache cache;
    private static BaseApplication baseApplication;
    // 退出应用广播
    public static final String ACTION_EXIT_APP = "ACTION_EXIT_APP";

    @Override
    public void onCreate() {
        super.onCreate();
        init();
        initCrash();
        initCache();
    }

    private void init(){
        baseApplication = this;
    }

    public static BaseApplication getAppContext() {
        return baseApplication;
    }

    public static String getExitsAction() {
        return baseApplication.getPackageName() + ACTION_EXIT_APP;
    }

    /**
     * debug开启错误日志捕捉
     * 保存到文本
     */
    public void initCrash() {
        //程序未捕获的异常，把异常保存到本地
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(this);
    }

    private void initCache() {
        File root = new File(Utils.getFileRootPath(this));
        cache = new DiskCache(new File(root, getHTTPCacheDir()),
                128 * 1024 * 1024);
        cache.initialize();
    }

    public DiskCache getCache() {
        return cache;
    }

    /**
     * 退出应用
     */
    public void exitApp() {
        getApplicationContext().sendBroadcast(new Intent(getExitsAction()));
        System.exit(0);
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    /**
     * 视频缓存目录
     */
    public abstract String getVideoCacheDir();

    /**
     * 请求缓存目录
     */
    public abstract String getHTTPCacheDir();

    /**
     * 图片缓存目录
     */
    public abstract String getImageCacheDir();

    /**
     * 异常退出日志目录
     */
    public abstract String getCrashFileDir();

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        //解决greendao在android4.4以下分包兼容问题
        MultiDex.install(this);
    }

}
