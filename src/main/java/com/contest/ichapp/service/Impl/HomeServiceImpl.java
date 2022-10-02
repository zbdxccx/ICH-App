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
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class HomeServiceImpl implements HomeService {
    @Resource
    CollectionMapper collectionMapper;
    @Resource
    HistoryMapper historyMapper;

    @Override
    public CommonResult<InfoResult> getAllInfo(String keyword, Integer pageNum) {

        List<Collection> collections;
        if ("all".equals(keyword)) collections = collectionMapper.selectAll((pageNum - 1) * 10);
        else collections = collectionMapper.selectAllLike(keyword, (pageNum - 1) * 10);

        if (collections.isEmpty()) return CommonResult.fail("无相关数据");
        //分页n*10
//        List<Collection> collectionList = collections.stream().skip((pageNum - 1) * 10L).limit(10).collect(Collectors.toList());

        List<InfoParam> params = new ArrayList<>();
        for (Collection collection : collections) {
            Integer id = collection.getId();
            String name = collection.getName();
            String location = collection.getLocation();
            String img = collection.getFullImg();
            BufferedImage sourceImg;
            try {
                sourceImg = ImageIO.read(new URL(img).openStream());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            int width = sourceImg.getWidth();
            int height = sourceImg.getHeight();
            InfoParam param = new InfoParam(id, name, location, img, height, width);
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
