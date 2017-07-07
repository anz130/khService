package com.dyb.dybc.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dyb.dybc.R;
import com.zhy.zhylib.base.CommonAdapter;

/**
 * Created by zhangyong on 2017/6/29.
 */

public class OrderAdapter extends CommonAdapter {

    public OrderAdapter(Context context) {
        super(context);
    }

    @Override
    public RecyclerView.ViewHolder commonCreateViewHolder(ViewGroup parent, int viewType) {
        OrderAdapter.MyViewHolder myViewHolder = new OrderAdapter.MyViewHolder(mInflater.inflate(R.layout.fragment_order_item, parent, false));
        return myViewHolder;
    }

    @Override
    public void commonBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        OrderAdapter.MyViewHolder myViewHolder = (OrderAdapter.MyViewHolder) holder;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name,time;

        public MyViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.image);
            name = (TextView) itemView.findViewById(R.id.name);
            time = (TextView) itemView.findViewById(R.id.time);
        }
    }
}
