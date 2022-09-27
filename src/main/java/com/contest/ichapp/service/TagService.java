package com.contest.ichapp.service;

import com.contest.ichapp.pojo.dto.CommonResult;
import com.contest.ichapp.pojo.dto.result.TagResult;

public interface TagService {
    CommonResult<TagResult> getAllTag();
}
