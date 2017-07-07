package com.dyb.dybc.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.DigitsKeyListener;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.dyb.dybc.MyApplication;
import com.dyb.dybc.R;
import com.dyb.dybc.bean.UserInfo;
import com.dyb.dybc.bean.request.LoginReq;
import com.dyb.dybc.config.Config;
import com.dyb.dybc.config.PreferenceConfig;
import com.dyb.dybc.greendao.BaseDao;
import com.dyb.dybc.util.Util;
import com.dyb.dybc.views.LoadingDialog;
import com.zhy.zhylib.base.BaseActivity;
import com.zhy.zhylib.http.JsonStrResponse;
import com.zhy.zhylib.http.OkHttpCallBack;
import com.zhy.zhylib.http.OkHttpUtil;
import com.zhy.zhylib.utils.DesUtils;
import com.zhy.zhylib.utils.PreferenceUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * Created by zhangyong on 2016/10/26.
 */

public class LoginActivity extends BaseActivity {

    @BindView(R.id.login_user)
    EditText loginUser;
    @BindView(R.id.login_pass)
    EditText loginPass;
    @BindView(R.id.loginUserDelete)
    ImageView loginUserDelete;
    @BindView(R.id.loginPassDelete)
    ImageView loginPassDelete;
    @BindView(R.id.login)
    TextView login;
    @BindView(R.id.regist)
    TextView regist;
    @BindView(R.id.forgetPass)
    TextView forgetPass;

    private LoadingDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Util.setWindowImmersiveState(this);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        init();
        setListener();
    }

    private void init() {
        loadingDialog = new LoadingDialog(this);
        String logincode = PreferenceUtils.getInstance().getString(mContext, PreferenceConfig.LOGIN, PreferenceConfig.LOGIN_NAME,"");
        if (!TextUtils.isEmpty(logincode)) {
            loginUser.setText(logincode);
            loginUserDelete.setVisibility(View.VISIBLE);
        }
        String password = PreferenceUtils.getInstance().getString(mContext, PreferenceConfig.LOGIN,PreferenceConfig.LOGIN_PASS,"");
        if (!TextUtils.isEmpty(password) && null !=  MyApplication.getUser()) {
            try {
                String text = MyApplication.getUser().getToken();
                loginPass.setText(DesUtils.decryptDES(password, text.substring(text.length()-8,text.length())));
                loginPassDelete.setVisibility(View.VISIBLE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void setListener() {
        loginUser.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                loginUserDelete.setVisibility(loginUser.getText().toString().length() > 0 ? View.VISIBLE : View.INVISIBLE);
            }
        });

        loginPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                loginPassDelete.setVisibility(loginPass.getText().toString().length() > 0 ? View.VISIBLE : View.INVISIBLE);
            }
        });

        loginPass.setKeyListener(new DigitsKeyListener() {
            @Override
            public int getInputType() {
                return InputType.TYPE_TEXT_VARIATION_PASSWORD;
            }
            @Override
            protected char[] getAcceptedChars() {
                char[] data = getResources().getString(R.string.number_english).toCharArray();
                return data;
            }
        });

        loginUserDelete.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                loginUser.setText("");
                return true;
            }
        });

        loginPassDelete.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                loginPass.setText("");
                return true;
            }
        });
    }

    @OnClick({R.id.login,R.id.regist,R.id.forgetPass, R.id.loginUserDelete, R.id.loginPassDelete})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login:
                startActivity(new Intent(mActivity,HomeActivity.class));
                finish();
