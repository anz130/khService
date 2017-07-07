package com.zhy.zhylib.http;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

/**
 * Created by zhangyong
 */
public class JsonObjResponse<I extends IJsonObj> extends BaseResponse {

    private static final long serialVersionUID = 1L;
    /**
     * json状态码
     */
    public int code;
    /**
     * 提示信息
     */
    public String msg;
    /**
     * json data
     */
    @SerializedName("data")
    public I jsonObj;

    public boolean isSuccess() {
        return 0 == code;
    }

    public String toString() {
        return "JsonObjResponse [code=" + code + ", jsonObj=" + jsonObj + ", msg=" + msg + "]";
    }

    /**
     * @return
     * @ReqParam cls
     */
    public static <T extends IJsonObj> JsonObjResponse<T> newInstance(Class<T> cls, JsonStrResponse response) {
        JsonObjResponse<T> jsonResponse = new JsonObjResponse<T>();
        jsonResponse.json = response.json;
        jsonResponse.code = response.code;
        jsonResponse.msg = response.msg;

        jsonResponse.json = response.json;
        try {
            jsonResponse.jsonObj = new Gson().fromJson(response.getJsonString(), cls);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonResponse;
    }
}