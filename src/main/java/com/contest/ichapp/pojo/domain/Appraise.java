package com.contest.ichapp.pojo.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Appraise {
    private Integer id;
    private Integer collectionId;
    private Integer userId;
    private String appraise;
    private String time;
}
