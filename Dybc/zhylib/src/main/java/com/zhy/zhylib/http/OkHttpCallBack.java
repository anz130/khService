package com.zhy.zhylib.http;

import com.google.gson.Gson;
import com.zhy.http.okhttp.callback.Callback;

import okhttp3.Response;

/**
 * Created by zhangyong
 */
public abstract class OkHttpCallBack extends Callback<JsonStrResponse> {

    @Override
    public JsonStrResponse parseNetworkResponse(Response response, int id) throws Exception {
        JsonStrResponse jsonStrResponse = null;
        try {
            String json = response.body().string();
            jsonStrResponse = new Gson().fromJson(json, JsonStrResponse.class);
            jsonStrResponse.jsonResp = json;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonStrResponse;
    }

}
