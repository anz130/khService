package com.dyb.dybc.views;

import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dyb.dybc.R;
import com.zhy.zhylib.base.BaseActivity;

/**
 * 自定义 titlebar
 *
 * @author zhangyong
 */

public class TitleModule {

    private BaseActivity activity;
    public TextView title_text, rightText;
    public LinearLayout leftImageLinear, rightImageLinear;
    public ImageView rightImage,leftImage;
    public WebView webView;
    public LinearLayout common_head;

    public TitleModule(BaseActivity activity, String title) {
        this.activity = activity;
        title_text = (TextView) activity.findViewById(R.id.title);
        rightImageLinear = (LinearLayout) activity.findViewById(R.id.rightImageLinear);
        leftImage = (ImageView) activity.findViewById(R.id.leftImage);
        rightImage = (ImageView) activity.findViewById(R.id.rightImage);
        leftImageLinear = (LinearLayout) activity.findViewById(R.id.leftImageLinear);
        common_head = (LinearLayout) activity.findViewById(R.id.common_head);
        rightText = (TextView) activity.findViewById(R.id.rightText);
        leftImageLinear.setOnClickListener(click);
        if (TextUtils.isEmpty(title)) {
            setTitle("");
        } else {
            setTitle(title);
        }
    }

    public TitleModule(BaseActivity activity, WebView webView, String title) {
        this(activity, title);
        this.webView = webView;
    }

    public TitleModule(BaseActivity activity, View view, String title) {
        this.activity = activity;
        title_text = (TextView) view.findViewById(R.id.title);
        rightImageLinear = (LinearLayout) view.findViewById(R.id.rightImageLinear);
        leftImage = (ImageView) view.findViewById(R.id.leftImage);
        rightImage = (ImageView) view.findViewById(R.id.rightImage);
        leftImageLinear = (LinearLayout) view.findViewById(R.id.leftImageLinear);
        common_head = (LinearLayout) view.findViewById(R.id.common_head);
        rightText = (TextView) view.findViewById(R.id.rightText);
        leftImageLinear.setOnClickListener(click);

        setTitle(TextUtils.isEmpty(title) ? "" : title);
    }

    OnClickListener click = new OnClickListener() {
        @Override
        public void onClick(View arg0) {
            if (arg0.getId() == R.id.leftImageLinear) {
                if (webView != null && webView.canGoBack()) {
                    webView.goBack();
                } else {
                    activity.finish();
                }
            }
        }
    };

    public void setBackgroundResource(int resId) {
        common_head.setBackgroundResource(resId);
    }

    public void setLeftImageVisible(boolean isVisible) {
        leftImageLinear.setVisibility(isVisible ? View.VISIBLE : View.INVISIBLE);
    }

    public void setLeftImageResource(int imageResource) {
        leftImage.setImageResource(imageResource);
    }

    public void setLeftImageResourceListener(int imageResource, OnClickListener listener) {
        leftImage.setImageResource(imageResource);
        leftImageLinear.setOnClickListener(listener);
    }

    public void setTitle(String text) {
        title_text.setText(text);
    }

    public void setRightImageOnClickListenter(OnClickListener listenter) {
        rightImageLinear.setOnClickListener(listenter);
    }

    public void setRightImageVisible(boolean flag) {
        if (!flag) {
            rightImageLinear.setVisibility(View.INVISIBLE);
        } else {
            rightImageLinear.setVisibility(View.VISIBLE);
        }
    }

    public void setRightImageResourseOnclick(int imgId, OnClickListener listener) {
        rightImage.setImageResource(imgId);
        rightImageLinear.setOnClickListener(listener);
    }

    public void setRightImageResourse(int imgId) {
        rightImage.setImageResource(imgId);
    }

    public void setRightTextViewOnClickListener(OnClickListener listener) {
        rightText.setOnClickListener(listener);
    }

    public void setRightTextVisible(boolean flag) {
        if (!flag) {
            rightText.setVisibility(View.GONE);
            rightImageLinear.setVisibility(View.INVISIBLE);
        } else {
            rightText.setVisibility(View.VISIBLE);
            rightImageLinear.setVisibility(View.GONE);
        }
    }

    public void setRightText(String msg) {
        rightText.setText(msg);
    }

    public void setRightTextColor(int color) {
        rightText.setTextColor(color);
    }

    public void setCommonHeadVisible(boolean isVisible) {
        if (isVisible) {
            common_head.setVisibility(View.VISIBLE);
        } else {
            common_head.setVisibility(View.GONE);
        }
    }

    public void setBackGround(int color) {
        common_head.setBackgroundResource(color);
    }

    /**
     * 设置标题透明度
     *
     * @param alpha 透明度值（1-255）
     */
    public void setTitleAlpha(int alpha) {
        common_head.getBackground().setAlpha(alpha);
    }

}
