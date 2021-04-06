package com.q.crypt.way;

import lombok.extern.slf4j.Slf4j;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @Author: qyp
 * @Date: 2021/3/22 14:05
 * @Description:
 */
@Slf4j
public class MDWay {

    public static final String KEY_MD = "MD5";

    /**
     * md加密   该方法不可逆
     * @param key
     * @return
     */
    public static String getMDResult(String key){
        System.out.println("加密前的字符串；"+key);
        BigInteger big=null;
        try {
            MessageDigest md=MessageDigest.getInstance(KEY_MD);
            byte[] inputData = key.getBytes();

            md.update(inputData);

            big=new BigInteger(md.digest());
        } catch (NoSuchAlgorithmException e) {
            log.error("加密异常:"+e.toString());
        }
        System.out.println("加密后的字符串:"+big);
        return big.toString();

    }

    public static void main(String[] args) {
        String s="qiyupeng";
        getMDResult(s);

    }


}
