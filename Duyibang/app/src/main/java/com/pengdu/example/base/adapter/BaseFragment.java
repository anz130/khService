package com.pengdu.example.base.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pengdu.example.R;
import com.pengdu.example.view.ActionBarView;

/**
 * Created by Administrator on 2017/7/1.
 */

public class BaseFragment extends Fragment {
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view=inflater.inflate(R.layout.layout_actionbar,container,false);
        return view;
    }
    // --------------------------------初始化ActionBar-------------------------------------------------

    protected void initActionBar(String title, int leftResID, String string,
                                 View.OnClickListener listener) {
        try {
            ActionBarView actionBar = view.findViewById(R.id.action_bar);
            actionBar.initActionBar(title, leftResID, string, listener);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }



    // -----------------Activity跳转及结束-------------------------------------------------------
    protected void startActivity(Class<?> targetClass) {
        Intent intent = new Intent(getActivity(),targetClass);
        startActivity(intent);
    }

    protected void startActivity(Class<?> targetClass, Bundle bundle) {
        Intent intent = new Intent(getActivity(), targetClass);
        intent.putExtras(bundle);
        startActivity(intent);
    }

}
