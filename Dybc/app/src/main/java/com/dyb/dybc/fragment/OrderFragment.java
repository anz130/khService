package com.dyb.dybc.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dyb.dybc.R;
import com.dyb.dybc.adapter.OrderAdapter;
import com.dyb.dybc.util.Util;
import com.zhy.zhylib.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

/**
 * Created by zhangyong on 2017/4/10.
 */

public class OrderFragment extends BaseFragment implements BGARefreshLayout.BGARefreshLayoutDelegate{

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    BGARefreshLayout refreshLayout;

    private List<Integer> list;
    private OrderAdapter orderAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order, null);
        ButterKnife.bind(this, view);
        init();
        initRefresh();
        setListener();
        return view;
    }

    private void init() {
        list = new ArrayList<>();
        orderAdapter = new OrderAdapter(getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(orderAdapter);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        orderAdapter.setList(list);
    }

    private void initRefresh() {
        refreshLayout.setDelegate(this);
        refreshLayout.setRefreshViewHolder(Util.setRefreshViewHolder(getActivity()));
//        refreshLayout.setRefreshViewHolder(new BGANormalRefreshViewHolder(getActivity(), true));
        refreshLayout.setIsShowLoadingMoreView(true);
    }

    private void setListener() {

    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {

    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {

        return true;
    }

}
