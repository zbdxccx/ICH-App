package com.contest.ichapp.service.Impl;

import com.contest.ichapp.mapper.LoveMapper;
import com.contest.ichapp.pojo.domain.Collection;
import com.contest.ichapp.pojo.dto.CommonResult;
import com.contest.ichapp.pojo.dto.result.CollectionResult;
import com.contest.ichapp.service.PersonalHomeService;
import com.contest.ichapp.util.JWTUtil.JWTUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class PersonalHomeServiceImpl implements PersonalHomeService {
    @Resource
    LoveMapper loveMapper;

    @Override
    public CommonResult<CollectionResult> getAllCollection(HttpServletRequest request) {
        String token = JWTUtil.getToken(request);
        Integer userId = JWTUtil.getUserId(token);

        List<Collection> collections = loveMapper.selectByUserId(userId);
        CollectionResult result = new CollectionResult(collections, collections.size());

        return CommonResult.success(result);
    }
}
