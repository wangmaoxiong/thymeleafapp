package com.wmx.thymeleafapp.utils;

import javax.crypto.KeyGenerator;
import java.security.*;

/**
 * @author wangmaoxiong
 * @version 1.0
 * @date 2020/5/18 10:35
 */
public class SecretKeyTest {
    /**
     * 1、获取加密的密匙，传入的 slatKey 可以是任意长度的，作为 SecureRandom 的随机种子，
     * 2、在 KeyGenerator 初始化时设置密匙的长度 128bit(16 位 byte)
     *
     * @param slatKey
     * @return
     */
    private static Key getSlatKey(String slatKey) {
        Key key = null;
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            random.setSeed(slatKey.getBytes());
            keyGenerator.init(128, random);
            key = keyGenerator.generateKey();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return key;
    }

    /**
     * 根据 slatKey 获取公匙 或者 私钥，传入的 slatKey 作为 SecureRandom 的随机种子，slatKey 相同，KeyPair 生成的公匙和私匙也是同一对.
     * 如果直接使用 new SecureRandom()创建一个新对象，此时就必须记录下公匙和私匙，在解密时使用。
     *
     * @param slatKey
     * @param isPublicKey ：true 表示获取公钥，否则获取私钥
     * @return
     */
    private static byte[] getPublicOrPrivateKey(String slatKey, boolean isPublicKey) {
        byte[] encoded = new byte[]{};
        try {
            if (slatKey == null || "".equals(slatKey.trim())) {
                return encoded;
            }
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            random.setSeed(slatKey.getBytes());
            keyPairGenerator.initialize(1024, random);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            if (isPublicKey) {
                encoded = keyPair.getPublic().getEncoded();
            } else {
                encoded = keyPair.getPrivate().getEncoded();
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return encoded;
    }

}
