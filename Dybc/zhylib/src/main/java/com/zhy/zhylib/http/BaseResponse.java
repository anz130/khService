package com.zhy.zhylib.http;

/**
 * Created by zhangyong
 */
public class BaseResponse implements IResponse {

    private static final long serialVersionUID = 1L;
    public String json;

    @Override
    public String toString() {
        return "BaseResponse [ json=" + json + "]";
    }
}