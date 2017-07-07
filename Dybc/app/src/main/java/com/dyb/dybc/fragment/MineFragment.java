package com.dyb.dybc.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dyb.dybc.R;
import com.dyb.dybc.activity.CityPickerActivity;
import com.dyb.dybc.activity.SearchAddressActivity;
import com.thinkcool.circletextimageview.CircleTextImageView;
import com.zhy.zhylib.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zhangyong on 2017/6/29.
 */

public class MineFragment extends BaseFragment {


    @BindView(R.id.userImage)
    CircleTextImageView userImage;
    @BindView(R.id.userName)
    TextView userName;
    @BindView(R.id.main_companyname)
    TextView mainCompanyname;
    @BindView(R.id.userInfoLinear)
    LinearLayout userInfoLinear;
    @BindView(R.id.moneyBoxLinear)
    LinearLayout moneyBoxLinear;
//    @BindView(R.id.shareLinear)
//    LinearLayout shareLinear;
    @BindView(R.id.settingLinear)
    LinearLayout settingLinear;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, null);
        ButterKnife.bind(this, view);
        init();
        setListener();
        return view;
    }

    private void init() {

    }

    private void setListener() {

    }

    @OnClick({R.id.userInfoLinear, R.id.moneyBoxLinear, R.id.settingLinear})
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.userInfoLinear:
//                intent = new Intent(getActivity(), PersonalInfoActivity.class);
//                startActivity(intent);
                break;
            case R.id.moneyBoxLinear:
//                intent = new Intent(getActivity(), MoneyBoxActivity.class);
//                startActivity(intent);
                break;
            case R.id.settingLinear:
//                intent = new Intent(getActivity(), SettingActivity.class);
//                startActivity(intent);
                intent = new Intent(getActivity(), SearchAddressActivity.class);
                startActivity(intent);
                break;
        }
    }
}
