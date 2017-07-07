package com.zhy.zhylib.http;

import com.google.gson.JsonElement;

/**
 * Created by zhangyong
 */
public class JsonStrResponse<I extends IJsonObj> extends BaseResponse {

    private static final long serialVersionUID = 1L;

    /**
     * json状态码
     */
    public int code;

    /**
     * msg
     */
    public String msg;

    /**
     * json data
     */
    public JsonElement data;

    String jsonStr;

    // 原始JSON数据
    public String jsonResp;

    public void setJsonStr(String jsonStr) {
        this.jsonStr = jsonStr;
    }

    public boolean isSuccess() {
        return OkHttpConstant.RESP_SUCESS_CODE == code;
    }

    public boolean isFail() {
        return OkHttpConstant.RESP_FAIL_CODE == code;
    }

    public boolean isOutTime() {
        return OkHttpConstant.RESP_OutTime_CODE == code;
    }

    public String getJsonString() {
        if (jsonStr == null && data != null) {
            jsonStr = data.toString();
        }
        return jsonStr;
    }

    @Override
    public String toString() {
        return "{" +
                "data=" + data +
                ", msg='" + msg + '\'' +
                ", code=" + code +
                '}';
    }
}