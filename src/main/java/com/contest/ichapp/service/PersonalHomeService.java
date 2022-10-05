package com.contest.ichapp.service;

import com.contest.ichapp.pojo.domain.UserInfo;
import com.contest.ichapp.pojo.dto.CommonResult;
import com.contest.ichapp.pojo.dto.param.StringParam;
import com.contest.ichapp.pojo.dto.result.CollectionResult;
import com.contest.ichapp.pojo.dto.result.HistoryResult;

import javax.servlet.http.HttpServletRequest;

public interface PersonalHomeService {

    CommonResult<CollectionResult> getAllCollection(HttpServletRequest request);

    CommonResult<HistoryResult> getAllHistory(HttpServletRequest request);

    CommonResult<UserInfo> getPersonalInfo(HttpServletRequest request);

    CommonResult<String> setName(HttpServletRequest request, StringParam param);

    CommonResult<String> setSign(HttpServletRequest request, StringParam param);
}
