package com.contest.ichapp.pojo.dto.param;

import lombok.Data;

@Data
public class AddParam {
    private String name;
    private String description;
    private Integer dynastyId;
    private Integer tagId;
    private Integer museumId;
    private String location;
    private String feature;
    private String more;
}
