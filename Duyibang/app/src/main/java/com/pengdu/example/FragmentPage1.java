package com.pengdu.example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.pengdu.example.base.adapter.BaseFragment;
import com.pengdu.example.base.adapter.MyAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/6/30.
 */
public class FragmentPage1 extends BaseFragment {
    @BindView(R.id.tv_adress)
    TextView tvAdress;
    @BindView(R.id.image_msg)
    ImageView imageMsg;
    @BindView(R.id.tv_serch)
    TextView tvSerch;
    @BindView(R.id.btn_sys)
    Button btnSys;
    @BindView(R.id.btn_syg)
    Button btnSyg;
    @BindView(R.id.btn_syb)
    Button btnSyb;

    Unbinder unbinder;
    @BindView(R.id.viewager)
    ViewPager viewpager;
    private List<View> viewList = new ArrayList<View>();




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.one_fagment, container, false);
        unbinder = ButterKnife.bind(this, view);

        initPagerData();
        return view;
    }


    private void initPagerData() {
        viewpager.setAdapter(new MyAdapter(viewList));
        viewpager.setCurrentItem(Integer.MAX_VALUE / 2);
        ImageView imageView = null;
        imageView = (ImageView) getLayoutInflater(null).inflate(R.layout.view_item, null);
        imageView.setImageResource(R.mipmap.tup);
        viewList.add(imageView);
        imageView = (ImageView) getLayoutInflater(null).inflate(R.layout.view_item, null);
        imageView.setImageResource(R.mipmap.tup);
        viewList.add(imageView);
        imageView = (ImageView) getLayoutInflater(null).inflate(R.layout.view_item, null);
        imageView.setImageResource(R.mipmap.tup);
        viewList.add(imageView);
        imageView = (ImageView) getLayoutInflater(null).inflate(R.layout.view_item, null);
        imageView.setImageResource(R.mipmap.tup);
        viewList.add(imageView);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.tv_adress, R.id.image_msg, R.id.tv_serch, R.id.btn_sys, R.id.btn_syg, R.id.btn_syb})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_adress:
                startActivity(AddressActivity.class);
                break;
            case R.id.image_msg:

                break;
            case R.id.tv_serch:

                break;
            case R.id.btn_sys:
                startActivity(SysActivity.class);
                break;
            case R.id.btn_syg:
                startActivity(SygActivity.class);
                break;
            case R.id.btn_syb:
                break;
        }
    }




}
