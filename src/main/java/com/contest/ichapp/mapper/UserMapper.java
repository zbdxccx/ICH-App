package com.contest.ichapp.mapper;

import com.contest.ichapp.pojo.domain.User;
import com.contest.ichapp.pojo.dto.vo.UserCheckVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    UserCheckVo selectToCheck(String username);

    UserCheckVo selectToDistinct(String username);

    Integer selectUserIdByUsername(String username);

    Integer insertByParam(String username);

    List<Integer> selectAllId();

    User selectById(Integer userId);

}
