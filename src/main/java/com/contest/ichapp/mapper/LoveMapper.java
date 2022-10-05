package com.contest.ichapp.mapper;

import com.contest.ichapp.pojo.dto.vo.CollectionVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LoveMapper {
    Integer insert(Integer userId, Integer collectionId);

    Integer delete(Integer userId, Integer collectionId);

    Integer selectToCount(Integer userId, Integer collectionId);

    List<CollectionVo> selectByUserId(Integer userId);
}
