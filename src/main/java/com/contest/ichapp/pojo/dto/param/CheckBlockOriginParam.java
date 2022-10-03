package com.contest.ichapp.pojo.dto.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckBlockOriginParam {
    private String museum;
    private String checkId;
    private String transId;
    private Integer transNum;
}