//                if (checkState()) {
//                login.setClickable(false);
//                login();
//                }
                break;
            case R.id.regist:
                Intent registIntent = new Intent(mActivity, RegistActivity.class);
                startActivityForResult(registIntent, Config.LOGIN_REGIST);
                break;
            case R.id.forgetPass:
                Intent forgetIntent = new Intent(mActivity, ForgetPassActivity.class);
                startActivityForResult(forgetIntent, Config.LOGIN_REGIST);
                break;
            case R.id.loginUserDelete:
                if (loginUser.getText().toString().length() > 0) {
                    loginUser.setText(loginUser.getText().toString().substring(0, loginUser.getText().toString().length() - 1));
                    loginUser.setSelection(loginUser.getText().length());
                }
                break;
            case R.id.loginPassDelete:
                if (loginPass.getText().toString().length() > 0) {
                    loginPass.setText(loginPass.getText().toString().substring(0, loginPass.getText().toString().length() - 1));
                    loginPass.setSelection(loginPass.getText().length());
                }
                break;
        }
    }

    private void login() {
        loadingDialog.show();
        LoginReq loginReq = new LoginReq();
        loginReq.username = loginUser.getText().toString().trim();
        loginReq.password = loginPass.getText().toString().trim();
        new OkHttpUtil().doPost(loginReq, new OkHttpCallBack() {
            @Override
            public void onError(Call call, Exception e, int id) {
                if (isFinishing()) {
                    return;
                }
                showToast("获取数据失败，请稍后重试...");
                loadingDialog.dismiss();
                login.setClickable(true);
                e.printStackTrace();
            }

            @Override
            public void onResponse(JsonStrResponse response, int id) {
                if (isFinishing()) {
                    return;
                }
                if (null == response) {
                    showToast("获取数据失败，请稍后重试...");
                    loadingDialog.dismiss();
                    return;
                }
                if (response.isSuccess() && null != response.data) {
//                    JsonObjResponse<LoginRes> res = JsonObjResponse.newInstance(LoginRes.class, response);
//                    UserInfo userInfo = new UserInfo();
//                    userInfo.setUserId(res.jsonObj.getUserId());
//                    userInfo.setCreateTime(res.jsonObj.getCreateTime());
//                    userInfo.setLoginname(res.jsonObj.getLoginname());
//                    userInfo.setMobile(res.jsonObj.getMobile());
//                    userInfo.setUserdesc(res.jsonObj.getUserdesc());
//                    userInfo.setUserlevel(res.jsonObj.getUserlevel());
//                    userInfo.setUsername(res.jsonObj.getUsername());
//                    userInfo.setUsertype(res.jsonObj.getUsertype());
//                    userInfo.setToken(res.jsonObj.getToken());
//
//                    saveUser(userInfo, loginPass.getText().toString().trim(), res.jsonObj.getToken().subSequence(res.jsonObj.getToken().length()-8,res.jsonObj.getToken().length()).toString());
//                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                    startActivity(intent);
//                    finish();
                } else if (response.isFail()) {
                    showToast(response.msg);
                }
                loadingDialog.dismiss();
                login.setClickable(true);
            }
        });
    }

    private void saveUser(UserInfo userInfo, String userpassword, String key) {
        try {
            BaseDao.getInstance().insertOrReplace(userInfo);
            PreferenceUtils.getInstance().putString(mContext, PreferenceConfig.LOGIN,PreferenceConfig.LOGIN_NAME,userInfo.getLoginName());
            MyApplication.setUser(userInfo);
            String passWord = DesUtils.encryptDES(userpassword, key);
            PreferenceUtils.getInstance().putString(mContext, PreferenceConfig.LOGIN,PreferenceConfig.LOGIN_NAME,passWord);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == Config.LOGIN_REGIST && null != data){
            if(data.hasExtra("username")){
                loginUser.setText(data.getStringExtra("username"));
            }
            if(data.hasExtra("password")){
                loginPass.setText(data.getStringExtra("password"));
            }
        }
    }

    private boolean checkState() {
        if (TextUtils.isEmpty(loginUser.getText().toString().trim())) {
            showToast("用户名不能为空");
            return false;
        } else if (TextUtils.isEmpty(loginPass.getText().toString().trim())) {
            showToast("密码不能为空");
            return false;
        } else if (loginUser.getText().toString().trim().length() < 1 || loginUser.getText().toString().trim().length() > 18) {
            showToast("账号不能小于1位并且不能大于18位");
            return false;
        }else if (loginPass.getText().toString().trim().length() < 6 || loginPass.getText().toString().trim().length() > 18) {
            showToast("密码不能小于6位并且不能大于18位");
            return false;
        }
        return true;
    }
}
