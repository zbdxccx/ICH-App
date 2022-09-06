package com.contest.ichapp.service.Impl;

import com.contest.ichapp.pojo.dto.CommonResult;
import com.contest.ichapp.service.RecommendService;
import com.contest.ichapp.util.imgUtil.ImgUtil;
import org.springframework.stereotype.Service;

@Service
public class RecommendServiceImpl implements RecommendService {
    @Override
    public CommonResult<String> recommend() {
        String img = ImgUtil.getImg("C:\\Users\\韦秉芮\\Desktop\\ICH-App\\src\\main\\resources\\static\\追韩信瓶.jpg");
        return CommonResult.success(img);
    }
}
