package com.contest.ichapp.service;

import com.contest.ichapp.pojo.dto.CommonResult;
import com.contest.ichapp.pojo.dto.param.LoginParam;

import javax.servlet.http.HttpServletResponse;

public interface LoginService {
    CommonResult<String> login(LoginParam param, HttpServletResponse response);

    CommonResult<String> register(LoginParam param);
}
