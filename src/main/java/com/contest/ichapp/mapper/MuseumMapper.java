package com.contest.ichapp.mapper;

import com.contest.ichapp.pojo.domain.Museum;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MuseumMapper {
    String selectNameById(Integer museumId);

    Museum selectAllByName(String museumName);
}
