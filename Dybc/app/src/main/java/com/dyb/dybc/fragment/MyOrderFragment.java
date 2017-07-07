package com.dyb.dybc.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dyb.dybc.R;
import com.dyb.dybc.adapter.OrderPagerAdapter;
import com.dyb.dybc.util.Util;
import com.zhy.zhylib.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zhangyong on 2017/6/29.
 */

public class MyOrderFragment extends BaseFragment {
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    private List<Fragment> fragList;
    private List<String> titles;
    private OrderFragment orderFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_order, null);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        fragList = new ArrayList<>();
        titles = new ArrayList<>();
        titles.add("全部");
        titles.add("待取件");
        titles.add("未完成");
        titles.add("已完成");
        Bundle bundle;
        for (int i = 0; i < titles.size(); i++) {
            bundle = new Bundle();
            bundle.putString("state", i + "");
            orderFragment = new OrderFragment();
            orderFragment.setArguments(bundle);
            fragList.add(orderFragment);
        }
        viewPager.setAdapter(new OrderPagerAdapter(getActivity().getSupportFragmentManager(), fragList, titles));
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                Util.setIndicator(tabLayout, 15, 15);
            }
        });
    }

}
