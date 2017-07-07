package com.dyb.dybc;

import android.graphics.Typeface;

import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.dyb.dybc.bean.UserInfo;
import com.dyb.dybc.config.Config;
import com.dyb.dybc.greendao.BaseDao;
import com.dyb.dybc.greendao.GreenDaoManager;
import com.zhy.zhylib.base.BaseApplication;

import java.lang.reflect.Field;

/**
 * Created by zhangyong on 2017/3/4.
 */

public class MyApplication extends BaseApplication {
    private static UserInfo userInfo;

    @Override
    public void onCreate() {
        super.onCreate();
        initDataBase();
        initTypeFace();
        initCrash();
        initLocation();
        //        initShare();
    }

    private void initDataBase() {
        GreenDaoManager.getInstance().setDebug(true);
    }

    public static UserInfo getUser() {
        if (null == userInfo || null == userInfo.getUid() || null == userInfo.getLoginName()) {
            userInfo = (UserInfo) BaseDao.getInstance().QueryById(0, UserInfo.class);
            if(null == userInfo){
                userInfo = new UserInfo();
            }
        }
        return userInfo;
    }

    public static void setUser(UserInfo user) {
        userInfo = user;
    }

    private void initLocation(){
        SDKInitializer.initialize(this);
        SDKInitializer.setCoordType(CoordType.BD09LL);
    }

//    private void initShare() {
//        UMShareAPI.get(this);
//        Config.DEBUG = true;
//        PlatformConfig.setWeixin("dggd", "dgdg");
//        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
//        PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad", "http://sns.whalecloud.com");
//    }

    /**
     * 设置整个项目的字体样式
     */
    private void initTypeFace() {
        Typeface typeFace = Typeface.createFromAsset(getAssets(), "fonts/robot_normal.ttf");
        try {
            Field field = Typeface.class.getDeclaredField("SANS_SERIF");
            field.setAccessible(true);
            field.set(null, typeFace);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getVideoCacheDir() {
        return Config.DIR_CACHE_VIDEO;
    }

    @Override
    public String getImageCacheDir() {
        return Config.DIR_CACHE_IMAGE;
    }

    @Override
    public String getCrashFileDir() {
        return Config.DIR_CRASH_LOG;
    }

    @Override
    public String getHTTPCacheDir() {
        return Config.DIR_CACHE_HTTP;
    }
}
