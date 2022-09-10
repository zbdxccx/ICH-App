package com.contest.ichapp.service.Impl;

import com.contest.ichapp.mapper.UserMapper;
import com.contest.ichapp.pojo.dto.CommonResult;
import com.contest.ichapp.pojo.dto.param.LoginParam;
import com.contest.ichapp.pojo.dto.vo.UserCheckVo;
import com.contest.ichapp.service.LoginService;
import com.contest.ichapp.util.JWTUtil.JWTUtil;
import com.contest.ichapp.util.SendMessageUtil.SendMessageUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Service
public class LoginServiceImpl implements LoginService {
    @Resource
    UserMapper userMapper;

    @Override
    public CommonResult<String> login(LoginParam param, HttpServletResponse response) {
        String phoneNum = param.getPhoneNum();
        String password = param.getPassword();

        Integer userId = userMapper.selectUserIdByUsername(phoneNum);

        //生成token
        String token = JWTUtil.createToken(userId);
        Cookie cookie = new Cookie("token", token);
        response.addCookie(cookie);

        //验证用户名和密码是否正确
        UserCheckVo userCheckVo = userMapper.selectToCheck(phoneNum, password);
        if (!userCheckVo.getCheck()) return CommonResult.wrongLogin();
        return CommonResult.success("登录成功");
    }

    @Override
    public CommonResult<String> register(LoginParam param) {
        String phoneNum = param.getPhoneNum();
        String verificationCode = SendMessageUtil.sendMessage(phoneNum);
        if ("E0400".equals(verificationCode)) return CommonResult.fail("验证码发送失败");
        //TODO Store verification code with redis

        //验证用户名是否已被使用
        UserCheckVo userCheckVo = userMapper.selectToDistinct(phoneNum);
        if (userCheckVo.getCheck()) return CommonResult.distinct();

        String password = param.getPassword();
        //存入数据库
        if (userMapper.insertByParam(phoneNum, password) == 0) return CommonResult.fail("注册失败");
        return CommonResult.success("注册成功");
    }
}