package com.contest.ichapp.mapper;

import com.contest.ichapp.pojo.domain.Collection;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CollectionMapper {
    Collection selectById(Integer id);
}
