package com.contest.ichapp.pojo.dto.param;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RecommendParam {
    private Integer id;
    private String img;
    private String name;
    private String description;
}
