package com.contest.ichapp.mapper;

import com.contest.ichapp.pojo.domain.TransInfo;
import com.contest.ichapp.pojo.dto.param.AllBlockParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TransInfoMapper {
    TransInfo selectById(String transId);

    List<AllBlockParam> selectByUserId(Integer userId);

    Integer updateUser(String transId, Integer userId, Integer collectionId);

    Integer countForConfirm(String transId, Integer userId);

}
