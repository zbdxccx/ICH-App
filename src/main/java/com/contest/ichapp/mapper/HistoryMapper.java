package com.contest.ichapp.mapper;

import com.contest.ichapp.pojo.dto.param.HistoryParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HistoryMapper {
    Integer insertOne(Integer collectionId, Integer userId);

    List<HistoryParam> selectAllById(Integer userId);
}
