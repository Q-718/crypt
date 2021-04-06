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
public class SHAWay {

    public static final String KEY_SHA = "SHA-256";

    /**
     * sha加密方式 与md相似 只是key不相同
     * @param key
     * @return
     */
    public static String getMDResult(String key){
        System.out.println("sha加密前的字符串；"+key);
        BigInteger sha=null;
        try {
            MessageDigest md=MessageDigest.getInstance(KEY_SHA);
            byte[] inputData = key.getBytes();

            md.update(inputData);

            sha=new BigInteger(md.digest());
        } catch (NoSuchAlgorithmException e) {
            log.error("加密异常:"+e.toString());
        }
        System.out.println("加密后的字符串:"+sha.toString());
        return sha.toString();

    }

    public static void main(String[] args) {
        String s="qiyupeng";
        getMDResult(s);

    }


}
