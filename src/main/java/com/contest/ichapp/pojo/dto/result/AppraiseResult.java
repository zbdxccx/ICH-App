package com.contest.ichapp.pojo.dto.result;

import com.contest.ichapp.pojo.dto.param.AppraiseParam;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AppraiseResult {
    List<AppraiseParam> paramList;
    private Integer total;
}
