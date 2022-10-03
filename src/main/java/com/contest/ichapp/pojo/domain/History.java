package com.contest.ichapp.pojo.domain;

import lombok.Data;

@Data
public class History {
    private Integer id;
    private Integer userId;
    private Integer collectionId;
    private String time;
    private Integer count;
}
