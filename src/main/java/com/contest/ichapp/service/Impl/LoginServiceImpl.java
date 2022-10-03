package com.contest.ichapp.service.Impl;

import com.contest.ichapp.mapper.UserMapper;
import com.contest.ichapp.pojo.dto.CommonResult;
import com.contest.ichapp.pojo.dto.param.LoginParam;
import com.contest.ichapp.pojo.dto.param.PhoneParam;
import com.contest.ichapp.pojo.dto.vo.UserCheckVo;
import com.contest.ichapp.service.LoginService;
import com.contest.ichapp.service.cacheService.CacheService;
import com.contest.ichapp.util.JWTUtil.JWTUtil;
import com.contest.ichapp.util.sendMessageUtil.SendMessageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class LoginServiceImpl implements LoginService {
    @Resource
    private UserMapper userMapper;
    private final CacheService cacheService;

    @Autowired
    public LoginServiceImpl(CacheService cacheService) {
        this.cacheService = cacheService;
    }

    @Override
    public CommonResult<String> login(LoginParam param) {
        String phoneNum = param.getPhoneNum();
        //校验验证码
//        if (!checkVerificationCode(param)) return CommonResult.fail("验证码错误");
        UserCheckVo userCheckVoRegister = userMapper.selectToDistinct(phoneNum);
        if (!userCheckVoRegister.getCheck()) {
            if (userMapper.insertByParam(phoneNum) == 0) log.info("注册失败");
            else log.info("注册成功");
        } else {
            UserCheckVo userCheckVo = userMapper.selectToCheck(phoneNum);
            //验证用户名和密码是否正确
            if (!userCheckVo.getCheck()) return CommonResult.wrongLogin();
        }
        Integer userId = userMapper.selectUserIdByUsername(phoneNum);
        //生成token
        String token = JWTUtil.createToken(userId);
        return CommonResult.success("登录成功", token);
    }

    @Override
    public CommonResult<String> sendMessage(PhoneParam param) {
        String verificationCode = cacheService.updateVerificationCode(param.getPhoneNum());
        int type;
        if (userMapper.selectUserIdByUsername(param.getPhoneNum()) == null) type = 2;
        else type = 1;
        String typeMessage = null;
        if (type == 1) typeMessage = "登录";
        if (type == 2) typeMessage = "注册";
        Boolean flag = SendMessageUtil.sendMessage("+86" + param.getPhoneNum(), verificationCode, type);
        if (!flag) return CommonResult.fail(typeMessage + "验证码发送失败");
        //todo Store verification code with redis
        return CommonResult.success(typeMessage + "验证码发送成功");
    }

    private Boolean checkVerificationCode(LoginParam param) {
        String phoneNum = param.getPhoneNum();
        String verificationCode = param.getVerificationCode();
        String verificationCodeCache = cacheService.getVerificationCode(phoneNum);
        return verificationCodeCache.equals(verificationCode);
    }
}