package com.manage.tavern.utils;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * @Author dll
 * @create 2021/3/10 13:07
 * @describe ecb加解密工具类
 */
public class EcbUtils {


    private final static String password = "2020081720200817";

    public static String decryptAES(String content) throws Exception {
        byte[] contentNew = Base64.decodeBase64(content);
        SecretKeySpec skeySpec = new SecretKeySpec(password.getBytes("UTF-8"), "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, skeySpec);
        return new String(cipher.doFinal(contentNew));
    }
}
