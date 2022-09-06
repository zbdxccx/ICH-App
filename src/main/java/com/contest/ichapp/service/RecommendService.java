package com.contest.ichapp.service;

import com.contest.ichapp.pojo.dto.CommonResult;
import com.contest.ichapp.pojo.dto.param.RecommendDateParam;
import com.contest.ichapp.pojo.dto.param.RecommendParam;

public interface RecommendService {
    CommonResult<RecommendParam> recommend();

    CommonResult<RecommendDateParam> localTime();
}
