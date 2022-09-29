package com.contest.ichapp.pojo.domain;

import lombok.Data;

@Data
public class Collection {
    private Integer id;
    private String name;
    private String description;
    private Integer tagId;
    private Integer museumId;
    private String location;
    private String img;
    private String fullImg;
}
