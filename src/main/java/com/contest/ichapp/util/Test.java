package com.contest.ichapp.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@SuppressWarnings("all")
public class Test {
    //图片到byte数组
    static String image2Bytes(String imgSrc) {
        byte[] imageBytes;
        try (FileInputStream fileInputStream = new FileInputStream(new File(imgSrc));) {
            imageBytes = new byte[fileInputStream.available()];
            fileInputStream.read(imageBytes);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return UnicodeByteToStr(imageBytes);
    }

    public static String UnicodeByteToStr(byte[] b) {
        StringBuilder sb = new StringBuilder();
        for (byte value : b) {
            sb.append(String.format("%02x", value));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String b1 = image2Bytes("C:\\Users\\韦秉芮\\Desktop\\default_head_img.png");
        System.out.println(b1);
    }
}
