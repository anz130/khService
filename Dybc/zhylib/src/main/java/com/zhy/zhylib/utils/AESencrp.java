package com.zhy.zhylib.utils;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;


/**
 * 用来进行AES的加密和解密程序
 */
public class AESencrp {
    // 加密算法
    private static final String ALGO = "AES";
    // 加密密钥 16长度
    private static final String key = "yciiAESkey123456";
    // 16位的加密密钥
    private static final byte[] keyValue = key.getBytes();

    private final static AESencrp INSTANCE = new AESencrp();

    // 单例
    private AESencrp() {
    }

    public static AESencrp getInstance() {
        return INSTANCE;
    }

    /**
     * 用来进行加密的操作
     *
     * @param Data
     * @return
     * @throws Exception
     */
    public String encrypt(String Data) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encVal = c.doFinal(Data.getBytes());
        String encryptedValue = Base64.encode(encVal);
        return encryptedValue;
    }

    /**
     * 用来进行解密的操作
     *
     * @param encryptedData
     * @return
     * @throws Exception
     */
    public String decrypt(String encryptedData) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.DECRYPT_MODE, key);
        byte[] decordedValue = Base64.decode(encryptedData);
        byte[] decValue = c.doFinal(decordedValue);
        String decryptedValue = new String(decValue);
        return decryptedValue;
    }

    /**
     * 根据密钥和算法生成Key
     *
     * @return
     * @throws Exception
     */
    private Key generateKey() throws Exception {
        Key key = new SecretKeySpec(keyValue, ALGO);
        return key;
    }
}
