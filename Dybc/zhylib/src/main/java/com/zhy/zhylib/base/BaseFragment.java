package com.zhy.zhylib.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.zhy.zhylib.R;

/**
 * Created by zhangyong
 */
public class BaseFragment extends Fragment {

    protected boolean isVisible;
    private boolean isPause;
    private Toast mToast;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        isPause = false;
    }

    @Override
    public void onPause() {
        super.onPause();
        isPause = true;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        isVisible = isVisibleToUser;
        if (isVisibleToUser) {
            onVisible();
        } else {
            onInvisible();
            System.gc();
            System.runFinalization();
        }
    }

    /**
     * Fragment Page显示
     */
    protected void onVisible() {
    }

    /**
     * Fragment Page未显示
     */
    protected void onInvisible() {

    }

    public void showToast(String msg) {
        if (isPause) {
            return;
        }
        if (null == mToast) {
            createToast();
        }
        TextView view = (TextView) mToast.getView();
        view.setText(msg);
        mToast.show();
    }

    public void showToast(int msgId) {
        if (isPause) {
            return;
        }
        if (null == mToast) {
            createToast();
        }
        TextView view = (TextView) mToast.getView();
        view.setText(msgId);
        mToast.show();
    }

    private void createToast() {
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.toast_layout, null);
        mToast = new Toast(getActivity());
        mToast.setView(v);
        mToast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 50);
        mToast.setDuration(Toast.LENGTH_SHORT);
    }
}
