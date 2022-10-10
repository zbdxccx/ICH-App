package com.contest.ichapp.service;

import com.contest.ichapp.pojo.dto.CommonResult;
import com.contest.ichapp.pojo.dto.param.AppraisePostParam;
import com.contest.ichapp.pojo.dto.param.DeleteAppraiseParam;
import com.contest.ichapp.pojo.dto.result.AppraiseResult;

import javax.servlet.http.HttpServletRequest;

public interface AppraiseService {
    CommonResult<String> appraise(HttpServletRequest request, AppraisePostParam param);

    CommonResult<AppraiseResult> viewAppraise(HttpServletRequest request, Integer collectionId, Integer pageNum);

    CommonResult<String> deleteAppraise(HttpServletRequest request, DeleteAppraiseParam param);
}
