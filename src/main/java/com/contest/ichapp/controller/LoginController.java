package com.contest.ichapp.controller;

import com.contest.ichapp.pojo.dto.CommonResult;
import com.contest.ichapp.pojo.dto.param.LoginParam;
import com.contest.ichapp.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
public class LoginController {

    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public CommonResult<String> login(@RequestBody LoginParam param, HttpServletResponse response) {
        Cookie cookie = new Cookie("sessionId", "testCookie123456");
        response.addCookie(cookie);
        return loginService.login(param);
    }

    @PostMapping("/register")
    public CommonResult<String> register(@RequestBody LoginParam param) {
        return loginService.register(param);
    }
}
