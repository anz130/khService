package com.pengdu.example;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by Administrator on 2017/7/1.
 */
public class SygActivity extends AppCompatActivity {
    @BindView(R.id.image_back)
    ImageView imageBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.syg_activity);
        ButterKnife.bind(this);


    }


    @OnClick(R.id.image_back)
    public void onViewClicked() {
        startActivity(MainActivity.class);
    }

    private void startActivity(Class<?> targtClass) {
        Intent intent=new Intent(this,targtClass);
        startActivity(intent);
    }
}
