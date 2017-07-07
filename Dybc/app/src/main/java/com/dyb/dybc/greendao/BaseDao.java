package com.dyb.dybc.greendao;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangyong on 2017/3/23.
 */

public class BaseDao<T> {

    private final String TAG = BaseDao.class.getSimpleName();
    private static GreenDaoManager greenDaoManager;
    private static BaseDao baseDao;

    public static BaseDao getInstance() {
        if (null == baseDao) {
            synchronized (BaseDao.class) {
                baseDao = new BaseDao();
                greenDaoManager = GreenDaoManager.getInstance();
            }
        }
        return baseDao;
    }

    /**
     * 插入单个对象
     */
    public boolean insertOrReplace(T object){
        boolean flag = false;
        try {
            flag = greenDaoManager.getSession().insertOrReplace(object) != -1 ? true:false;
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
            Log.e(TAG, e.toString());
        }
        return flag;
    }

    /**
     * 插入多个对象，并开启新的线程
     */
    public boolean insertMultObject(final List<T> objects){
        boolean flag = false;
        if (null == objects || objects.isEmpty()){
            return false;
        }
        try {
            greenDaoManager.getSession().runInTx(new Runnable() {
                @Override
                public void run() {
                    for (T object : objects) {
                        greenDaoManager.getSession().insertOrReplace(object);
                    }
                }
            });
            flag = true;
        } catch (Exception e) {
            Log.e(TAG, e.toString());
            flag = false;
        }finally {
//            manager.CloseDataBase();
        }
        return flag;
    }

    /**
     * 以对象形式进行数据修改
     * 其中必须要知道对象的主键ID
     */
    public void  updateObject(T object){

        if (null == object){
            return ;
        }
        try {
            greenDaoManager.getSession().update(object);
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
    }

    /**
     * 批量更新数据
     */
    public void updateMultObject(final List<T> objects, Class clss){
        if (null == objects || objects.isEmpty()){
            return;
        }
        try {

            greenDaoManager.getSession().getDao(clss).updateInTx(new Runnable() {
                @Override
                public void run() {
                    for(T object:objects){
                        greenDaoManager.getSession().update(object);
                    }
                }
            });
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
    }

    /**
     * 删除某个数据库表
     */
    public boolean deleteAll(Class clss){
        boolean flag = false;
        try {
            greenDaoManager.getSession().deleteAll(clss);
            flag = true;
        } catch (Exception e) {
            Log.e(TAG, e.toString());
            flag = false;
        }
        return flag;
    }

    /**
     * 删除某个对象
     */
    public void deleteObject(T object){
        try {
            greenDaoManager.getSession().delete(object);
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
    }

    /**
     * 异步批量删除数据
     */
    public boolean deleteMultObject(final List<T> objects, Class clss){
        boolean flag = false;
        if (null == objects || objects.isEmpty()){
            return false;
        }
        try {

            greenDaoManager.getSession().getDao(clss).deleteInTx(new Runnable() {
                @Override
                public void run() {
                    for(T object:objects){
                        greenDaoManager.getSession().delete(object);
                    }
                }
            });
            flag = true;
        } catch (Exception e) {
            Log.e(TAG, e.toString());
            flag = false;
        }
        return flag;
    }

    /**
     * 获得某个表名
     */
    public String getTablename(Class object){
        return greenDaoManager.getSession().getDao(object).getTablename();
    }

    /**
     * 查询某个ID的对象是否存在
     */
    public boolean isExitObject(long id,Class cla){
        Object object = greenDaoManager.getSession().getDao(cla).loadByRowId(id);
        return object == null  ? false:true;
    }

    /**
     * 根据主键ID来查询
     */
    public T QueryById(long id,Class object){
        return (T) greenDaoManager.getSession().getDao(object).loadByRowId(id);
    }

    /**
     * 查询某条件下的对象
     */
    public List<T> QueryObject(Class object,String where,String...params){
        Object obj = null;
        List<T> objects = null;
        try {
            obj = greenDaoManager.getSession().getDao(object);
            if (null == obj){
                return null;
            }
            objects = greenDaoManager.getSession().getDao(object).queryRaw(where,params);
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }

        return objects;
    }

    /**
     * 查询所有对象
     */
    public List<T> QueryAll(Class object){
        List<T> list = new ArrayList<>();
        try {
            list = (List<T>) greenDaoManager.getSession().getDao(object).loadAll();
        } catch (Exception e) {
            Log.e(TAG,e.toString());
        }
        return list;
    }

    /**
     * 关闭数据库一般在Odestory中使用
     */
    public void CloseDataBase(){
        greenDaoManager.closeDataBase();
    }
}
