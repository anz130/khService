package com.dyb.dybc.config;

/**
 * 配置类
 */

public class Config {

    //activity跳转返回
    public static final int LOGIN_REGIST = 10001;
    public static final int SEARCH_CITY = 10002;



    //图片缓存地址
    public static final String DIR_CACHE_IMAGE = "/dyb/image/";
    //程序崩溃log存储地址
    public static final String DIR_CRASH_LOG = "/dyb/crash/";
    //网络请求缓存
    public static final String DIR_CACHE_HTTP = "/dyb/http/";
    //视频缓存目录
    public static final String DIR_CACHE_VIDEO = "/dyb/video/";


    //权限code
    public static int ACCESS_COARSE_LOCATION_REQUEST_CODE = 1001;
    public static int ACCESS_FINE_LOCATION_REQUEST_CODE = 1002;
    public static int WRITE_EXTERNAL_STORAGE_REQUEST_CODE = 1003;
    public static int READ_EXTERNAL_STORAGE_REQUEST_CODE = 1004;
    public static int READ_PHONE_STATE_REQUEST_CODE = 1005;
    public static int CALL_PHONE = 1006;
    public static int CAMERA = 1007;
    public static int LOCATION = 1008;



}
