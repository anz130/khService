package com.dyb.dybc.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.dyb.dybc.R;
import com.dyb.dybc.fragment.HomeFragment;
import com.dyb.dybc.fragment.MineFragment;
import com.dyb.dybc.fragment.MyOrderFragment;
import com.dyb.dybc.fragment.ShareFragment;
import com.dyb.dybc.views.TitleModule;
import com.zhy.zhylib.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zhangyong on 2017/6/29.
 */

public class HomeActivity extends BaseActivity {

    @BindView(R.id.frameLayout)
    FrameLayout frameLayout;
    @BindView(R.id.homeLinear)
    LinearLayout homeLinear;
    @BindView(R.id.orderLinear)
    LinearLayout orderLinear;
    @BindView(R.id.shareLinear)
    LinearLayout shareLinear;
    @BindView(R.id.mineLinear)
    LinearLayout mineLinear;
    private TitleModule titleModule;
    private FragmentManager fragmentManager;
    private int selectState = 0;

    private List<Fragment> fragList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        init();
        setListener();
    }

    private void init() {
        titleModule = new TitleModule(this, "抢单");
        titleModule.setLeftImageVisible(false);
        titleModule.setRightTextVisible(true);
        titleModule.setRightText("下班");

        fragList = new ArrayList<>();
        fragmentManager = getSupportFragmentManager();
        fragList.add(new HomeFragment());
        fragList.add(new MyOrderFragment());
        fragList.add(new ShareFragment());
        fragList.add(new MineFragment());
        fragmentManager.beginTransaction().replace(R.id.frameLayout, fragList.get(0), "0").commit();
        homeLinear.setSelected(true);
    }

    private void setListener(){
        titleModule.setRightTextViewOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void initViewpager(int tag) {
        Fragment frag = fragmentManager.findFragmentByTag("" + tag);
        if (frag == null) {
            fragmentManager.beginTransaction().add(R.id.frameLayout, fragList.get(tag), tag + "").commit();
        }
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (selectState != tag) {
            fragmentTransaction.show(fragList.get(tag));
            fragmentTransaction.hide(fragList.get(selectState));
            fragmentTransaction.commit();
            selectState = tag;
        }
    }

    private void setSelectState(int state, boolean isSelect) {
        if (isSelect) {
            setSelectState(selectState, false);
        }
        switch (state) {
            case 0:
                homeLinear.setSelected(isSelect == true ? true : false);
                break;
            case 1:
                orderLinear.setSelected(isSelect == true ? true : false);
                break;
            case 2:
                shareLinear.setSelected(isSelect == true ? true : false);
                break;
            case 3:
                mineLinear.setSelected(isSelect == true ? true : false);
                break;
            default:

                break;
        }
    }

    @OnClick({R.id.homeLinear, R.id.orderLinear, R.id.shareLinear, R.id.mineLinear})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.homeLinear:
                setSelectState(0, true);
                initViewpager(0);
                titleModule.setTitle("抢单");
                break;
            case R.id.orderLinear:
                setSelectState(1, true);
                initViewpager(1);
                titleModule.setTitle("订单");
                break;
            case R.id.shareLinear:
                setSelectState(2, true);
                initViewpager(2);
                titleModule.setTitle("分享赚钱");
                break;
            case R.id.mineLinear:
                setSelectState(3, true);
                initViewpager(3);
                titleModule.setTitle("我的");
                break;
        }
    }
}
