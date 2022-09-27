package com.contest.ichapp.service.Impl;

import com.contest.ichapp.mapper.CollectionMapper;
import com.contest.ichapp.mapper.HistoryMapper;
import com.contest.ichapp.pojo.domain.Collection;
import com.contest.ichapp.pojo.dto.CommonResult;
import com.contest.ichapp.pojo.dto.param.InfoParam;
import com.contest.ichapp.pojo.dto.result.InfoResult;
import com.contest.ichapp.pojo.dto.vo.MoreInfoVo;
import com.contest.ichapp.service.HomeService;
import com.contest.ichapp.util.JWTUtil.JWTUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
public class HomeServiceImpl implements HomeService {
    @Resource
    CollectionMapper collectionMapper;
    @Resource
    HistoryMapper historyMapper;

    @Override
    public CommonResult<InfoResult> getAllInfo(String keyword) {

        List<Collection> collections;
        if ("all".equals(keyword)) collections = collectionMapper.selectAll();
        else collections = collectionMapper.selectAllLike(keyword);

        if (collections.isEmpty()) return CommonResult.fail("无相关数据");

        List<InfoParam> params = new ArrayList<>();
        for (Collection collection : collections) {
            Integer id = collection.getId();
            String name = collection.getName();
            String location = collection.getLocation();
            String img = collection.getImg();
            InfoParam param = new InfoParam(id, name, location, img);
            params.add(param);
        }
        InfoResult result = new InfoResult(params);
        return CommonResult.success(result);
    }

    @Override
    public CommonResult<MoreInfoVo> getMoreInfo(Integer collectionId, HttpServletRequest request) {
        String token = JWTUtil.getToken(request);
        Integer userId = JWTUtil.getUserId(token);
        //添加历史记录
        if (historyMapper.insertOne(collectionId, userId) == 0) return CommonResult.fail("Insert failed");
        MoreInfoVo collection = collectionMapper.selectAllInfoById(collectionId);
        return CommonResult.success(collection);
    }
}
