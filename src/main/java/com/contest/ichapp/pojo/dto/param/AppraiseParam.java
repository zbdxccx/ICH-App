package com.contest.ichapp.pojo.dto.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppraiseParam {
    private Integer id;
    private Integer userId;
    private String userName;
    private String headUrl;
    private String time;
    private String appraise;
}