package com.contest.ichapp.mapper;

import com.contest.ichapp.pojo.domain.TransInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TransInfoMapper {
    Integer updateAll(Integer collectionId, Integer userId, String transId, String transBlock);

    TransInfo selectById(String transId);

}
