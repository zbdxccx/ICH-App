package com.contest.ichapp.pojo.dto.param;


import lombok.Data;

import javax.validation.constraints.NotNull;


@Data
public class LoginParam {
    @NotNull
    private String phoneNum;
    @NotNull
    private String password;
}
