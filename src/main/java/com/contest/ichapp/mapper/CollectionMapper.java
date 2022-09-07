package com.contest.ichapp.mapper;

import com.contest.ichapp.pojo.domain.Collection;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CollectionMapper {
    Collection selectById(Integer id);

    List<Collection> selectAll();
}
