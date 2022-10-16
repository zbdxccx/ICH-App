package com.contest.ichapp.pojo.dto.param;


import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;


@Data
public class LoginParam {
    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "^(13[\\d]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[\\d]|19[0-35-9])\\d{8}$", message = "非手机号（截至2022运营规则）")
    private String phoneNum;
    @NotBlank
    private String verificationCode;
}
