package com.wmx.thymeleafapp.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * BASE64 编解码工具类
 *
 * @author wangmaoxiong
 * @version 1.0
 * @date 2020/5/16 18:44
 */
public class Base64Utils {
    /**
     * BASE64 编码
     * BASE64 有自己的编码表，可编码，同时可解解码；长度是不固定的，随着源字符串的增加而增加
     * 1、比如登陆的密码单纯只有 BASE64 转码的话，是可以再解密的，但是用了 MD5 摘要之后则不能再返回了，
     * 2、在某些不方便使用中文的地方，可以通过 BASE64 转码成可见字符，需要中文时再解码回来即可，如 Cookie 中是不能存储中文的
     *
     * @param message 待编码的源数据
     * @return BASE64 编码后的字符串.
     */
    public static String base64Encode(String message) {
        String result = "";
        try {
            if (message == null || "".equals(message.trim())) {
                return result;
            }
            result = base64Encode(message.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * BASE64Encoder 编码，重载方法.
     *
     * @param message
     * @return
     */
    public static String base64Encode(byte[] message) {
        String result = "";
        if (message == null || message.length <= 0) {
            return result;
        }
        /** BASE64Encoder 用于编码、BASE64Decoder 用于解码*/
        BASE64Encoder base64Encoder = new BASE64Encoder();
        result = base64Encoder.encode(message);
        return result;
    }

    /**
     * BASE64 解码
     * 1、BASE64 有自己的编码表，可编码，同时可解密；长度是不固定的，随着源字符串的增加而增加
     * 2、比如登陆的密码单纯只有 BASE64 的转码的话，是可以再解密返回的，但是用了 MD5 摘要之后则不能再返回了，
     * 3、在不方便使用中文的地方，可以通过 BASE64 转码成可见字符，需要中文时再解码回来即可，如 Cookie 中是不能存储中文的
     *
     * @param message 待解码的字符串，如果为空，则返回空字符串.
     * @return
     */
    public static String base64Decode(String message) {
        String result = "";
        try {
            if (message == null || "".equals(message.trim())) {
                return result;
            }
            /** BASE64Encoder 用于编码、BASE64Decoder 用于解码*/
            BASE64Decoder base64Decoder = new BASE64Decoder();
            byte[] decoderByte = base64Decoder.decodeBuffer(message);
            result = new String(decoderByte, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
        String sourceMessage = "123456万里长城_&*$#.Nice";
        String encodeMsg = base64Encode(sourceMessage);
        String decodeMsg = base64Decode(encodeMsg);

        //原字符：123456万里长城_&*$#.Nice
        System.out.println("原字符：" + sourceMessage);
        //base64 编码后：MTIzNDU25LiH6YeM6ZW/5Z+OXyYqJCMuTmljZQ==
        System.out.println("base64 编码后：" + encodeMsg);
        //base64 解码后(可以解码)：123456万里长城_&*$#.Nice
        System.out.println("base64 解码后：" + decodeMsg);
    }
}
