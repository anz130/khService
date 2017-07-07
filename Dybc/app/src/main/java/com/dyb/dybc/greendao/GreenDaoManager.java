package com.dyb.dybc.greendao;


import com.dyb.dybc.MyApplication;

import org.greenrobot.greendao.query.QueryBuilder;

/**
 * Created by zhangyong on 2017/3/23.
 */

public class GreenDaoManager {

    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;
    private DaoMaster.DevOpenHelper devOpenHelper;
    private static volatile GreenDaoManager mInstance = null;
    private static final String DB_NAME="dybc.db";

    private GreenDaoManager() {
        if (mInstance == null) {
            DaoMaster.DevOpenHelper devOpenHelper = new
                    DaoMaster.DevOpenHelper(MyApplication.getAppContext(), DB_NAME);
            mDaoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
            mDaoSession = mDaoMaster.newSession();
        }
    }

    public static GreenDaoManager getInstance() {
        if (mInstance == null) {
            synchronized (GreenDaoManager.class) {
                if (mInstance == null) {
                    mInstance = new GreenDaoManager();
                }
            }
        }
        return mInstance;
    }

    public DaoMaster getMaster() {
        return mDaoMaster;
    }

    public DaoSession getSession() {
        return mDaoSession;
    }

    /**
     * 设置debug模式开启或关闭，默认关闭
     */
    public void setDebug(boolean flag){
        QueryBuilder.LOG_SQL = flag;
        QueryBuilder.LOG_VALUES = flag;
    }

    /**
     * 关闭数据库
     */
    public void closeDataBase(){
        closeHelper();
        closeDaoSession();
    }

    private void closeDaoSession(){
        if (null != mDaoSession){
            mDaoSession.clear();
            mDaoSession = null;
        }
    }

    private  void  closeHelper(){
        if (devOpenHelper!=null){
            devOpenHelper.close();
            devOpenHelper = null;
        }
    }
}
