package com.dyb.dybc.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by y.vn on 2017/3/23.
 */

public class OrderPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> mList;
    private List<String> mTitles;

    public OrderPagerAdapter(FragmentManager fm, List<Fragment> list, List<String> titles) {
        super(fm);
        this.mList = list;
        this.mTitles = titles;
    }

    /**
     * 得到每个页面
     */
    @Override
    public Fragment getItem(int arg0) {
        return (mList == null || mList.size() == 0) ? null : mList.get(arg0);
    }

    /**
     * 每个页面的title
     */
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }

    /**
     * 页面的总个数
     */
    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

}
