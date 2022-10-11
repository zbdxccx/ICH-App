package com.contest.ichapp.service.Impl;

import com.alibaba.fastjson2.JSONObject;
import com.contest.ichapp.mapper.CollectionMapper;
import com.contest.ichapp.pojo.domain.Collection;
import com.contest.ichapp.pojo.dto.CommonResult;
import com.contest.ichapp.pojo.dto.param.StringParam;
import com.contest.ichapp.service.PhotoService;
import com.contest.ichapp.util.imgUtil.ImgSearchUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Service
@Slf4j
public class PhotoServiceImpl implements PhotoService {
    @Resource
    CollectionMapper collectionMapper;

    @Override
    public synchronized CommonResult<String> addImg(Integer collectionId) {
        for (int i = 1; i <= 63; i++) {
            Collection collection = collectionMapper.selectById(i);
            ImgSearchUtil.sameHqAdd(collection);
            log.info("第" + i + "张图片已添加入图库");
        }
        return CommonResult.success("succeed");
    }

    @Override
    public synchronized CommonResult<Collection> searchImg(HttpServletRequest request, StringParam param) {
        String imgBase64 = param.getString();
        String search = ImgSearchUtil.sameHqSearch(imgBase64);
        JSONObject jsonObject = JSONObject.parseObject(search);
        String result = null;
        if (jsonObject != null) result = jsonObject.getString("result");
        assert result != null;
        if (result.isEmpty()) return CommonResult.success("未找到相关藏品");
        JSONObject jsonObjectBrief = JSONObject.parseObject(result.substring(1, result.length() - 1));
        String brief = null;
        if (jsonObjectBrief != null) brief = jsonObjectBrief.getString("brief");
        JSONObject jsonObjectId = JSONObject.parseObject(brief);
        Integer id = null;
        if (jsonObjectId != null) id = jsonObjectId.getInteger("id");
        Collection collection = collectionMapper.selectById(id);
        return CommonResult.success(collection);
    }
}
