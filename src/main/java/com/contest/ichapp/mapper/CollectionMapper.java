package com.contest.ichapp.mapper;

import com.contest.ichapp.pojo.domain.Collection;
import com.contest.ichapp.pojo.dto.vo.MoreInfoVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CollectionMapper {
    Collection selectById(Integer id);
    MoreInfoVo selectAllInfoById(Integer id);

    List<Collection> selectAll();
    Integer selectCountAll();

    List<Collection> selectAllLike(String keyword);
}
