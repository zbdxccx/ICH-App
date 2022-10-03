package com.contest.ichapp.controller;

import com.contest.ichapp.pojo.dto.CommonResult;
import com.contest.ichapp.pojo.dto.param.LoginParam;
import com.contest.ichapp.pojo.dto.param.PhoneParam;
import com.contest.ichapp.service.LoginService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@CrossOrigin
public class LoginController {

    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public CommonResult<String> login(@RequestBody @Valid LoginParam param) {
        return loginService.login(param);
    }

    @PostMapping("/send")
    public CommonResult<String> sendMessage(@RequestBody @Valid PhoneParam param) {
        return loginService.sendMessage(param);
    }
}
