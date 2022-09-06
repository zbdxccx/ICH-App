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
        //验证用户名和密码是否正确
        UserCheckVo userCheckVo = userMapper.selectToCheck(username, password);
        if (!userCheckVo.getCheck()) return CommonResult.wrongLogin();
        return CommonResult.success("登录成功");
    }

    @Override
    public CommonResult<String> register(LoginParam param) {
        String username = param.getUsername();
        //验证用户名是否已被使用
        UserCheckVo userCheckVo = userMapper.selectToDistinct(username);
        if (userCheckVo.getCheck()) return CommonResult.distinct();

        String password = param.getPassword();
        //存入数据库
        if (userMapper.insertByParam(username, password) == 0) return CommonResult.fail("注册失败");
        return CommonResult.success("注册成功");
    }
}
