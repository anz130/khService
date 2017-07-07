package com.zhy.zhylib.http;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;
import com.zhy.http.okhttp.callback.FileCallBack;
import com.zhy.zhylib.base.BaseApplication;
import com.zhy.zhylib.cache.Entry;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by zhangyong
 */
public class OkHttpUtil {

    private final static int CACHE_DURATION = 24 * 60 * 60 * 1000;
    private OkHttpUtil okHttpUtil;

    public OkHttpUtil getInstance() {
        if (okHttpUtil == null) {
            okHttpUtil = new OkHttpUtil();
        }
        return okHttpUtil;
    }

    private static void putCache(String key, String body) {
        Entry en = new Entry();
        en.setBody(body.getBytes());
        en.setDate(System.currentTimeMillis());
        en.setDuration(CACHE_DURATION);
        BaseApplication.getAppContext().getCache().put(key, en);
    }

    private static String getCache(String key) {
        Entry entry = BaseApplication.getAppContext().getCache().get(key);
        if (null != entry && null != entry.getBody()) {
            if (entry.isExpired()) { // 缓存过时
                BaseApplication.getAppContext().getCache().remove(key);
                return null;
            }
            return new String(entry.getBody());
        }
        return null;
    }

    public static void doGetCache(BaseRequest request, final CacheCallback callback) {
        final String url = getParams(request);
        String cache = getCache(url);
        if (null != cache && null != callback) {
            try {
                JsonStrResponse resp = new Gson().fromJson(cache, JsonStrResponse.class);
                callback.onCache(resp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        OkHttpUtils
                .get()
                .url(url)
                .build()
                .connTimeOut(15000)
                .readTimeOut(15000)
                .writeTimeOut(15000)
                .execute(new OkHttpCallBack() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        if (null != callback) {
                            callback.onError(call, e, id);
                        }
                    }

                    @Override
                    public void onResponse(JsonStrResponse response, int id) {
                        if (null != response && response.isSuccess()) {
                            putCache(url, response.jsonResp);
                        }

                        if (null != callback) {
                            callback.onSuc(response, id);
                        }
                    }
                });
    }

    public static void doGet(BaseRequest request, Callback callback) {
        OkHttpUtils
                .get()
                .url(getParams(request))
                .build()
                .connTimeOut(15000)
                .readTimeOut(15000)
                .writeTimeOut(15000)
                .execute(callback);
    }

    public static void doPost(BaseRequest request, Callback callback) {
        Map<String, String> map = getMapParams(request);
        OkHttpUtils
                .post()
                .url(request.url)
                .addHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8")
                .params(map)
                .build()
                .connTimeOut(15000)
                .readTimeOut(15000)
                .writeTimeOut(15000)
                .execute(callback);
    }

    public static void upFiles(String url, String key, Map<String, File> files, Callback callback) {
        OkHttpUtils
                .post()
                .url(url)
                .files(key, files)
                .build()
                .connTimeOut(15000)
                .readTimeOut(15000)
                .writeTimeOut(15000)
                .execute(callback);
    }

    public static void doParamsAndFiles(String url, String key ,Map<String, File> files, Map<String, String> params, Callback callback) {
        OkHttpUtils
                .post()
                .url(url)
                .params(params)
                .files(key, files)
                .build()
                .connTimeOut(15000)
                .readTimeOut(15000)
                .writeTimeOut(15000)
                .execute(callback);
    }

    public static void doFile(File file, BaseRequest request, Callback callback) {
        OkHttpUtils
                .postFile()
                .file(file)
                .url(request.url)
                .addHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8")
                .headers(getMapParams(request))
                .build()
                .connTimeOut(15000)
                .execute(callback);
    }

    public static void getFile(String url, FileCallBack fileCallBack) {
        OkHttpUtils
                .get()
                .url(url)
                .addHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8")
                .build()
                .execute(fileCallBack);
    }

    private static String getParams(BaseRequest request) {
        StringBuffer normalSb = new StringBuffer();
        Class<?> cls = request.getClass();
        Field[] fields = cls.getFields();
        KeyValue keyValue;
        boolean isFirst = true;

        for (Field field : fields) {
            keyValue = new KeyValue();
            keyValue.key = field.getName();
            if (keyValue.key.equals("url")) {
                continue;
            }
            try {
                Object obj = field.get(request);
                if (obj != null) {
                    keyValue.value = obj.toString();
                }
                if(isFirst){
                    isFirst = false;
                }else {
                    normalSb.append("&");
                }
                normalSb.append(keyValue.key + "=" + encodeUTF8(TextUtils.isEmpty(keyValue.value) ? "" : keyValue.value));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        String myUrl = request.url + "?" + normalSb.toString();
        return myUrl;
    }

    private static  Map<String, String> getMapParams(BaseRequest request) {
        Map<String, String> map = new HashMap<>();
        Class<?> cls = request.getClass();
        Field[] fields = cls.getFields();
        KeyValue keyValue;
        for (Field field : fields) {
            keyValue = new KeyValue();
            keyValue.key = field.getName();
            if (keyValue.key.equals("url")) {
                continue;
            }
            try {
                Object obj = field.get(request);
                if (obj != null) {
                    keyValue.value = obj.toString();
                }
                map.put(keyValue.key, encodeUTF8(TextUtils.isEmpty(keyValue.value)?"":keyValue.value));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return map;
    }

    private static String encodeUTF8(String string) {
        try {
            return URLEncoder.encode(string, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    public interface CacheCallback {
        void onCache(JsonStrResponse response);

        void onSuc(JsonStrResponse response, int id);

        void onError(Call call, Exception e, int id);
    }
}
