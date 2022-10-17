package com.contest.ichapp.mapper;

import com.contest.ichapp.pojo.domain.Collection;
import com.contest.ichapp.pojo.dto.vo.MoreInfoVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CollectionMapper {
    Collection selectById(Integer id);

    Collection randByTagId(Integer tagId);

    MoreInfoVo selectAllInfoById(Integer id);

    Collection selectAllInfoByTransId(String transId);

    List<Collection> selectAll();

    Integer selectCountAll();

    Integer updateUrl(String url, Integer id);

    List<Collection> selectAllLike(String keyword);

    Integer insertOne(String name, String description, String location, Integer dynastyId, Integer museumId, Integer tagId, String feature, String more);
}
