package com.shangyong.thjdq.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.commons.codec.binary.Base64;

/**
 * RSATool
 *
 * <p>生成私钥: openssl genrsa -out rsa_private_key.pem 1024 </p>
 * <p>生成公钥: openssl rsa -in rsa_private_key.pem -pubout -out rsa_public_key.pem </p>
 * <p>生成java版私钥: openssl pkcs8 -topk8 -inform PEM -in rsa_private_key.pem -outform PEM -nocrypt -out
 * rsa_private_key_pkcs8.pem </p>
 *
 * @author hejiangshan on 2018年12月19日
 * @version 1.0
 */
public class RSATool {

    /**
     * RSA最大加密明文大小
     */
    private static final int MAX_ENCRYPT_BLOCK = 117;

    /** */
    /**
     * RSA最大解密密文大小
     */
    private static final int MAX_DECRYPT_BLOCK = 128;

    /**
     * 签名算法
     */
    public static final String ALGORITHM_RSA_SIGN = "SHA256WithRSA";

    /**
     * 随机生成密钥对
     */
    public static KeyPair genKeyPair() {
        KeyPairGenerator keyPairGen = null;
        try {
            keyPairGen = KeyPairGenerator.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
        keyPairGen.initialize(1024, new SecureRandom());
        KeyPair keyPair = keyPairGen.generateKeyPair();
        return keyPair;
    }

    /**
     * 公钥字符串转成PublicKey实例
     *
     * @param publicKey 公钥字符串(base64编码)
     */
    public static RSAPublicKey parseRSAPublicKey(String publicKey) throws Exception {
        byte[] keyBytes = Base64.decodeBase64(publicKey);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return (RSAPublicKey) keyFactory.generatePublic(keySpec);
    }

    /**
     * 公钥字符串转成PublicKey实例
     *
     * @param privateKey 私钥字符串(base64编码)
     */
    public static RSAPrivateKey parseRSAPrivateKey(String privateKey) throws Exception {
        byte[] keyBytes = Base64.decodeBase64(privateKey);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return (RSAPrivateKey) keyFactory.generatePrivate(pkcs8KeySpec);
    }

    /**
     * 使用公钥解密
     *
     * @param publicKey  公钥
     * @param cipherData 密文数据
     */
    public static byte[] rsaDecryptByPublicKey(RSAPublicKey publicKey, byte[] cipherData) throws Exception {
        if (publicKey == null) {
            throw new Exception("解密公钥为空");
        }
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, publicKey);
            return doByCipher(cipher, cipherData, MAX_DECRYPT_BLOCK);
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("无此解密算法");
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
            return null;
        } catch (InvalidKeyException e) {
            throw new Exception("解密公钥非法");
        } catch (IllegalBlockSizeException e) {
            throw new Exception("密文长度非法");
        } catch (BadPaddingException e) {
            throw new Exception("密文数据已损坏");
        }
    }

    /**
     * 使用私钥加密
     *
     * @param privateKey    私钥
     * @param plainTextData 明文数据
     *
     * @return
     *
     * @throws Exception 加密过程中的异常信息
     */
    public static byte[] rsaEncryptByPrivateKey(RSAPrivateKey privateKey, byte[] plainTextData) throws Exception {
        if (privateKey == null) {
            throw new Exception("加密私钥为空");
        }
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);
            return doByCipher(cipher, plainTextData, MAX_ENCRYPT_BLOCK);
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("无此加密算法");
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
            return null;
        } catch (InvalidKeyException e) {
            throw new Exception("加密私钥非法");
        } catch (IllegalBlockSizeException e) {
            throw new Exception("明文长度非法");
        } catch (BadPaddingException e) {
            throw new Exception("明文数据已损坏");
        }
    }

    private static byte[] doByCipher(final Cipher cipher, final byte[] data, final int blockSize)
            throws IOException, BadPaddingException, IllegalBlockSizeException {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            final int length = data.length;
            int offset = 0;
            while (offset < length) {
                int size = length - offset > blockSize ? blockSize : length - offset;
                byte[] cache = cipher.doFinal(data, offset, size);
                out.write(cache, 0, cache.length);
                offset += size;
            }
            return out.toByteArray();
        }
    }

    /**
     * 使用SHA256WithRSA算法签名
     *
     * @param privateKey 私钥
     * @param dataBytes  待签名的byte数组
     *
     * @return
     */
    public static byte[] rsaSignByPrivateKey(RSAPrivateKey privateKey, byte[] dataBytes) throws Exception {
        try {
            Signature signature = Signature.getInstance(ALGORITHM_RSA_SIGN);
            signature.initSign(privateKey);
            signature.update(dataBytes);
            return signature.sign();
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("无此签名算法");
        } catch (SignatureException e) {
            throw new Exception("加签异常", e);
        } catch (InvalidKeyException e) {
            throw new Exception("加签私钥非法");
        }
    }

    /**
     * 使用SHA256WithRSA算法验签
     *
     * @param publicKey 公钥
     * @param dataBytes 待签名的byte数组
     * @param signBytes 签名的byte数组
     *
     * @return
     */
    public static boolean rsaVerifySignByPublicKey(RSAPublicKey publicKey, byte[] dataBytes, byte[] signBytes)
            throws Exception {
        try {
            Signature signature = Signature.getInstance(ALGORITHM_RSA_SIGN);
            signature.initVerify(publicKey);
            signature.update(dataBytes);
            return signature.verify(signBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("无此签名算法");
        } catch (InvalidKeyException e) {
            throw new Exception("验签公钥非法");
        } catch (SignatureException e) {
            throw new Exception("验签异常", e);
        }
    }

}
