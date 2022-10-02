package com.contest.ichapp.service;

import com.contest.ichapp.pojo.dto.CommonResult;
import com.contest.ichapp.pojo.dto.param.CheckBlockParam;
import com.contest.ichapp.pojo.dto.param.TransParam;
import com.contest.ichapp.pojo.dto.result.AllBlockResult;

import javax.servlet.http.HttpServletRequest;

public interface TransBlockService {
    CommonResult<String> transOne(HttpServletRequest request, TransParam transParam);

    CommonResult<CheckBlockParam> checkBlock(HttpServletRequest request, String transId);

    CommonResult<AllBlockResult> getCollectionBlock(HttpServletRequest request);
}
