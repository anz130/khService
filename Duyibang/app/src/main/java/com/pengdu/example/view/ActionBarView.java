package com.pengdu.example.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pengdu.example.R;

/**
 * Created by Administrator on 2017/7/1.
 */

public class ActionBarView extends LinearLayout {
    private ImageView iv_actionbar_left;
    private TextView iv_actionbar_right;
    private TextView tv_title;

    public ActionBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.layout_actionbar, this);
        tv_title = findViewById(R.id.tv_title);
        iv_actionbar_left = findViewById(R.id.iv_left);
        iv_actionbar_right = findViewById(R.id.iv_right);

    }

    public void initActionBar(String title, int leftResID, String string,
                              OnClickListener listener) {
        tv_title.setText(title);
        if (leftResID == -1) {
            iv_actionbar_left.setVisibility(View.INVISIBLE);
        } else {
            iv_actionbar_left.setImageResource(leftResID);
            iv_actionbar_left.setOnClickListener(listener);
        }
        if (string ==null) {
            iv_actionbar_right.setVisibility(View.INVISIBLE);
        } else {
            iv_actionbar_right.setText(string);
            iv_actionbar_right.setOnClickListener(listener);
        }
    }
}
