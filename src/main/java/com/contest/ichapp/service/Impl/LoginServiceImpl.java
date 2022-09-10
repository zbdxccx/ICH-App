package com.contest.ichapp.service.Impl;

import com.contest.ichapp.mapper.UserMapper;
import com.contest.ichapp.pojo.dto.CommonResult;
import com.contest.ichapp.pojo.dto.param.LoginParam;
import com.contest.ichapp.pojo.dto.param.PhoneParam;
import com.contest.ichapp.pojo.dto.vo.UserCheckVo;
import com.contest.ichapp.service.LoginService;
import com.contest.ichapp.util.JWTUtil.JWTUtil;
import com.contest.ichapp.util.RandomUtil.RandomUtil;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.sms.v20210111.SmsClient;
import com.tencentcloudapi.sms.v20210111.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20210111.models.SendSmsResponse;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import static com.contest.ichapp.util.ConstantSmsUtil.ConstantSmsUtil.*;

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

    @Override
    public CommonResult<String> sendMessage(PhoneParam param) {
        try {
            // 实例化一个认证对象，入参需要传入腾讯云账户secretId，secretKey,此处还需注意密钥对的保密
            // 密钥可前往https://console.cloud.tencent.com/cam/capi网站进行获取
            Credential cred = new Credential(SECRET_ID, SECRET_KEY);
            // 实例化一个http选项，可选的，没有特殊需求可以跳过
            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint(END_POINT);
            // 实例化一个client选项，可选的，没有特殊需求可以跳过
            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);
            // 实例化要请求产品的client对象,clientProfile是可选的
            SmsClient client = new SmsClient(cred, SMS_CLIENT, clientProfile);
            // 实例化一个请求对象,每个接口都会对应一个request对象
            SendSmsRequest req = new SendSmsRequest();
            String[] phoneNumberSet1 = {param.getPhoneNum()};
            req.setPhoneNumberSet(phoneNumberSet1);

            req.setSmsSdkAppId(SMS_SDK_APP_ID);
            req.setSignName(SIGN_NAME);
            req.setTemplateId(TEMPLATE_ID_LOGIN);

            String verificationCode = RandomUtil.getSixBitRandom();

            String[] templateParamSet1 = {verificationCode};
            req.setTemplateParamSet(templateParamSet1);

            // 返回的resp是一个SendSmsResponse的实例，与请求对象对应
            SendSmsResponse resp = client.SendSms(req);
            // 输出json格式的字符串回包
            System.out.println(SendSmsResponse.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
            return CommonResult.fail("发送短信失败");
        }
        return CommonResult.success("发送短信成功");
    }
}