package com.contest.ichapp.mapper;

import com.contest.ichapp.pojo.domain.Museum;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LikeToGoMapper {
    Integer insert(Integer userId, Integer museumId);

    Integer delete(Integer userId, Integer museumId);

    List<Museum> selectByUserId(Integer userId);
}
