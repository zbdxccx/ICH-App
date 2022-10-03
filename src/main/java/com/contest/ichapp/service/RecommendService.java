package com.contest.ichapp.service;

import com.contest.ichapp.pojo.dto.CommonResult;
import com.contest.ichapp.pojo.dto.param.RecommendDateParam;
import com.contest.ichapp.pojo.dto.param.RecommendParam;

import javax.servlet.http.HttpServletRequest;

public interface RecommendService {
    CommonResult<RecommendParam> recommend(HttpServletRequest request);

    CommonResult<RecommendDateParam> localTime();
}
