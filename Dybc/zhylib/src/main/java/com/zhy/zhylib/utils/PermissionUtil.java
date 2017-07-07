package com.zhy.zhylib.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;

import java.io.File;

/**
 * Created by zhangyong on 2017/6/13.
 */

public class PermissionUtil {

    public static Uri getBuildNUri(Context context, File file) {
        Uri uri = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            uri = FileProvider.getUriForFile(context, context.getPackageName() + ".android7.fileprovider", file);
        } else {
            uri = Uri.fromFile(file);
        }
        return uri;
    }

    //检查所需要的权限
    public static boolean checkPermission(Activity activity, String[] permissions) {
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    //申请所需要的权限
    public static void requestPermission(Activity activity, String[] permissions, int code) {
        ActivityCompat.requestPermissions(activity, permissions, code);
    }

    /**
     * 打开 APP 的详情设置
     */
    public static void openAppDetails(final Activity activity) {
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(activity);
        builder.setMessage("请到设置中开启相应权限,否则部分功能无正常使用");
        builder.setPositiveButton("去手动授权", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                intent.setData(Uri.parse("package:" + activity.getPackageName()));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                activity.startActivity(intent);
            }
        });
        builder.setNegativeButton("取消", null);
        builder.show();
    }

}
