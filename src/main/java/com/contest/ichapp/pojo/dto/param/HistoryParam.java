package com.contest.ichapp.pojo.dto.param;

import lombok.Data;

@Data
public class HistoryParam {
    private Integer id;
    private Integer collectionId;
    private String img;
    private String name;
    private String description;
    private String time;
    private Integer count;
    private Boolean isLove;
}
