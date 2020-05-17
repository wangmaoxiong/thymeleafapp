package com.wmx.thymeleafapp.utils;

import sun.misc.BASE64Encoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * 新增摘要工具类
 *
 * @author wangmaoxiong
 * @version 1.0
 * @date 2020/5/16 18:48
 */
public class MessageDigestUtils {

    /**
     * 将任意字符通过 MD5 摘要 与 Base64 进行定长加密
     *
     * @param message   待加密字符
     * @param algorithm 信息生成摘要的算法，默认为  md5，可选值有  SHA-1、SHA-256、MD5
     * @return 加密后字符
     */
    public static String digestEncryption(String message, String algorithm) {
        String result = "";
        try {
            algorithm = algorithm == null ? "MD5" : algorithm;
            //指定信息摘要算法提取摘要的哈希值. 哈希值字节数组，如果直接 new String(md5Byte) 是会乱码的
            MessageDigest md5 = MessageDigest.getInstance(algorithm);
            byte[] md5Byte = md5.digest(message.getBytes());
            //使用 BASE64 进行定长编码
            BASE64Encoder base64Encoder = new BASE64Encoder();
            result = base64Encoder.encode(md5Byte);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
        String sourceMessage = "123456万里长城_&*$#.Nice";
        //原字符：123456万里长城_&*$#.Nice
        System.out.println("原字符：" + sourceMessage);

        String md5Msg = digestEncryption(sourceMessage, "md5");
        String sha1Msg = digestEncryption(sourceMessage, "SHA-1");
        String sha256Msg = digestEncryption(sourceMessage, "SHA-256");
        //md5 + base64 加密后：rIJQEL19bo+eV5p7qPLlDg==
        System.out.println("md5 + base64 加密后：" + md5Msg);
        //md5 + base64 加密后：UYg5qBdsuCdloFP+0CVPasziIEU=
        System.out.println("SHA-1 + base64 加密后：" + sha1Msg);
        //md5 + base64 加密后：dqTa6BTTvZ4zLF5WNAH5Cv660RxYMEIJlBRIGKdNXmM=
        System.out.println("SHA-256 + base64 加密后：" + sha256Msg);
    }
}