package com.contest.ichapp.service;

import com.contest.ichapp.pojo.dto.CommonResult;
import com.contest.ichapp.pojo.dto.result.InfoResult;
import com.contest.ichapp.pojo.dto.vo.MoreInfoVo;

import javax.servlet.http.HttpServletRequest;

public interface HomeService {
    CommonResult<InfoResult> getAllInfo(String keyword, Integer pageNum, Integer tagId, HttpServletRequest request);

    CommonResult<MoreInfoVo> getMoreInfo(Integer collectionId, HttpServletRequest request);
}
