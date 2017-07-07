package com.pengdu.example;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.pengdu.example.base.adapter.BaseActivity;

/**
 * Created by Administrator on 2017/7/1.
 */
public class LoginActivity  extends BaseActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
    }
}
