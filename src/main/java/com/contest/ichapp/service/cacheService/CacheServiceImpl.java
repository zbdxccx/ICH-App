package com.contest.ichapp.service.cacheService;

import com.contest.ichapp.util.RandomUtil.RandomUtil;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@CacheConfig(cacheNames = "CacheService")
public class CacheServiceImpl implements CacheService {
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
}
