package com.wmx.thymeleafapp.utils;

import java.util.Arrays;

/**
 * 16 进制转换
 *
 * @author wangmaoxiong
 * @version 1.0
 * @date 2020/5/17 15:41
 */
public class DataTypeConvertUtils {

    /**
     * 普通字符串转 16 进制字符串。
     *
     * @param strSource 待转换字符串，如 "45 5A 43 2F 56 00"
     *                  每一个字符转换后都是2个16进制字符，如 4对34,5对35，空格对20...
     * @return 转换好的16进制字符串 ，如 "3435203541203433203246203536203030",每两位对应一个原始字符
     */
    public static String string2HexString(String strSource) {
        if (strSource == null || "".equals(strSource.trim())) {
            System.out.println("string2HexString 参数为空，放弃转换.");
            return null;
        }
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < strSource.length(); i++) {
            int ch = (int) strSource.charAt(i);
            String strHex = Integer.toHexString(ch);
            hexString.append(strHex);
        }
        return hexString.toString();
    }

    /**
     * 16进制字符串转普通字符串
     * 与上面的 string2HexString 方法对应
     *
     * @param strSource 16进制字符串，如 "3435203541203433203246203536203030"
     * @return 转换好的普通字符串 ，如 "45 5A 43 2F 56 00"
     */
    public static String hexString2String(String strSource) {
        if (strSource == null || "".equals(strSource.trim())) {
            System.out.println("hexString2String 参数为空，放弃转换.");
            return null;
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < strSource.length() / 2; i++) {
            sb.append((char) Integer.valueOf(strSource.substring(i * 2, i * 2 + 2), 16).byteValue());
        }
        return sb.toString();
    }

    /**
     * 16进制字符串转十进制字节数组。对结果进行 new String 即可转为字符串
     * 这是常用的方法，如某些硬件的通信指令就是提供的16进制字符串，发送时需要转为字节数组再进行发送
     *
     * @param strSource 16进制字符串，如 "455A432F5600"，每两位对应字节数组中的一个10进制元素
     *                  默认会去除参数字符串中的空格，所以参数 "45 5A 43 2F 56 00" 也是可以的
     * @return 十进制字节数组, 如 [69, 90, 67, 47, 86, 0]. 对结果进行 new String 即可转为字符串
     */
    public static byte[] hexString2Bytes(String strSource) {
        if (strSource == null || "".equals(strSource.trim())) {
            System.out.println("hexString2Bytes 参数为空，放弃转换.");
            return null;
        }
        strSource = strSource.replace(" ", "");
        int l = strSource.length() / 2;
        byte[] ret = new byte[l];
        for (int i = 0; i < l; i++) {
            ret[i] = Integer.valueOf(strSource.substring(i * 2, i * 2 + 2), 16).byteValue();
        }
        return ret;
    }

    /**
     * 支持任意字符。
     * 10 进制字节数组转 16 进制字符串（因为16进制中含有A-F，所以只能用字符串表示）
     * 对应上面的 hexString2Bytes 方法
     *
     * @param b           :待转换的10进制字节数组，如 new byte[]{69, 83, 67, 47, 86, 80, 46, 110, 101, 116, 16, 3, 0, 0, 0, 0};
     * @param isHaveBlank 转换的结果是否用空格隔开，true 时如 "45 5A 43 2F 56 00"，false 时如 "455A432F5600"
     * @return 转换好的 16进制字符串，如 "45 53 43 2F 56 50 2E 6E 65 74 10 03 00 00 00 00 "
     */
    public static String bytes2HexString(byte[] b, boolean isHaveBlank) {
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

    /**
     * 字符串转 16 进制字符串（因为16进制中含有A-F，所以只能用字符串表示）
     *
     * @param b
     * @param isHaveBlank
     * @return
     */
    public static String bytes2HexString(String b, boolean isHaveBlank) {
        if (b == null || "".equals(b.trim())) {
            System.out.println("bytes2HexString 参数错误，放弃转换.");
            return null;
        }
        return bytes2HexString(b.getBytes(), isHaveBlank);
    }

    public static void main(String[] args) {
        String ordinaryString = "455A432F5600";
        String hexString_result = string2HexString(ordinaryString);
        //原字符串：455A432F5600
        System.out.println("原字符串：" + ordinaryString);
        //转换后16进制字符串：343535413433324635363030
        System.out.println("转换后16进制字符串：" + hexString_result);
        //16进制字符串反转普通字符串：455A432F5600
        System.out.println("16进制字符串反转普通字符串：" + hexString2String(hexString_result));

        String hexString = "45 53 43 2F 56 50 2E 6E 65 74 10 03 00 00 00 00 ";
        byte[] bytes = hexString2Bytes(hexString);
        //原16进制字符串：45 53 43 2F 56 50 2E 6E 65 74 10 03 00 00 00 00
        System.out.println("\n原16进制字符串：" + hexString);
        //转换好的10进制字节数组：[69, 83, 67, 47, 86, 80, 46, 110, 101, 116, 16, 3, 0, 0, 0, 0]
        System.out.println("转换好的10进制字节数组：" + Arrays.toString(bytes));
        //10进制数组反转16进制字符串：45 53 43 2F 56 50 2E 6E 65 74 10 03 00 00 00 00
        System.out.println("10进制数组反转16进制字符串：" + bytes2HexString(bytes, true));

        String content = "万里长城今犹在，不见当年秦始皇";
        String bytes2HexString = bytes2HexString(content.getBytes(), false);
        //e4b887e9878ce995bfe59f8ee4bb8ae78ab9e59ca8efbc8ce4b88de8a781e5bd93e5b9b4e7a7a6e5a78be79a87
        System.out.println(bytes2HexString);
        byte[] hexString2Bytes = hexString2Bytes(bytes2HexString);
        //万里长城今犹在，不见当年秦始皇
        System.out.println(new String(hexString2Bytes));
    }
}