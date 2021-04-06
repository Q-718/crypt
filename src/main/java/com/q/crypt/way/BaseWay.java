package com.q.crypt.way;

import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import com.sun.org.apache.xml.internal.security.utils.Base64;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;

/**
 *base 加密方式
 * 只能算一个文件格式，一种让人不容易一眼看出来的算法
 * @Author: qyp
 * @Date: 2021/3/22 13:46
 * @Description:
 */
public class BaseWay {

    private static final char[] HEX_CHAR = {'0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /**
     * Base64 解密
     * @param key
     * @return
     * @throws IOException
     */
    public static byte[] decryptBase(String key) throws IOException {
        return (new BASE64Decoder()).decodeBuffer(key);
    }

    /**
     * 16进制转换为二进制
     * @param str
     * @return
     */
    public static byte[] toBytes(String str) {
        if(str == null || str.trim().equals("")) {
            return new byte[0];
        }

        byte[] bytes = new byte[str.length() / 2];
        for(int i = 0; i < str.length() / 2; i++) {
            String subStr = str.substring(i * 2, i * 2 + 2);
            bytes[i] = (byte) Integer.parseInt(subStr, 16);
        }

        return bytes;
    }

    /**
     * byte转换为16进制
     * @param bytes
     * @return
     */
    public static String bytesToHex(byte[] bytes) {
        char[] buf = new char[bytes.length * 2];
        int index = 0;
        for(byte b : bytes) { // 利用位运算进行转换，可以看作方法一的变种
            buf[index++] = HEX_CHAR[b >>> 4 & 0xf];
            buf[index++] = HEX_CHAR[b & 0xf];
        }

        return new String(buf);
    }

    /**
     * 加密
     * @param key
     * @return
     */
    public static String encryptBase(byte[] key){
        return (new BASE64Encoder()).encodeBuffer(key);
    }


    public static void main(String[] args) throws IOException, Base64DecodingException {
        String a="AQGNe3uSAYQVAhcQKhMxMjEwMTA1MjU1MDAwMQAAAAAAAAAAAAAAAAAAABMAAAAAAAAAAAAAAAAAAAAAAAAAAAAASMMAAEjDAABIwwAASMMAAAAAAAAAAAAAAAAAAAAAAAAAAEjhR0IAAEhDMzNjQwAAAAAAAAAAZmaXQgCAV0NVwcRDAAAAAAAAAADGHANDAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAABIQ8P1KD8AAAAAAAAAAK9HYT4zMzM/AAAAAAAAAAAAAAAAj8L1PQAAAAAAAAAAj8L1PY/Cdb0AAAAAAAAAAI/CdT0pXA8+AAAAAAAAAAApXA8+AACAPwAAAAAAAAAAWmRbPz0KVz8AAAAAAAAAAHsUrj4fhWs/mpl5QAAAAAAAAAAAAAAAAAAAAAAAAAAACtejPAAAAAAAAAAAAAAAAArXozw9Clc/AAAAAD0KVz8AAAAAAAAAAAAAAAAAAAAAAAAAAArXozwCFwo6CtejPAIXCjoAAAAAAhYAAFK43j8BDxMnhIh9fQ==";
        byte[] decode = Base64.decode(a);
        String s = bytesToHex(decode);
        System.out.println(s);
        byte[] bytes = toBytes("01018d7b7b920184150217102a133132313031303532353530303031000000000000000000000000000000130000000000000000000100000000000000000000000048c3000048c3000048c3000048c3000000000000000000000000000000000000000048e1474200004843333363430000000000000000666697420080574355c1c4430000000000000000c61c03434224c345000000000000000000000000000000000000000000004843c3f5283f0000000000000000af47613e3333333f0000000000000000000000008fc2f53d00000000000000008fc2f53d8fc275bd00000000000000008fc2753d295c0f3e0000000000000000295c0f3e0000803f00000000000000005a645b3f3d0a573f00000000000000007b14ae3e1f856b3f9a99794000000000000000000000000000000000000000000ad7a33c0000000000000000000000000ad7a33c3d0a573f000000003d0a573f00000000000000000000000000000000000000000ad7a33c02170a3a0ad7a33c02170a3a000000000216000052b8de3f010f132784887d7d");
        String encode = Base64.encode(bytes);
        System.out.println(encode);

    }
}
