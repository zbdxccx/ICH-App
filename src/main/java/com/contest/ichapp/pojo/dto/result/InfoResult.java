package com.contest.ichapp.pojo.dto.result;

import com.contest.ichapp.pojo.dto.param.InfoParam;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class InfoResult {
    List<InfoParam> infoList;
}
