package com.contest.ichapp.service.Impl;

import com.contest.ichapp.mapper.CollectionMapper;
import com.contest.ichapp.pojo.domain.Collection;
import com.contest.ichapp.pojo.dto.CommonResult;
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
    public CommonResult<String> addImg(Integer collectionId) {
        Collection collection = collectionMapper.selectById(collectionId);
        ImgSearchUtil.sameHqAdd(collection);
        log.info("第" + collectionId + "张图片已添加入图库");
        return CommonResult.success("succeed");
    }

    @Override
    public CommonResult<String> searchImg(HttpServletRequest request, String ImgBase64) {
        return null;
    }
}
