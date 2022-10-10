package com.contest.ichapp.util.imgUtil;


import com.alibaba.fastjson.JSONObject;
import com.contest.ichapp.pojo.domain.Collection;
import com.contest.ichapp.util.baiduTool.Base64Util;
import com.contest.ichapp.util.baiduTool.FileUtil;
import com.contest.ichapp.util.baiduTool.HttpUtil;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.net.URL;
import java.net.URLEncoder;

public class ImgSearchUtil {


    public static String sameHqAdd(Collection collection) {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/realtime_search/same_hq/add";


        try {
            BufferedImage read = ImageIO.read(new URL(collection.getFullImg()));
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            ImageIO.write(read, "png", stream);

            byte[] imgData = stream.toByteArray();
            String imgStr = Base64Util.encode(imgData);
            String imgParam = URLEncoder.encode(imgStr, "UTF-8");

            String param = "brief=" + JSONObject.toJSONString("id=" + collection.getId() + " name=" + collection.getName()) + "&image=" + imgParam + "&tags=" + collection.getTagId() + ",1";

            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            String accessToken = "24.4c296bc549fb1d7efb598ddaf408d312.2592000.1667998119.282335-27842220";

            String result = HttpUtil.post(url, accessToken, param);
            System.out.println(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String sameHqSearch() {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/realtime_search/same_hq/search";
        try {
            // 本地文件路径
            String filePath = "C:\\Users\\韦秉芮\\Desktop\\tset.jpeg";
            byte[] imgData = FileUtil.readFileByBytes(filePath);
            String imgStr = Base64Util.encode(imgData);
            String imgParam = URLEncoder.encode(imgStr, "UTF-8");

            String param = "image=" + imgParam;

            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            String accessToken = "24.4c296bc549fb1d7efb598ddaf408d312.2592000.1667998119.282335-27842220";

            String result = HttpUtil.post(url, accessToken, param);
            System.out.println(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}