package com.contest.ichapp.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MuseumMapper {
    String selectNameById(Integer museumId);
}
