package com.contest.ichapp.service.Impl;

import com.contest.ichapp.mapper.LoveMapper;
import com.contest.ichapp.pojo.dto.CommonResult;
import com.contest.ichapp.pojo.dto.param.CollectParam;
import com.contest.ichapp.service.CollectService;
import com.contest.ichapp.util.jwtUtil.JWTUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Service
public class CollectServiceImpl implements CollectService {
    @Resource
    LoveMapper loveMapper;

    @Override
    public synchronized CommonResult<String> collect(CollectParam collectParam, HttpServletRequest request) {
        //鉴权
        Integer userId = JWTUtil.getUserId_X(request);
        if (userId == -1) return CommonResult.tokenWrong();
        if (userId == -2) return CommonResult.tokenNull();

        Integer collectionId = collectParam.getCollectionId();
        if (loveMapper.selectToCount(userId, collectionId) != 0) return CommonResult.fail("已收藏");
        if (loveMapper.insert(userId, collectionId) == 0) return CommonResult.fail("插入数据失败");
        else return CommonResult.success("收藏成功");
    }

    @Override
    public synchronized CommonResult<String> cancel(CollectParam collectParam, HttpServletRequest request) {
        //鉴权
        Integer userId = JWTUtil.getUserId_X(request);
        if (userId == -1) return CommonResult.tokenWrong();
        if (userId == -2) return CommonResult.tokenNull();

        Integer collectionId = collectParam.getCollectionId();
        if (loveMapper.selectToCount(userId, collectionId) == 0) return CommonResult.fail("未收藏");
        if (loveMapper.delete(userId, collectionId) == 0) return CommonResult.fail("取消收藏失败");
        else return CommonResult.success("取消收藏成功");
    }
}
