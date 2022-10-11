package com.contest.ichapp.service;

import com.contest.ichapp.pojo.domain.UserInfo;
import com.contest.ichapp.pojo.dto.CommonResult;
import com.contest.ichapp.pojo.dto.param.PersonalParam;
import com.contest.ichapp.pojo.dto.result.CollectionResult;
import com.contest.ichapp.pojo.dto.result.HistoryResult;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface PersonalHomeService {

    CommonResult<CollectionResult> getAllCollection(HttpServletRequest request);

    CommonResult<HistoryResult> getAllHistory(HttpServletRequest request);

    CommonResult<UserInfo> getPersonalInfo(HttpServletRequest request);

    CommonResult<String> setNameAndSign(HttpServletRequest request, PersonalParam param);

    CommonResult<String> setHeadImg(HttpServletRequest request, MultipartFile file);
}
