package com.contest.ichapp.service;

import com.contest.ichapp.pojo.dto.CommonResult;
import com.contest.ichapp.pojo.dto.param.MuseumParam;

public interface MuseumService {
    CommonResult<MuseumParam> getMuseumInfo(String museumName);
}
