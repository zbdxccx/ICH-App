package com.contest.ichapp.pojo.dto.resultEnum;

import lombok.Getter;

@Getter
public enum ResultEnum {

    SUCCESS("00000", "请求正常"),
    FAILED("A0400", "请求失败"),
    WRONG_USER_NAME("B0501", "用户名错误"),
    WRONG_PASSWORD("B0502", "密码错误");

    private final String code;
    private final String message;

    ResultEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
