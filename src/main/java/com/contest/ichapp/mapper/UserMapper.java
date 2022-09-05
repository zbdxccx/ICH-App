package com.contest.ichapp.mapper;

import com.contest.ichapp.pojo.dto.vo.UserCheckVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    UserCheckVo selectToCheck(String username, String password);
}
