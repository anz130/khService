package com.zhy.zhylib.utils;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Rect;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Environment;
import android.provider.Settings;
import android.provider.Settings.SettingNotFoundException;
import android.telephony.TelephonyManager;
import android.text.ClipboardManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.io.File;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class DeviceUtils {
    private static final String TAG = DeviceUtils.class.getSimpleName();

    /**
     * 获取屏幕宽度
     */
    public static int getScreenWidth(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }

    /**
     * 获取屏幕高度
     */
    public static int getScreenHeight(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.heightPixels;
    }

    /**
     * 网络是否可用
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm == null) {
            return false;
        }
        NetworkInfo mNetworkInfo = cm.getActiveNetworkInfo();
        if (mNetworkInfo != null) {
            return mNetworkInfo.isAvailable();
        }
        return false;
    }

    /**
     * GPS是否关闭
     */
    public static boolean hasOpenedGPS(Context context) {
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        return (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER));
    }

    /**
     * 获取可用存储路径
     */
    public static File getAvailableStorageDirectory(Context context) {
        File directory = null;
        if (isSDcardAvailable()) {
            directory = Environment.getExternalStorageDirectory();
        } else {
            directory = context.getFilesDir();
        }
        return directory;
    }

    /**
     * SD卡是否可用
     */
    public static boolean isSDcardAvailable() {
        try {
            return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 获取MAC地址
     */
    public static String getLocalMacAddress(Context context) {
        WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifi.getConnectionInfo();
        return info.getMacAddress();
    }

    /**
     * 获取本机IP
     *
     * @return
     */
    public static String getLocalIpAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        return inetAddress.getHostAddress();
                    }
                }
            }
        } catch (SocketException ex) {
            Log.e(TAG, ex.toString());
        }
        return null;
    }

    /**
     * 获取设备deviceId
     */
    public static String getDeviceId(Context mContext) {
        TelephonyManager tm = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getDeviceId();
    }

    /**
     * 获得本机手机号码
     * 只能获取那些很古老的sim卡的电话号码
     */
    public static String getMobile(Context mContext) {
        TelephonyManager tm = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getLine1Number();//获取本机号码
    }

    /**
     * 获取设备信息
     */
    public static String getDeviceInfo(Context mContext) {
        TelephonyManager tm = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
        StringBuilder sb = new StringBuilder();
        sb.append("{\"DeviceId(IMEI)\":\"" + tm.getDeviceId());
        sb.append("\",\"DeviceSoftwareVersion\":\"" + tm.getDeviceSoftwareVersion());
        sb.append("\",\"Line1Number\":\"" + tm.getLine1Number());
        sb.append("\",\"NetworkCountryIso\":\"" + tm.getNetworkCountryIso());
        sb.append("\",\"NetworkOperator\":\"" + tm.getNetworkOperator());
        sb.append("\",\"NetworkOperatorName\":\"" + tm.getNetworkOperatorName());
        sb.append("\",\"NetworkType\":\"" + tm.getNetworkType());
        sb.append("\",\"PhoneType\":\"" + tm.getPhoneType());
        sb.append("\",\"SimCountryIso\":\"" + tm.getSimCountryIso());
        sb.append("\",\"SimOperator\":\"" + tm.getSimOperator());
        sb.append("\",\"SimOperatorName\":\"" + tm.getSimOperatorName());
        sb.append("\",\"SimSerialNumber\":\"" + tm.getSimSerialNumber());
        sb.append("\",\"SimState\":\"" + tm.getSimState());
        sb.append("\",\"SubscriberId(IMSI)\":\"" + tm.getSubscriberId());
        sb.append("\",\"VoiceMailNumber\":\"" + tm.getVoiceMailNumber() + "\"}");
        return sb.toString();
    }

    /**
     * 显示键盘
     */
    public static void showSoftInput(View v) {
        v.setFocusable(true);
        v.setFocusableInTouchMode(true);
        v.requestFocus();
        InputMethodManager inputManager = (InputMethodManager) v.getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.showSoftInput(v, 0);
    }

    /**
     * 隐藏键盘
     */
    public static void hideSoftInput(View v) {
        InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(v, InputMethodManager.SHOW_FORCED);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

    public static void setScreenRotation(ContentResolver cr, boolean rotation) {
        Settings.System.putInt(cr, Settings.System.ACCELEROMETER_ROTATION, rotation ? 1 : 0);
    }

    public static boolean isScreenRotation(ContentResolver cr) throws SettingNotFoundException {
        return Settings.System.getInt(cr, Settings.System.ACCELEROMETER_ROTATION) == 1;
    }

    /**
     * 获取状态栏高度
     */
    public static int getStatusBarHeight(Activity activity) {
        Rect frame = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        return frame.top;
    }

    /**
     * 实现文本复制功能
     */
    public static void copy(String content, Context context) {
        // 得到剪贴板管理器
        ClipboardManager cmb = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        cmb.setText(content.trim());
    }
}
