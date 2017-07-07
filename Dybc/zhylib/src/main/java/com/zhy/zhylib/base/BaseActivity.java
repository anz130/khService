package com.zhy.zhylib.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.zhy.zhylib.R;
import com.zhy.zhylib.listener.NetBroadcastReceiver;
import com.zhy.zhylib.utils.NetUtil;


/**
 * Created by zhangyong
 */
public class BaseActivity extends FragmentActivity implements NetBroadcastReceiver.NetEvevt {

    private Toast mToast;
    private boolean isPause;
    public Context mContext;
    public BaseActivity mActivity;
    private int netMobile;
    public static NetBroadcastReceiver.NetEvevt evevt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init(){
        mContext = this;
        mActivity = this;
        evevt = this;
    }

    @Override
    protected void onResume() {
        super.onResume();
        isPause = false;
    }

    @Override
    protected void onPause() {
        super.onPause();
        isPause = true;
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        if (level == TRIM_MEMORY_UI_HIDDEN) {
            System.gc();
            System.runFinalization();
        }
    }

    /**
     * 初始化时判断有没有网络
     */
    public boolean inspectNet() {
        this.netMobile = NetUtil.getNetWorkState(BaseActivity.this);
        return isNetConnect();
    }

    /**
     * 网络变化之后的类型
     */
    @Override
    public void onNetChange(int netMobile) {
        // TODO Auto-generated method stub
        this.netMobile = netMobile;
        isNetConnect();
    }

    /**
     * 判断有无网络 。
     *
     * @return true 有网, false 没有网络.
     */
    public boolean isNetConnect() {
        if (netMobile == 1) {
            return true;
        } else if (netMobile == 0) {
            return true;
        } else if (netMobile == -1) {
            return false;

        }
        return false;
    }

    /**
     * 显示toast
     */
    public void showToast(String msg) {
        if (isPause) {
            return;
        }
        if (null == mToast) {
            createToast();
        }
        TextView view = (TextView)mToast.getView();
        view.setText(msg);
        mToast.show();
    }

    /**
     * 显示toast
     */
    public void showToast(int msgId) {
        if (isPause) {
            return;
        }
        if (null == mToast) {
            createToast();
        }
        TextView view = (TextView)mToast.getView();
        view.setText(msgId);
        mToast.show();
    }

    private void createToast() {
        View view = LayoutInflater.from(this).inflate(R.layout.toast_layout, null);
        mToast = new Toast(this);
        mToast.setView(view);
        mToast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 50);
        mToast.setDuration(Toast.LENGTH_SHORT);
    }
}
