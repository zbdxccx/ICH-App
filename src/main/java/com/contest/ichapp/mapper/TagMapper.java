package com.contest.ichapp.mapper;

import com.contest.ichapp.pojo.domain.Tag;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TagMapper {
    List<Tag> selectAll();

}
