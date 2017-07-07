package com.dyb.dybc.listener;

import com.baidu.location.BDLocation;

/**
 * Created by zhangyong on 2017/7/1.
 */

public interface LocationListener{
    void onReceiveLocation(BDLocation bdLocation);
}
