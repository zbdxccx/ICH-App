package com.contest.ichapp.mapper;

import com.contest.ichapp.pojo.domain.UserInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserInfoMapper {
    UserInfo selectAllById(Integer userId);

    Integer setNameAndSign(String name,String sign, Integer userId);

    Integer setHeadUrl(String headUrl, Integer userId);

    Integer insertById(Integer userId);
}
