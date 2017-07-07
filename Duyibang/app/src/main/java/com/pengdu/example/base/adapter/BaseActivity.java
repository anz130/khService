package com.pengdu.example.base.adapter;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.pengdu.example.R;
import com.pengdu.example.view.ActionBarView;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Administrator on 2017/7/1.
 */

public abstract class BaseActivity extends AppCompatActivity {

    private static ArrayList<BaseActivity> onlineActivityList = new ArrayList<BaseActivity>();

    /**
     * 依次退出当前存在的所有Activity
     */
    public static void finishAll() {
        Iterator<BaseActivity> iterator = onlineActivityList.iterator();
        while (iterator.hasNext()) {
            iterator.next().finish();
        }
    }
    // --------------------------------初始化ActionBar-------------------------------------------------

    protected void initActionBar(String title, int leftResID, String string,
                                 View.OnClickListener listener) {
        try {
            ActionBarView actionBar = (ActionBarView) findViewById(R.id.action_bar);
            actionBar.initActionBar(title, leftResID, string, listener);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    // -----------------Activity跳转及结束-------------------------------------------------------
    protected void startActivity(Class<?> targetClass) {
        Intent intent = new Intent(this,targetClass);
        startActivity(intent);
    }

    protected void startActivity(Class<?> targetClass, Bundle bundle) {
        Intent intent = new Intent(this, targetClass);
        intent.putExtras(bundle);
        startActivity(intent);
    }

}
