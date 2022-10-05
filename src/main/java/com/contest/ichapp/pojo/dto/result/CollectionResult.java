package com.contest.ichapp.pojo.dto.result;

import com.contest.ichapp.pojo.dto.vo.CollectionVo;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CollectionResult {
    private List<CollectionVo> collectionList;
    private Integer total;
}
