package com.dyb.dybc.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * 解决listview 和 scrollview共存
 * @author zhangyong
 */
public class ListViewInScroll extends ListView {

    public ListViewInScroll(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }

    public ListViewInScroll(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
    }

    public ListViewInScroll(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int mExpandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, mExpandSpec);
    }

}
