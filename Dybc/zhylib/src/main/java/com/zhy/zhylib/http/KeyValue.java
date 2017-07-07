package com.zhy.zhylib.http;

/**
 * Created by zhangyong
 */
public class KeyValue {

    public String key;

    public String value;

    public KeyValue() {

    }

    public KeyValue(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String toString() {
        return "KeyValue [key=" + key + ", value=" + value + "]";
    }

}
