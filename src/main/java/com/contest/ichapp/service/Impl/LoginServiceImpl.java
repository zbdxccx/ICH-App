package com.contest.ichapp.service.Impl;

import com.contest.ichapp.mapper.UserMapper;
import com.contest.ichapp.pojo.dto.CommonResult;
import com.contest.ichapp.pojo.dto.param.LoginParam;
import com.contest.ichapp.pojo.dto.vo.UserCheckVo;
import com.contest.ichapp.service.LoginService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class LoginServiceImpl implements LoginService {
    @Resource
    UserMapper userMapper;

    @Override
    public CommonResult<String> login(LoginParam param) {
        String username = param.getUsername();
        String password = param.getPassword();
        UserCheckVo userCheckVo = userMapper.selectToCheck(username, password);
        if (!userCheckVo.getCheck()) return CommonResult.wrongLogin();
        return CommonResult.success("登录成功");
    }
}
