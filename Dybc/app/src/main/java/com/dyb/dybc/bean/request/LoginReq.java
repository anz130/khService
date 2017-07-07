package com.dyb.dybc.bean.request;

import com.dyb.dybc.config.Url;
import com.zhy.zhylib.http.BaseRequest;

/**
 * Created by zhangyong on 2016/10/26.
 */

public class LoginReq extends BaseRequest {

    public String username;
    public String password;

    public LoginReq() {
        url = Url.LOGIN;
    }
}
