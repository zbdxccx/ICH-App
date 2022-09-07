package com.contest.ichapp.pojo.dto.resultEnum;

import lombok.Getter;

@Getter
public enum ResultEnum {

    SUCCESS("00000", "请求正常"),
    FAILED("A0400", "请求失败"),
    WRONG_LOGIN("B0502", "用户名或密码错误"),
    DISTINCT("C0500", "该用户名已被注册"),
    TOKEN_WRONG("D0400", "token过期"),
    TOKEN_NULL("D0500", "token不存在");

    private final String code;
    private final String message;

    ResultEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
