package com.contest.ichapp.util.cryptoUtil;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class StringUtil {
    /**
     * 应用SHA256算法接收输入字符串计算并返回哈希字符串
     */
    public static String SHA256(String input) throws Exception {
        //返回实现指定摘要算法的 MessageDigest 对象。此处是SHA-256算法
        MessageDigest digest = MessageDigest.getInstance("SHA-256"); //getInstance有异常
        //根据输入的bytes数组完成哈希计算。
        byte[] hash = digest.digest(input.getBytes(StandardCharsets.UTF_8));//getBytes有异常
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            //将生成的哈希字节数组每个字节（8bit）转换16进制数字符串
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                //当生成的16进制数只有一位时，在末尾添0，丢弃生成的16进制数（因为8位应是两位的16进制数，除非前面全为0）
                hexString.append("0");
            }
            //将每一个字节的转换结果连接
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
