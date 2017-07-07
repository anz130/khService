package com.pengdu.example.base.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/30.
 */

public class MyAdapter extends PagerAdapter {

    private List<View> viewlist=new ArrayList<View>();
    public MyAdapter(List<View> viewList){
        super();
        this.viewlist=viewList;
    }
    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {


    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        position %=viewlist.size();
        View view=viewlist.get(position);
        ViewParent vp = view.getParent();
        if (vp!=null){
            ViewGroup vg= (ViewGroup) vp;
            vg.removeView(view);
        }
        container.addView(view);
        return view;
    }
}
