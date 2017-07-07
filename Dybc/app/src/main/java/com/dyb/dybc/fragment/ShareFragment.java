package com.dyb.dybc.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dyb.dybc.R;
import com.zhy.zhylib.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zhangyong on 2017/6/29.
 */

public class ShareFragment extends BaseFragment {

    @BindView(R.id.shareContentText)
    TextView shareContentText;
    @BindView(R.id.shareImage)
    ImageView shareImage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_share, null);
        ButterKnife.bind(this, view);
        init();
        setListener();
        return view;
    }

    private void init() {

    }

    private void setListener() {

    }

}
