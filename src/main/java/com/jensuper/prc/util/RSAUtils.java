package com.jensuper.prc.util;

import sun.misc.BASE64Decoder;

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * 加密工具类
 */
public class RSAUtils {

    //公钥加密
    public static String encrypt(String key, String content) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");//java默认"RSA"="RSA/ECB/PKCS1Padding"
        PublicKey publicKey = getPublicKey(key);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        Base64.Encoder encoder = Base64.getEncoder();
        return encoder.encodeToString(cipher.doFinal(content.getBytes()));
    }

    private static PublicKey getPublicKey(String key) throws Exception {
        byte[] keyBytes;
        keyBytes = (new BASE64Decoder()).decodeBuffer(key);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(keySpec);
        return publicKey;
    }

}
