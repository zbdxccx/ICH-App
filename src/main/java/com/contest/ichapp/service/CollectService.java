package com.contest.ichapp.service;

import com.contest.ichapp.pojo.dto.CommonResult;
import com.contest.ichapp.pojo.dto.param.CollectParam;

public interface CollectService {
    CommonResult<String> collect(CollectParam collectParam);

    CommonResult<String> cancel(CollectParam collectParam);


}
