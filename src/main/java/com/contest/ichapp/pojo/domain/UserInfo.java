package com.contest.ichapp.pojo.domain;

import lombok.Data;

@Data
public class UserInfo {
    private Integer id;
    private Integer userId;
    private String sign;
    private Integer collectedId;
    private Integer historyId;
}
