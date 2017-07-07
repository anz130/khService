package com.zhy.zhylib.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by y.vn on 2016/10/31.
 */

public class DesUtils {

    private static final String T = "0";
    private static byte[] iv = {1, 2, 3, 4, 5, 6, 7, 8};

    /**
     * 加密
     * @param encryptKey    这个字符串必须是8位的
     */
    public static String encryptDES(String encryptString, String encryptKey) throws Exception {
        IvParameterSpec zeroIv = new IvParameterSpec(iv);
        SecretKeySpec key = new SecretKeySpec(encryptKey.getBytes(), "DES");
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key, zeroIv);
        byte[] encryptedData = cipher.doFinal(encryptString.getBytes("utf-8"));
        return byte2hex(encryptedData);
    }
    /**
     * 解密
     * @param decryptKey    这个字符串必须是8位的
     */
    public static String decryptDES(String decryptString, String decryptKey) throws Exception {
        byte[] byteMi = hex2byte(decryptString.getBytes());
        IvParameterSpec zeroIv = new IvParameterSpec(iv);
        SecretKeySpec key = new SecretKeySpec(decryptKey.getBytes(), "DES");
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, key, zeroIv);
        byte decryptedData[] = cipher.doFinal(byteMi);
        return new String(decryptedData, "utf-8");
    }

    /**
     * 二行制转字符串
     */
    private static String byte2hex(byte[] b) { // 一个字节的数，
        // 转成16进制字符串
        String stmp = "";
        StringBuilder info = new StringBuilder();
        final int N = 0XFF;
        for (int n = 0; n < b.length; n++) {
            // 整数转成十六进制表示
            stmp = (Integer.toHexString(b[n] & N));
            if (stmp.length() == 1) {
                info.append(T).append(stmp);
            } else {
                info.append(stmp);
            }
        }
        return info.toString().toUpperCase();
    }

    private static byte[] hex2byte(byte[] b) {
        if ((b.length % 2) != 0)
            throw new IllegalArgumentException("Argument is not even.");
        byte[] b2 = new byte[b.length / 2];
        for (int n = 0; n < b.length; n += 2) {
            String item = new String(b, n, 2);
            // 两位一组，表示一个字节,把这样表示的16进制字符串，还原成一个进制字节
            b2[n / 2] = (byte) Integer.parseInt(item, 16);
        }
        return b2;
    }
}
