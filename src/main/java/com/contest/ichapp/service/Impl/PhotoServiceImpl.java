package com.contest.ichapp.service.Impl;

import com.alibaba.fastjson2.JSONObject;
import com.contest.ichapp.mapper.CollectionMapper;
import com.contest.ichapp.pojo.domain.Collection;
import com.contest.ichapp.pojo.dto.CommonResult;
import com.contest.ichapp.service.PhotoService;
import com.contest.ichapp.util.baiduTool.Base64Util;
import com.contest.ichapp.util.imgUtil.ImgSearchUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Service
@Slf4j
public class PhotoServiceImpl implements PhotoService {
    @Resource
    CollectionMapper collectionMapper;

    @Override
    public synchronized CommonResult<String> addImg(Integer collectionId) {

        Collection collection = collectionMapper.selectById(collectionId);
        ImgSearchUtil.sameHqAdd(collection);
        log.info("第" + collectionId + "张图片已添加入图库");
        return CommonResult.success("succeed");
    }

    @SneakyThrows
    @Override
    public synchronized CommonResult<Collection> searchImg(HttpServletRequest request, MultipartFile file) {

        String imgBase64;
        imgBase64 = Base64Util.encode(file.getBytes());

        String search = ImgSearchUtil.sameHqSearch(imgBase64);
        JSONObject jsonObject = JSONObject.parseObject(search);
        String result = null;
        if (jsonObject != null) result = jsonObject.getString("result");
        if (result == null || result.isEmpty()) return CommonResult.success("未找到相关藏品");
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
