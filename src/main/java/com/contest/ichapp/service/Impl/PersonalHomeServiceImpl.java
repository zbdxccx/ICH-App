package com.contest.ichapp.service.Impl;

import com.contest.ichapp.mapper.HistoryMapper;
import com.contest.ichapp.mapper.LoveMapper;
import com.contest.ichapp.pojo.domain.Collection;
import com.contest.ichapp.pojo.dto.CommonResult;
import com.contest.ichapp.pojo.dto.param.HistoryParam;
import com.contest.ichapp.pojo.dto.result.CollectionResult;
import com.contest.ichapp.pojo.dto.result.HistoryResult;
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
    @Resource
    HistoryMapper historyMapper;

    @Override
    public CommonResult<CollectionResult> getAllCollection(HttpServletRequest request) {
        String token = JWTUtil.getToken(request);
        Integer userId = JWTUtil.getUserId(token);
        List<Collection> collections = loveMapper.selectByUserId(userId);

        return CommonResult.success(new CollectionResult(collections, collections.size()));
    }

    @Override
    public CommonResult<HistoryResult> getAllHistory(HttpServletRequest request) {
        String token = JWTUtil.getToken(request);
        Integer userId = JWTUtil.getUserId(token);
        List<HistoryParam> histories = historyMapper.selectAllById(userId);

        return CommonResult.success(new HistoryResult(histories));
    }
}
