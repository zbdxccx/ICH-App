package com.contest.ichapp.util.ConstantSmsUtil;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConstantSmsUtil implements InitializingBean {

    @Value("${tencent.sms.keyId}")
    private String secretID;
    @Value("${tencent.sms.keySecret}")
    private String secretKey;
    @Value("${tencent.sms.smsSdkAppId}")
    private String smsSdkAppID;
    @Value("${tencent.sms.signName}")
    private String signName;
    @Value("${tencent.sms.templateIdLogin}")
    private String templateIDLogin;
    @Value("${tencent.sms.templateIdRegister}")
    private String templateIDRegister;
    @Value("${tencent.sms.smsClient}")
    private String smsClient;
    @Value("${tencent.sms.url}")
    private String endPoint;


    public static String SECRET_ID;
    public static String SECRET_KEY;
    public static String SMS_SDK_APP_ID;
    public static String SIGN_NAME;
    public static String TEMPLATE_ID_LOGIN;
    public static String TEMPLATE_ID_REGISTER;
    public static String END_POINT;
    public static String SMS_CLIENT;

    @Override
    public void afterPropertiesSet() {
        SECRET_ID = secretID;
        SECRET_KEY = secretKey;
        SMS_SDK_APP_ID = smsSdkAppID;
        SIGN_NAME = signName;
        TEMPLATE_ID_LOGIN = templateIDLogin;
        TEMPLATE_ID_REGISTER = templateIDRegister;
        END_POINT = endPoint;
        SMS_CLIENT = smsClient;
    }
}
