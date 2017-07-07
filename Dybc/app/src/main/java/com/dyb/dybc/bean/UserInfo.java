package com.dyb.dybc.bean;

import com.zhy.zhylib.bean.BaseInfo;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

/**
 * Created by zhangyong on 2017/5/31.
 */

@Entity
public class UserInfo extends BaseInfo {

    @Id(autoincrement = false)
    private long id;

    @Property(nameInDb = "uid")
    private String uid;

    @Property(nameInDb = "userName")
    private String userName;

    @Property(nameInDb = "loginName")
    private String loginName;

    @Property(nameInDb = "token")
    private String token;

    @Generated(hash = 297442991)
    public UserInfo(long id, String uid, String userName, String loginName,
                    String token) {
        this.id = id;
        this.uid = uid;
        this.userName = userName;
        this.loginName = loginName;
        this.token = token;
    }

    @Generated(hash = 1279772520)
    public UserInfo() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
