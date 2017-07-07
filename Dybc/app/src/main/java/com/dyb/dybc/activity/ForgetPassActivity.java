package com.dyb.dybc.activity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.dyb.dybc.R;
import com.dyb.dybc.util.Util;
import com.dyb.dybc.views.LoadingDialog;
import com.dyb.dybc.views.TitleModule;
import com.zhy.zhylib.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zhangyong on 2017/5/15.
 */

public class ForgetPassActivity extends BaseActivity {


    @BindView(R.id.userPhone)
    EditText userPhone;
    @BindView(R.id.userCode)
    EditText userCode;
    @BindView(R.id.codeText)
    TextView codeText;
    @BindView(R.id.userPass)
    EditText userPass;
    @BindView(R.id.userConfirmPass)
    EditText userConfirmPass;
    @BindView(R.id.confirm)
    TextView confirm;


    private TitleModule titleModule;
    private LoadingDialog loadingDialog;
    private MyCountDownTimer myCountDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Util.setWindowImmersiveState(mActivity);
        setContentView(R.layout.activity_forget_pass);
        ButterKnife.bind(this);
        init();
        setListener();
    }

    private void init() {
        titleModule = new TitleModule(this, "忘记密码");
        loadingDialog = new LoadingDialog(this);
        myCountDownTimer = new MyCountDownTimer(60000, 1000);
    }

    private boolean checkState() {
        if (TextUtils.isEmpty(userPhone.getText().toString().trim())) {
            showToast("用户名不能为空");
            return false;
        } else if (TextUtils.isEmpty(userPass.getText().toString().trim()) || TextUtils.isEmpty(userConfirmPass.getText().toString().trim())) {
            showToast("密码不能为空");
            return false;
        } else if (userPass.getText().toString().trim().length() < 6 || userPass.getText().toString().trim().length() > 18) {
            showToast("账号不能小于1位并且不能大于18位");
            return false;
        } else if (userConfirmPass.getText().toString().trim().length() < 6 || userConfirmPass.getText().toString().trim().length() > 18) {
            showToast("密码不能小于6位并且不能大于18位");
            return false;
        } else if (!userConfirmPass.equals(userPass)) {
            showToast("密码不一致");
            return false;
        }
        return true;
    }

    private void setListener() {

    }

//    private void changePass() {
//        loadingDialog.show();
//        RegistReq registReq = new RegistReq();
//        registReq.username = userName.getText().toString().trim();
//        registReq.password = userPass.getText().toString().trim();
//        registReq.userdesc = userRemark.getText().toString().trim();
//        new OkHttpUtil().doPost(registReq, new OkHttpCallBack() {
//            @Override
//            public void onError(Call call, Exception e, int id) {
//                if (isFinishing()) {
//                    return;
//                }
//                showToast("获取数据失败，请稍后重试...");
//                loadingDialog.dismiss();
//                regist.setClickable(true);
//                e.printStackTrace();
//            }
//
//            @Override
//            public void onResponse(JsonStrResponse response, int id) {
//                if (isFinishing()) {
//                    return;
//                }
//                if (null == response) {
//                    showToast("获取数据失败，请稍后重试...");
//                    loadingDialog.dismiss();
//                    return;
//                }
//                if (response.isSuccess() && null != response.data) {
//                    JsonObjResponse<RegistRes> res = JsonObjResponse.newInstance(RegistRes.class, response);
//                    Intent intent = new Intent();
//                    intent.putExtra("username", userName.getText().toString().trim());
//                    intent.putExtra("password", userPass.getText().toString().trim());
//                    setResult(Config.LOGIN_REGIST, intent);
//                    finish();
//                }
//                Toast.makeText(mContext, response.msg, Toast.LENGTH_LONG).show();
//                loadingDialog.dismiss();
//                regist.setClickable(true);
//            }
//        });
//    }

    @OnClick({R.id.codeText, R.id.confirm})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.codeText:
                myCountDownTimer.start();
                codeText.setClickable(false);
                break;
            case R.id.confirm:
                finish();
//                if (checkState()) {
//                    confirm.setClickable(false);
//                    regist();
//                }
                break;
        }
    }

    class MyCountDownTimer extends CountDownTimer {

        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {
            codeText.setText("验证码");
            codeText.setClickable(true);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            codeText.setText(millisUntilFinished / 1000 + "");
        }
    }

}
