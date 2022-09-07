package com.contest.ichapp.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CollectedMapper {
    Integer insert(Integer collectedId, Integer collectionId);

    Integer delete(Integer collectedId, Integer collectionId);

    Integer selectToCount(Integer collectedId, Integer collectionId);
}
