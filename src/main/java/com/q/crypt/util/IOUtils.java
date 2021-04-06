package com.q.crypt.util;

import java.io.*;

/**
 * 文件的读取和写入
 * @Author: qyp
 * @Date: 2021/4/6 9:46
 * @Description:
 */

public class IOUtils {

    /**
     * 写文件
     * @param data
     * @param file
     * @throws IOException
     */
    public static void writeFile(String data, File file) throws IOException {
        OutputStream out = null;
        try {
            out = new FileOutputStream(file);
            out.write(data.getBytes());
            out.flush();
        } finally {
            close(out);
        }
    }

    /**
     * 读文件
     * @param file
     * @return
     * @throws IOException
     */
    public static String readFile(File file) throws IOException {
        InputStream in = null;
        ByteArrayOutputStream out = null;
        try {
            in = new FileInputStream(file);
            out = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int len = -1;
            while ((len = in.read(buf)) != -1) {
                out.write(buf, 0, len);
            }
            out.flush();
            byte[] data = out.toByteArray();
            return new String(data);
        } finally {
            close(in);
            close(out);
        }
    }

    /**
     * 关闭
     * @param c
     */
    public static void close(Closeable c) {
        if (c != null) {
            try {
                c.close();
            } catch (IOException e) {
                // nothing
            }
        }
    }
}
