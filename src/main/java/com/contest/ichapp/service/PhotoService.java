package com.contest.ichapp.service;

import com.contest.ichapp.pojo.dto.CommonResult;

import javax.servlet.http.HttpServletRequest;

public interface PhotoService {
    CommonResult<String> addImg(Integer collectionId);

    CommonResult<String> searchImg(HttpServletRequest request, String ImgBase64);
}
