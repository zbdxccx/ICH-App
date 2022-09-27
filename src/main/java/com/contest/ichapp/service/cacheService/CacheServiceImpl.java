package com.contest.ichapp.service.cacheService;

import com.contest.ichapp.mapper.TagMapper;
import com.contest.ichapp.pojo.domain.Tag;
import com.contest.ichapp.pojo.dto.result.TagResult;
import com.contest.ichapp.util.RandomUtil.RandomUtil;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@CacheConfig(cacheNames = "CacheService")
public class CacheServiceImpl implements CacheService {
    @Resource
    TagMapper tagMapper;

    @Override
    @Cacheable(cacheNames = "code", key = "#phoneNum")
    public String getVerificationCode(String phoneNum) {
        return RandomUtil.getSixBitRandom();
    }

    @Override
    @CachePut(cacheNames = "code", key = "#phoneNum")
    public String updateVerificationCode(String phoneNum) {
        return RandomUtil.getSixBitRandom();
    }

    @Override
    @CacheEvict(cacheNames = "code", key = "#phoneNum")
    public String deleteVerificationCode(String phoneNum) {
        return RandomUtil.getSixBitRandom();
    }

    @Override
    @Cacheable(cacheNames = "tag")
    public TagResult getTagName() {
        List<Tag> tags = tagMapper.selectAll();
        return new TagResult(tags);
    }

    @Override
    @CachePut(cacheNames = "tag")
    public TagResult updateTagName() {
        List<Tag> tags = tagMapper.selectAll();
        return new TagResult(tags);
    }

    @Override
    @CacheEvict(cacheNames = "tag")
    public TagResult deleteTagName() {
        List<Tag> tags = tagMapper.selectAll();
        return new TagResult(tags);
    }


}
