package com.contest.ichapp.service.Impl;

import com.contest.ichapp.mapper.LoveMapper;
import com.contest.ichapp.pojo.dto.CommonResult;
import com.contest.ichapp.pojo.dto.param.CollectParam;
import com.contest.ichapp.service.CollectService;
import com.contest.ichapp.util.JWTUtil.JWTUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Service
public class CollectServiceImpl implements CollectService {
    @Resource
    LoveMapper loveMapper;

    @Override
    public CommonResult<String> collect(CollectParam collectParam, HttpServletRequest request) {

        String token = JWTUtil.getToken(request);
        if (token == null) return CommonResult.tokenNull();
        //鉴别token
        if (!JWTUtil.checkToken(token)) return CommonResult.tokenWrong();
        Integer userId = JWTUtil.getUserId(token);

        Integer collectionId = collectParam.getCollectionId();
        if (loveMapper.selectToCount(userId, collectionId) != 0) return CommonResult.fail("已收藏");
        if (loveMapper.insert(userId, collectionId) == 0) return CommonResult.fail("插入数据失败");
        else return CommonResult.success("收藏成功");
    }

    @Override
    public CommonResult<String> cancel(CollectParam collectParam, HttpServletRequest request) {

        String token = JWTUtil.getToken(request);
        if (token == null) return CommonResult.tokenNull();
        //鉴别token
        if (!JWTUtil.checkToken(token)) return CommonResult.tokenWrong();
        Integer userId = JWTUtil.getUserId(token);

        Integer collectionId = collectParam.getCollectionId();
        if (loveMapper.selectToCount(userId, collectionId) == 0) return CommonResult.fail("未收藏");
        if (loveMapper.delete(userId, collectionId) == 0) return CommonResult.fail("删除数据失败");
        else return CommonResult.success("删除成功");
    }
}
