package com.contest.ichapp.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoveMapper {
    Integer insert(Integer userId, Integer collectionId);

    Integer delete(Integer userId, Integer collectionId);

    Integer selectToCount(Integer userId, Integer collectionId);
}
