package com.contest.ichapp.service.Impl;

import com.contest.ichapp.pojo.dto.CommonResult;
import com.contest.ichapp.pojo.dto.result.TagResult;
import com.contest.ichapp.service.TagService;
import com.contest.ichapp.service.cacheService.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagServiceImpl implements TagService {

    private final CacheService cacheService;

    @Autowired
    public TagServiceImpl(CacheService cacheService) {
        this.cacheService = cacheService;
    }

    @Override
    public synchronized CommonResult<TagResult> getAllTag() {
        return CommonResult.success(cacheService.getTagName());
    }
}
