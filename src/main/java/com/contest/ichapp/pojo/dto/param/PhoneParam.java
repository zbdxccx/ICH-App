package com.contest.ichapp.pojo.dto.param;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class PhoneParam {
    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "^(13[\\d]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[\\d]|19[0-35-9])\\d{8}$", message = "非手机号（截至2022运营规则）")
    private String phoneNum;
    @NotNull
    private Integer type;
}
