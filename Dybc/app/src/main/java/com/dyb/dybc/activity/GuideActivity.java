package com.dyb.dybc.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;
import android.view.WindowManager;

import com.dyb.dybc.R;
import com.dyb.dybc.bean.UserInfo;
import com.zhy.zhylib.base.BaseActivity;

/**
 * Created by y.vn on 2016/10/28.
 */

public class GuideActivity extends BaseActivity {

    private int LOGIN_CODE = 2;
    private int MAIN_CODE = 1;
    private int START_CODE = 0;
    private boolean toLogin = true;
    private UserInfo userInfo;
    private String userName;
    private String password;
    private String desPassword;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    Intent intent = null;
                    if (toLogin) {
                        intent = new Intent(GuideActivity.this, LoginActivity.class);
                    } else {
                        intent = new Intent(GuideActivity.this, HomeActivity.class);
                    }
                    startActivity(intent);
                    finish();
                    break;
                case 1:
                    toLogin = false;
                    break;
                case 2:
                    toLogin = true;
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_guide);

//        if(Util.checkPermission(mActivity, Manifest.permission.READ_PHONE_STATE, HrConfig.READ_PHONE_STATE_REQUEST_CODE)){
//            initJpush();
//            isHasUserInfo();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                handler.sendEmptyMessage(START_CODE);
            }
        }, 2000);
//        }
    }

//    //判断是否有用户信息
//    private void isHasUserInfo() {
//        userInfo = UserInfoManager.getUserInfo();
//        if (null != userInfo) {
//            autoSessionLogin();
//        } else {
//            autoPasswordLogin();
//        }
//    }
//
//    //session自动登录
//    private void autoSessionLogin() {
//        AutoLoginReq autoLoginReq = new AutoLoginReq();
//        autoLoginReq.url = autoLoginReq.url + userInfo.getSessionid();
//        new OkHttpUtil().doPost(autoLoginReq, new OkHttpCallBack() {
//            @Override
//            public void onError(Call call, Exception e, int id) {
//                if (isFinishing()) {
//                    return;
//                }
//                autoPasswordLogin();
//            }
//
//            @Override
//            public void onResponse(JsonStrResponse response, int id) {
//                if (isFinishing()) {
//                    return;
//                }
//                if (response != null && response.isSuccess() && null != response.data) {
//                    saveUser(response);
//                    handler.sendEmptyMessage(MAIN_CODE);
//                } else {
//                    autoPasswordLogin();
//                }
//            }
//        });
//    }
//
//    //账号密码自动登录
//    private void autoPasswordLogin() {
//        userName = SharedPerferenceUtil.getInstance().getUserJobNumber(mContext);
//        password = SharedPerferenceUtil.getInstance().getUserPassword(mContext);
//        if (!TextUtils.isEmpty(userName) && !TextUtils.isEmpty(password)) {
//            try {
//                desPassword = DesUtils.decryptDES(password, DesUtils.DesUtilsPassWord);
//                LoginReq loginReq = new LoginReq();
//                loginReq.username = userName;
//                loginReq.password = desPassword;
//                loginReq.deviceId = DeviceUtils.getDeviceId(mContext);
//                new OkHttpUtil().doPost(loginReq, new OkHttpCallBack() {
//                    @Override
//                    public void onError(Call call, Exception e, int id) {
//                        if (isFinishing()) {
//                            return;
//                        }
//                        handler.sendEmptyMessage(LOGIN_CODE);
//                    }
//
//                    @Override
//                    public void onResponse(JsonStrResponse response, int id) {
//                        if (isFinishing()) {
//                            return;
//                        }
//                        if (response != null && response.isSuccess() && null != response.data) {
//                            saveUser(response);
//                            handler.sendEmptyMessage(MAIN_CODE);
//                        } else {
//                            handler.sendEmptyMessage(LOGIN_CODE);
//                        }
//                    }
//                });
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        } else {
//            handler.sendEmptyMessage(LOGIN_CODE);
//        }
//    }
//
//    //将用户信息保存到数据库
//    private void saveUser(JsonStrResponse response) {
//        JsonObjResponse<LoginRes> res = JsonObjResponse.newInstance(LoginRes.class, response);
//        if (null == res || res.jsonObj == null) {
//            handler.sendEmptyMessage(LOGIN_CODE);
//            return;
//        }
//        userInfo = new UserInfo();
//        userInfo.setUserId(res.jsonObj.getId());
//        userInfo.setUserName(res.jsonObj.getName());
//        userInfo.setUserJobNumber(res.jsonObj.getLoginName());
//        userInfo.setUserSex(res.jsonObj.getSex());
//        userInfo.setUserMobile(res.jsonObj.getMobile());
//        userInfo.setUserPortrait(res.jsonObj.getPhoto());
//        userInfo.setUserCompany(res.jsonObj.getCompanyName());
//        userInfo.setUserDepartment(res.jsonObj.getOfficeName());
//        userInfo.setUserWork(res.jsonObj.getJobName());
//        userInfo.setRoleType(res.jsonObj.getRoleType());
//        userInfo.setSessionid(res.jsonObj.getSessionid());
//        UserInfoManager.addUserInfo(userInfo);
//        SharedPerferenceUtil.getInstance().putUserJobNumber(mContext, res.jsonObj.getLoginName());
//        HrApplication.setUser(userInfo);
//    }

}
