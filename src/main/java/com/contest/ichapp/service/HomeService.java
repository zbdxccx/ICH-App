package com.contest.ichapp.service;

import com.contest.ichapp.pojo.dto.CommonResult;
import com.contest.ichapp.pojo.dto.result.InfoResult;

public interface HomeService {
    CommonResult<InfoResult> getAllInfo();
}
