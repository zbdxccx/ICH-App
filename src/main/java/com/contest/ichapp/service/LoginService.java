package com.contest.ichapp.service;

import com.contest.ichapp.pojo.dto.CommonResult;
import com.contest.ichapp.pojo.dto.param.LoginParam;

public interface LoginService {
    CommonResult<String> login(LoginParam param);

    CommonResult<String> register(LoginParam param);
}
