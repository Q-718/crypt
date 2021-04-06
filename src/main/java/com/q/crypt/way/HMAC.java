package com.q.crypt.way;

import lombok.extern.slf4j.Slf4j;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;

/**
 *HMAC加密方式
 * @Author: qyp
 * @Date: 2021/3/22 15:04
 * @Description: 消息鉴别码实现鉴别的原理是，用公开函数和密钥产生一个固定长度的值作为认证标识，用这个标识鉴别消息的完整性。
 *使用一个密钥生成一个固定大小的小数据块，
 *即MAC，并将其加入到消息中，然后传输。接收方利用与发送方共享的密钥进行鉴别认证等
 */
@Slf4j
public class HMAC {

    public static final String  KEY_MAC="HmacMD5";

    /**
     * 初始化HMAC密钥
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static  String initMacKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator=KeyGenerator.getInstance(KEY_MAC);
        SecretKey secretKey=keyGenerator.generateKey();
        System.out.println("密钥:"+secretKey);
        return BaseWay.encryptBase(secretKey.getEncoded());

    }

    /**
     * HMAC加密 主要方法
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] encryptHMAC(byte[] data, String key) throws Exception {

        SecretKey secretKey=new SecretKeySpec(BaseWay.decryptBase(key),KEY_MAC);
        Mac mac=Mac.getInstance(secretKey.getAlgorithm());
        mac.init(secretKey);
        return  mac.doFinal(data);

    }

    public static String getResult(String inputStr)

    {

        System.out.println("=======加密前的数据:"+inputStr);
        BigInteger result=null;
        try {
            byte[] inputData = inputStr.getBytes();
            //产生密钥
            String key = initMacKey();
            System.out.println("Mac密钥:===" + key);
            //加密
            result=new BigInteger(encryptHMAC(inputData, key));
            System.out.println("HMAC加密后:===" + result);

        } catch (Exception e)
        {
           log.info("异常的方法:"+e.toString());
        }

        return result.toString();

    }

    public static void main(String[] args) {
        String s="qiyupeng";
        String result = getResult(s);

    }

}
