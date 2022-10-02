package com.contest.ichapp.pojo.dto;

import com.contest.ichapp.pojo.dto.resultEnum.ResultEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
public class CommonResult<T> {

    /**
     * 响应参数
     */
    private String code;
    private String message;
    private T data;

    /**
     * 请求成功
     */
    public static <T> CommonResult<T> success() {
        return new CommonResult<>(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage(), null);
    }

    /**
     * 返回成功消息通知
     *
     * @param message 成功信息
     */
    public static <T> CommonResult<T> success(String message) {
        return new CommonResult<>(ResultEnum.SUCCESS.getCode(), message, null);
    }

    /**
     * 返回成功结果
     *
     * @param data 成功结果
     * @param <T>  返回对象封装类
     * @return 返回请求对象
     */
    public static <T> CommonResult<T> success(T data) {
        return new CommonResult<>(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage(), data);
    }


    /**
     * 请求失败
     */
    public static <T> CommonResult<T> fail() {
        return new CommonResult<>(ResultEnum.FAILED.getCode(), ResultEnum.FAILED.getMessage(), null);
    }

    /**
     * 返回失败消息通知
     *
     * @param message 错误信息
     */
    public static <T> CommonResult<T> fail(String message) {
        return new CommonResult<>(ResultEnum.FAILED.getCode(), message, null);
    }

    /**
     * 用户名或密码错误
     */
    public static <T> CommonResult<T> wrongLogin() {
        return new CommonResult<>(ResultEnum.WRONG_LOGIN.getCode(), ResultEnum.WRONG_LOGIN.getMessage(), null);
    }

    public static <T> CommonResult<T> distinct() {
        return new CommonResult<>(ResultEnum.DISTINCT.getCode(), ResultEnum.DISTINCT.getMessage(), null);
    }

    public static <T> CommonResult<T> tokenWrong() {
        return new CommonResult<>(ResultEnum.TOKEN_WRONG.getCode(), ResultEnum.TOKEN_WRONG.getMessage(), null);
    }

    public static <T> CommonResult<T> tokenNull() {
        return new CommonResult<>(ResultEnum.TOKEN_NULL.getCode(), ResultEnum.TOKEN_NULL.getMessage(), null);
    }

    public static <T> CommonResult<T> checkSuccess(T data) {
        return new CommonResult<>(ResultEnum.CHECK_SUCCEED.getCode(), ResultEnum.CHECK_SUCCEED.getMessage(), data);
    }
}
