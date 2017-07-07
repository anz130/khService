package com.zhy.zhylib.utils;


import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

/**
 * 3DES加密工具
 */
public class Des3 {
    // 密钥
    private final static String secretKey = "liuyunqiang@lx100$#365#$";
    // 向量
    private final static String iv = "01234567";
    // 加解密统�?��用的编码方式
    private final static String encoding = "utf-8";

//	/**
//	 * 3DES加密
//	 * 
//	 * @param plainText 普�?文本
//	 * @return
//	 * @throws Exception 
//	 */
//	public static String encode(String plainText) throws Exception {
//		Key deskey = null;
//		DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());
//		SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
//		deskey = keyfactory.generateSecret(spec);
//
//		Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
//		IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
//		cipher.init(Cipher.ENCRYPT_MODE, deskey, ips);
//		byte[] encryptData = cipher.doFinal(plainText.getBytes(encoding));
//		return Base64.encode(encryptData);
//	}

    /**
     * 3DES解密
     *
     * @param encryptText 加密文本
     * @return
     * @throws Exception
     */
    public static String decode(String encryptText) throws Exception {
        Key deskey = null;
        DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
        deskey = keyfactory.generateSecret(spec);
        Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
        IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
        cipher.init(Cipher.DECRYPT_MODE, deskey, ips);

        byte[] decryptData = cipher.doFinal(Base64_2.decode(encryptText));

        return new String(decryptData, encoding);
    }

    public static void main(String[] args) {
        try {
            //待加密内�?
            String str = "{'userTel':'15301586743','password':'123456'}";
            //   System.out.println("加密前内容为�?+new String(str));

            String result = "";
            //System.out.println("加密后内容为�?+new String(result));
            //直接将如上内容解�?
            String decryResult = Des3.decode(result);
            //  System.out.println("解密后内容为�?+new String(decryResult));
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
}
