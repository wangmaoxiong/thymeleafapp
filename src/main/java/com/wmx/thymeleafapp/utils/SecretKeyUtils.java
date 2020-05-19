package com.wmx.thymeleafapp.utils;

import javax.crypto.KeyGenerator;
import java.security.*;

/**
 * 密钥 key 生成工具类
 *
 * @author wangmaoxiong
 * @version 1.0
 * @date 2020/5/18 10:35
 */
public class SecretKeyUtils {

    public static void main(String[] args) {
        Key wmx = generateSecretKey("wmx");
        System.out.println(wmx.getAlgorithm());
    }

    /**
     * 1、获取加密的密匙，传入的 slatKey 可以是任意长度的，作为 SecureRandom 的随机种子，
     * 2、在 KeyGenerator 初始化时设置密匙的长度 128bit(16 位 byte)
     *
     * @param slatKey
     * @return
     */
    private static Key generateSecretKey(String slatKey) {
        Key key = null;
        try {
            //DES 时 must be equal to 56
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            random.setSeed(slatKey.getBytes());
            //java.security.InvalidParameterException: Wrong keySize: must be equal to 128, 192 or 256
            keyGenerator.init(256, random);
            key = keyGenerator.generateKey();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return key;
    }

    /**
     * 生成 RSA 密钥对：公钥（PUBLIC_KEY）、私钥：PRIVATE_KEY
     *
     * @param secureRandomSeed ：SecureRandom 随机数生成器的种子，只要种子相同，则生成的公钥、私钥就是同一对.
     *                         <p>randomSeed 长度可以自定义，加/解密必须是同一个.</p>
     * @return
     */
    public static KeyPair generateRsaKeyPair(String secureRandomSeed) {
        //KeyPair 是密钥对（公钥和私钥）的简单持有者。加密、解密都需要使用.
        KeyPair keyPair = null;
        try {
            //获取生成 RSA 加密算法的公钥/私钥对 KeyPairGenerator 对象
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            //获取实现指定算法（SHA1PRNG）的随机数生成器（RNG）对象.
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            //重新设定此随机对象的种子.
            secureRandom.setSeed(secureRandomSeed.getBytes());
            /**
             * initialize(int keySize, SecureRandom random):使用给定的随机源（random）初始化特定密钥大小的密钥对生成器。
             * keySize: 健的大小值，这是一个特定于算法的度量。值越大，能加密的内容就越多，否则会抛异常：javax.crypto.IllegalBlockSizeException: Data must not be longer than xxx bytes
             * 如 keySize 为 2048 时加密数据的长度不能超过 245 字节。
             */
            keyPairGenerator.initialize(1024, secureRandom);
            //genKeyPair(): 生成密钥对
            keyPair = keyPairGenerator.genKeyPair();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return keyPair;
    }
}
