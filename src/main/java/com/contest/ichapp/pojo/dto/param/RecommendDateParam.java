package com.contest.ichapp.pojo.dto.param;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RecommendDateParam {
    private String calendar;
    private String weekNum;
    private String time;
}
