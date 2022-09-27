package com.contest.ichapp.service;

import com.contest.ichapp.pojo.dto.CommonResult;
import com.contest.ichapp.pojo.dto.param.CollectParam;

import javax.servlet.http.HttpServletRequest;

public interface CollectService {
    CommonResult<String> collect(CollectParam collectParam, HttpServletRequest request);

    CommonResult<String> cancel(CollectParam collectParam, HttpServletRequest request);

}
