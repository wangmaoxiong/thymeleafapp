package com.wmx.thymeleafapp.utils;

import sun.misc.BASE64Encoder;

import java.io.File;
import java.io.FileInputStream;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 新增摘要工具类
 *
 * @author wangmaoxiong
 * @version 1.0
 * @date 2020/5/16 18:48
 */
public class Md5EncodeUtils {

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
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            byte[] md5Byte = messageDigest.digest(message.getBytes());
            //使用 BASE64 进行定长编码
            BASE64Encoder base64Encoder = new BASE64Encoder();
            result = base64Encoder.encode(md5Byte);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * 对文件提取摘要
     *
     * @param file      ：待提取摘要的文件
     * @param algorithm 生成摘要的算法，默认为  md5，可选值有  SHA-1、SHA-256、MD5
     * @return
     */
    public static String md5DigestByFile(File file, String algorithm) {
        String result = "";
        try {
            algorithm = algorithm == null ? "MD5" : algorithm;
            //构建文件输入流，然后获取文件通道。
            FileInputStream fileInputStream = new FileInputStream(file);
            //FileChannel 用于读取，写入，映射和操作文件的通道。
            FileChannel fileChannel = fileInputStream.getChannel();
            /**
             * map(MapMode mode,long position, long size)：将此频道文件的区域直接映射到内存中。
             * position - 映射区域要启动的文件中的位置; 必须是非负的
             * size - 要映射的区域的大小; 必须是非负数，不得大于Integer.MAX_VALUE
             */
            MappedByteBuffer byteBuffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, file.length());
            //指定信息摘要算法提取摘要的哈希值. 哈希值字节数组，如果直接 new String(md5Byte) 是会乱码的
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            //使用指定的 ByteBuffer 更新摘要.
            messageDigest.update(byteBuffer);
            //提取摘要，然后将十进制的字节数组转成 16 进制可视字符串.
            //通常在网上下载的资源，对方提供对照的 md5 码，或者 sha 码，也是使用 16 进制转换的.
            result = bytes2HexString(messageDigest.digest(), false);
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 10 进制字节数组转 16 进制字符串（因为16进制中含有A-F，所以只能用字符串表示）
     * 对应上面的 hexString2Bytes 方法
     *
     * @param b           :待转换的10进制字节数组，如 new byte[]{69, 83, 67, 47, 86, 80, 46, 110, 101, 116, 16, 3, 0, 0, 0, 0};
     * @param isHaveBlank 转换的结果是否用空格隔开，true 时如 "45 5A 43 2F 56 00"，false 时如 "455A432F5600"
     * @return 转换好的 16进制字符串，如 "45 53 43 2F 56 50 2E 6E 65 74 10 03 00 00 00 00 "
     */
    private static String bytes2HexString(byte[] b, boolean isHaveBlank) {
        if (b == null || b.length <= 0) {
            System.out.println("bytes2HexString 参数错误，放弃转换.");
            return null;
        }
        StringBuffer result = new StringBuffer();
        String hex;
        for (int i = 0; i < b.length; i++) {
            hex = Integer.toHexString(b[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            if (isHaveBlank) {
                result.append(hex.toUpperCase() + " ");
            } else {
                result.append(hex.toUpperCase());
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {

        new Md5EncodeUtils().test1();
//        new Md5EncodeUtils().test2();

    }

    public void test2() {
        File file = new File("C:\\Users\\Think\\Downloads\\commons-io-2.6-bin.tar.gz");
        String fileMd5Digest = md5DigestByFile(file, "SHA-256");
        System.out.println("fileMd5Digest: " + fileMd5Digest.toLowerCase());
    }

    public void test1() {
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