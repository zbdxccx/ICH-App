package com.contest.ichapp.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserInfoMapper {
    Integer selectToCount(Integer userId);


}
