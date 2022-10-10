package com.contest.ichapp.mapper;

import com.contest.ichapp.pojo.dto.param.AppraiseParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AppraiseMapper {
    Integer insertOne(Integer collectionId, Integer userId, String appraise, String time);

    Integer deleteOne(Integer collectionId, Integer userId, String time);

    List<AppraiseParam> selectAllByCollectionId(Integer collectionId, Integer userId);

}
