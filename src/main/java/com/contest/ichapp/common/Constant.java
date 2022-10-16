package com.contest.ichapp.common;

import org.springframework.stereotype.Component;

/**
 * @author 韦秉芮
 * 存常量
 */

@Component
public class Constant {
    public static final String GMT8 = "GMT+8";
    public static final String DATE_FORMAT = "d/M/yyyy";
    public static final String WEEK_FORMAT = "E";
    public static final String PRIVATE_SIGN = "WBR_PRIVATE_SIGN";
    public static final long EXPIRE_DATE = 1000 * 60 * 60 * 24 * 7;
    public static final int TOKEN_WRONG = -1;
    public static final int TOKEN_NULL = -2;
}
