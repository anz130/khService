package com.dyb.dybc.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dyb.dybc.R;
import com.dyb.dybc.adapter.OrderAdapter;
import com.dyb.dybc.adapter.SearchAddressAdapter;
import com.dyb.dybc.config.Config;
import com.dyb.dybc.util.Util;
import com.dyb.dybc.views.ScrollTextView;
import com.zhy.zhylib.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

/**
 * Created by zhangyong on 2017/7/1.
 */

public class SearchAddressActivity extends BaseActivity implements BGARefreshLayout.BGARefreshLayoutDelegate {


    @BindView(R.id.leftImageLinear)
    LinearLayout leftImageLinear;
    @BindView(R.id.title)
    ScrollTextView title;
    @BindView(R.id.rightText)
    TextView rightText;
    @BindView(R.id.searchEditText)
    EditText searchEditText;
    @BindView(R.id.search)
    TextView search;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    BGARefreshLayout refreshLayout;

    private SearchAddressAdapter searchAddressAdapter;
    private List<Object> addressList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_address);
        ButterKnife.bind(this);
        init();
        initRefresh();
    }

    private void init() {
        title.setText("搜索地址");

        addressList = new ArrayList<>();
        searchAddressAdapter = new SearchAddressAdapter(mActivity);
        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        recyclerView.setAdapter(searchAddressAdapter);
        addressList.add(1);
        addressList.add(1);
        addressList.add(1);
        addressList.add(1);
        addressList.add(1);
        addressList.add(1);
        addressList.add(1);
        addressList.add(1);
        addressList.add(1);
        addressList.add(1);
        searchAddressAdapter.setList(addressList);
    }

    private void initRefresh() {
        refreshLayout.setDelegate(this);
        refreshLayout.setRefreshViewHolder(Util.setRefreshViewHolder(mActivity));
//        refreshLayout.setRefreshViewHolder(new BGANormalRefreshViewHolder(getActivity(), true));
        refreshLayout.setIsShowLoadingMoreView(true);
    }

    @OnClick({R.id.leftImageLinear, R.id.rightText, R.id.search})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.leftImageLinear:
                finish();
                break;
            case R.id.rightText:
                Intent intent = new Intent(mActivity,CityPickerActivity.class);
                startActivityForResult(intent, Config.SEARCH_CITY);
                break;
            case R.id.search:

                break;
        }
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {

    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {

        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data != null && requestCode == Config.SEARCH_CITY && resultCode == Config.SEARCH_CITY && data.hasExtra("city")){
            rightText.setText(data.getStringExtra("city"));
        }
    }
}
