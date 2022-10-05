package com.contest.ichapp.pojo.dto.vo;

import lombok.Data;

@Data
public class CollectionVo {
    private Integer id;
    private String name;
    private String description;
    private Integer tagId;
    private Integer museumId;
    private String location;
    private String img;
    private String fullImg;
    private Boolean isLove;
}
