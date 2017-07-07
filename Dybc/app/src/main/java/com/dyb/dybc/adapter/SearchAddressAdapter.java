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

public class SearchAddressAdapter extends CommonAdapter<Object> {

    public SearchAddressAdapter(Context context) {
        super(context);
    }

    @Override
    public RecyclerView.ViewHolder commonCreateViewHolder(ViewGroup parent, int viewType) {
        SearchAddressAdapter.MyViewHolder myViewHolder = new SearchAddressAdapter.MyViewHolder(mInflater.inflate(R.layout.activity_search_address_item, parent, false));
        return myViewHolder;
    }

    @Override
    public void commonBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        SearchAddressAdapter.MyViewHolder myViewHolder = (SearchAddressAdapter.MyViewHolder) holder;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView addressName,addressDetail;

        public MyViewHolder(View itemView) {
            super(itemView);
            addressName = (TextView) itemView.findViewById(R.id.addressName);
            addressDetail = (TextView) itemView.findViewById(R.id.addressDetail);
        }
    }
}
