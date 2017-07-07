package com.zhy.zhylib.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangyong
 */

public abstract class CommonAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    protected Context mContext;
    protected List<T> mList;
    protected LayoutInflater mInflater;

    public CommonAdapter() {
        mList = new ArrayList<T>();
        mInflater = LayoutInflater.from(mContext);
    }

    public CommonAdapter(Context context) {
        super();
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }

    public CommonAdapter(Context context,List<T> list) {
        super();
        mContext = context;
        mList = list;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getItemCount() {
        return null == mList ? 0 : mList.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return commonCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        commonBindViewHolder(holder, position);
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    public abstract RecyclerView.ViewHolder commonCreateViewHolder(ViewGroup parent, int viewType);

    public abstract void commonBindViewHolder(RecyclerView.ViewHolder holder,int position);

    public void setList(List<T> list) {
        mList = list;
        notifyDataSetChanged();
    }

    public void remove(T t) {
        mList.remove(t);
        notifyDataSetChanged();
    }

    public void add(T t) {
        mList.add(t);
        notifyDataSetChanged();
    }

    public void clear() {
        mList.clear();
        notifyDataSetChanged();
    }
}
