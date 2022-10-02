package com.contest.ichapp.pojo.dto.result;

import com.contest.ichapp.pojo.dto.param.AllBlockParam;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AllBlockResult {
    private List<AllBlockParam> blockParamList;
    private Integer total;
}
