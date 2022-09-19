package com.contest.ichapp.pojo.dto.result;

import com.contest.ichapp.pojo.domain.Collection;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CollectionResult {
    private List<Collection> collectionList;
    private Integer total;
}
