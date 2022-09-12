package com.contest.ichapp.service.CacheService;

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
    @Cacheable(cacheNames = "code")
    public String getVerificationCode() {
        return RandomUtil.getSixBitRandom();
    }

    @Override
    @CachePut(cacheNames = "code")
    public String updateVerificationCode() {
        return RandomUtil.getSixBitRandom();
    }

    @Override
    @CacheEvict(cacheNames = "code")
    public String deleteVerificationCode() {
        return RandomUtil.getSixBitRandom();
    }
}
