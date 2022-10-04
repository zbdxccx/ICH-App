package com.contest.ichapp.pojo.dto.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InfoParam {
    private Integer id;
    private String name;
    private String location;
    private String img;
    private Integer height;
    private Integer width;
    private Boolean isLove;
}
