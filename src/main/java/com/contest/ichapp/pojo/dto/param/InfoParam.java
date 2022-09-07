package com.contest.ichapp.pojo.dto.param;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InfoParam {
    private Integer id;
    private String name;
    private String location;
    private String img;
}
