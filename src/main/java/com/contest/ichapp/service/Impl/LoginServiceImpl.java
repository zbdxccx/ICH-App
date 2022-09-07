package com.contest.ichapp.service.Impl;

import com.contest.ichapp.mapper.UserMapper;
import com.contest.ichapp.pojo.dto.CommonResult;
import com.contest.ichapp.pojo.dto.param.LoginParam;
import com.contest.ichapp.pojo.dto.vo.UserCheckVo;
import com.contest.ichapp.service.LoginService;
import com.contest.ichapp.util.JWTUtil.JWTUtil;
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
        String username = param.getUsername();
        String password = param.getPassword();

        Integer userId = userMapper.selectUserIdByUsername(username);

        //生成token
        String token = JWTUtil.createToken(userId);
        Cookie cookie = new Cookie("token", token);
        response.addCookie(cookie);

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
