package com.utils.aes;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

import sun.misc.BASE64Decoder;

/**
 * AES加密算法
 * 注意：
 * 1、加密密钥必须是128位，这里是用SecureRandom随机数根据种子生成128位随机数作为密钥
 * 2、密钥加密和解密的内容必须是"同一内容"，否则报错
 */
public class AESUtil {
    //密钥
    private static final String encodeRules = "shaoniandedao";

    public static String AESEncode(String content) {
        try {
            //1.构造密钥生成器，指定生成的是AES加密算法的密钥,不区分大小写
            KeyGenerator keygen = KeyGenerator.getInstance("AES");
            //2.使用"SHA1PRNG"算法(解析种子)生成随机数;ps:我自己的理解，瞎想的
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            //3.添加种子
            random.setSeed(encodeRules.getBytes());
            //4.初始化密钥生成器：根据由种子(ecnodeRules)生成的128位随机数初始化密钥生成器(使用AES算法将128位的随机数计算成为密钥)
            keygen.init(128, random);

//            //5.根据传入的128位字节数组产生原始对称密钥
//            SecretKey original_key = keygen.generateKey();
//            //6.获得原始对称密钥的字节数组
//            byte[] raw = original_key.getEncoded();
//            //7.根据字节数组生成AES密钥
//            SecretKey key = new SecretKeySpec(raw, "AES");
            //5.根据传入的128位字节数组生成密钥
            SecretKey key=keygen.generateKey();
            //6.指定算法AES生成密码加密/解密器
            Cipher cipher = Cipher.getInstance("AES");
            //7.初始化密码加密/解密器，第一个参数为加密(Encrypt_mode)或者解密解密(Decrypt_mode)操作，第二个参数为使用的密钥
            cipher.init(Cipher.ENCRYPT_MODE, key);
            //8.获取加密内容的字节数组(这里要设置为utf-8)不然内容中如果有中文和英文混合中文就会解密为乱码
            byte[] byte_encode = content.getBytes("utf-8");
            //9.根据密码器的初始化方式--加密：将数据加密
            byte[] byte_AES = cipher.doFinal(byte_encode);
            //10.将加密后的数据转换为字符串
            //这里用Base64Encoder中会找不到包
            //解决办法：
            //在项目的Build path中先移除JRE System Library，再添加库JRE System Library，重新编译后就一切正常了。
//            String AES_encode = new BASE64Encoder().encode(byte_AES);
            String AES_encode =Base64.getEncoder().encodeToString(byte_AES);
            //11.将字符串返回
            return AES_encode;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //如果有错就返加nulll
        return null;
    }



    /**
     * AES加密
     * @param content 待加密的内容
     * @param encryptKey 加密密钥
     * @return 加密后的byte[]
     * @throws Exception
     */
    public static byte[] aesEncryptToBytes(String content, String encryptKey) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(128, new SecureRandom(encryptKey.getBytes()));
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(kgen.generateKey().getEncoded(), "AES"));
        return cipher.doFinal(content.getBytes("utf-8"));
    }

    /**
     * 解密
     * 解密过程：
     * 1.同加密1-4步
     * 2.将加密后的字符串反纺成byte[]数组
     * 3.将加密内容解密
     */
    public static String AESDecode(String content) {
        try {
            //1.构造密钥生成器，指定为AES算法,不区分大小写
            KeyGenerator keygen = KeyGenerator.getInstance("AES");
            //2.根据ecnodeRules规则初始化密钥生成器
            //生成一个128位的随机源,根据传入的字节数组
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            random.setSeed(encodeRules.getBytes());
            keygen.init(128, random);
            //3.产生原始对称密钥
            SecretKey original_key = keygen.generateKey();
            //4.获得原始对称密钥的字节数组
            byte[] raw = original_key.getEncoded();
            //5.根据字节数组生成AES密钥
            SecretKey key = new SecretKeySpec(raw, "AES");
            //6.根据指定算法AES自成密码器
            Cipher cipher = Cipher.getInstance("AES");
            //7.初始化密码器，第一个参数为加密(Encrypt_mode)或者解密(Decrypt_mode)操作，第二个参数为使用的KEY
            cipher.init(Cipher.DECRYPT_MODE, key);
            //8.将加密并编码后的内容解码成字节数组
            byte[] byte_content = new BASE64Decoder().decodeBuffer(content);
            /*
             * 解密
             */
            byte[] byte_decode = cipher.doFinal(byte_content);
            String AES_decode = new String(byte_decode, "utf-8");
            return AES_decode;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        //如果有错就返加nulll
        return null;
    }

    public static void main(String[] args) {
        String pwd="hello1234";
        String pwd2="4ESjiJaBAPO1ffS3HUr72w==";
        System.out.println(AESEncode(pwd));
        System.out.println(AESDecode(pwd2));
    }
}
