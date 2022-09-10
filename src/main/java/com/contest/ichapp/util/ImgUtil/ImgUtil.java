package com.contest.ichapp.util.ImgUtil;

import lombok.Cleanup;
import sun.misc.BASE64Encoder;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ImgUtil {

    public static String getImg(String imgFilePath) {
        File file = new File(imgFilePath);
        byte[] imgStr = new byte[0];
        try {
            @Cleanup FileInputStream fileStream = new FileInputStream(file);
            @Cleanup ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len;
            while ((len = fileStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, len);
            }
            imgStr = outStream.toByteArray();

        } catch (IOException e) {
            e.printStackTrace();
        }
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(imgStr);
    }
}
