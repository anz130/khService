package com.pengdu.example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pengdu.example.base.adapter.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Administrator on 2017/6/30.
 */
public class FragmentPage4 extends BaseFragment {


    @BindView(R.id.image_back)
    ImageView imageBack;
    @BindView(R.id.icon_image)
    CircleImageView iconImage;
    @BindView(R.id.mail)
    TextView mail;
    @BindView(R.id.username)
    TextView username;
    @BindView(R.id.tv_account)
    TextView tvAccount;
    @BindView(R.id.tv_member)
    TextView tvMember;
    @BindView(R.id.tv_award)
    TextView tvAward;
    @BindView(R.id.tv_invite)
    TextView tvInvite;
    @BindView(R.id.tv_helper)
    TextView tvHelper;
    @BindView(R.id.tv_frpartner)
    TextView tvFrpartner;
    @BindView(R.id.tv_service)
    TextView tvService;
    @BindView(R.id.tv_feedback)
    TextView tvFeedback;
    @BindView(R.id.tv_setting)
    TextView tvSetting;
    @BindView(R.id.tv_logout)
    TextView tvLogout;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.four_fagment, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.image_back, R.id.icon_image, R.id.mail, R.id.username, R.id.tv_account,
            R.id.tv_member, R.id.tv_award, R.id.tv_invite, R.id.tv_helper, R.id.tv_frpartner,
            R.id.tv_service, R.id.tv_feedback, R.id.tv_setting, R.id.tv_logout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_back:
                break;
            case R.id.icon_image:
                break;
            case R.id.mail:
                break;
            case R.id.username:
                break;
            case R.id.tv_account:
                break;
            case R.id.tv_member:
                break;
            case R.id.tv_award:
                break;
            case R.id.tv_invite:
                break;
            case R.id.tv_helper:
                break;
            case R.id.tv_frpartner:
                break;
            case R.id.tv_service:
                break;
            case R.id.tv_feedback:
                break;
            case R.id.tv_setting:
                break;
            case R.id.tv_logout:
                break;
        }
    }
}
