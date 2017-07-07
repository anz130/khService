package com.zhy.zhylib.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceUtils {

    private static PreferenceUtils instance;

    private PreferenceUtils() {
    }

    public static synchronized PreferenceUtils getInstance() {
        if (null == instance) {
            instance = new PreferenceUtils();
        }
        return instance;
    }

    public boolean getBoolean(Context context, String name,String key, boolean defValue) {
        SharedPreferences preferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        return preferences.getBoolean(key, defValue);
    }

    public void putBoolean(Context context, String name,String key, boolean value) {
        SharedPreferences preferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        preferences.edit().putBoolean(key, value).commit();
    }

    public int getInt(Context context, String name,String key, int defValue) {
        SharedPreferences preferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        return preferences.getInt(key, defValue);
    }

    public void putInt(Context context, String name,String key, int value) {
        SharedPreferences preferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        preferences.edit().putInt(key, value).commit();
    }

    public long getLong(Context context, String name,String key, long defValue) {
        SharedPreferences preferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        return preferences.getLong(key, defValue);
    }

    public void putLong(Context context, String name,String key, long value) {
        SharedPreferences preferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        preferences.edit().putLong(key, value).commit();
    }

    public float getFloat(Context context, String name,String key, float defValue) {
        SharedPreferences preferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        return preferences.getFloat(key, defValue);
    }

    public void putFloat(Context context, String name,String key, float value) {
        SharedPreferences preferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        preferences.edit().putFloat(key, value).commit();
    }

    public String getString(Context context, String name,String key, String defValue) {
        SharedPreferences preferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        return preferences.getString(key, defValue);
    }

    public void putString(Context context, String name,String key, String value) {
        SharedPreferences preferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        preferences.edit().putString(key, value).commit();
    }
}
