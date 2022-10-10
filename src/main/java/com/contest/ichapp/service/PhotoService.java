package com.contest.ichapp.service;

import com.contest.ichapp.pojo.domain.Collection;
import com.contest.ichapp.pojo.dto.CommonResult;
import com.contest.ichapp.pojo.dto.param.StringParam;

import javax.servlet.http.HttpServletRequest;

public interface PhotoService {
    CommonResult<String> addImg(Integer collectionId);

    CommonResult<Collection> searchImg(HttpServletRequest request,  StringParam param);
}
