package com.contest.ichapp.mapper;

import com.contest.ichapp.pojo.domain.UserInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserInfoMapper {
    UserInfo selectAllById(Integer userId);

    Integer setNickname(String name, Integer userId);

    Integer setSign(String sign, Integer userId);

    Integer insertById(Integer userId);
}
