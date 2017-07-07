package com.zhy.zhylib.utils;

import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * JSON 工具类
 */
public class JSONUtils {

    public static Object toObject(String json, Class<?> clazz) {
        Object msg = null;
        try {
            Gson gson = new Gson();
            msg = gson.fromJson(json, clazz);
        } catch (Exception e) {
            Log.e("JSONUtils", "JSON Exception:" + e.toString());
        }

        return msg;
    }

    public static String toJson(Object obj) {
        Gson gson = new Gson();
        String result = null;
        try {
            result = gson.toJson(obj);
        } catch (Exception e) {
            Log.e("JSONUtils", "JSON Exception:" + e.toString());
        }
        return result;
    }

    public static int getInt(JSONObject obj, String key) {
        if (obj.has(key)) {
            return obj.optInt(key);
        }
        return 0;
    }

    public static String getString(JSONObject obj, String key) {
        if (obj.has(key)) {
            return obj.optString(key);
        }
        return null;
    }

    public static long getLong(JSONObject obj, String key) {
        if (obj.has(key)) {
            return obj.optLong(key);
        }
        return 0;
    }

    public static boolean getBoolean(JSONObject obj, String key) {
        if (obj.has(key)) {
            return obj.optBoolean(key);
        }
        return false;
    }

    public static JSONObject getObj(JSONObject obj, String key) throws Exception {
        if (obj.has(key)) {
            return obj.optJSONObject(key);
        }
        return null;
    }

    public static JSONArray getArray(JSONObject obj, String key) throws Exception {
        if (obj.has(key)) {
            return obj.optJSONArray(key);
        }
        return null;
    }
}
