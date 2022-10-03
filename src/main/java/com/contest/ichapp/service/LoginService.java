package com.contest.ichapp.service;

import com.contest.ichapp.pojo.dto.CommonResult;
import com.contest.ichapp.pojo.dto.param.LoginParam;
import com.contest.ichapp.pojo.dto.param.PhoneParam;

public interface LoginService {
    CommonResult<String> login(LoginParam param);

    CommonResult<String> sendMessage(PhoneParam param);
}
