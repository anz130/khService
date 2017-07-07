package com.zhy.zhylib.listener;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;

import com.zhy.zhylib.base.BaseActivity;
import com.zhy.zhylib.utils.NetUtil;

/**
 * Created by zhangyong on 2017/6/19.
 */

public class NetBroadcastReceiver extends BroadcastReceiver {

    public NetEvevt evevt = BaseActivity.evevt;

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub
        if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
            int netWorkState = NetUtil.getNetWorkState(context);
            evevt.onNetChange(netWorkState);
        }
    }

    public interface NetEvevt {
        public void onNetChange(int netMobile);
    }

}
