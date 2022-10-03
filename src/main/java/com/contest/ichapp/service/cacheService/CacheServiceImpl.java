package com.contest.ichapp.service.cacheService;

import com.contest.ichapp.mapper.CollectionMapper;
import com.contest.ichapp.mapper.TagMapper;
import com.contest.ichapp.pojo.domain.Collection;
import com.contest.ichapp.pojo.domain.Tag;
import com.contest.ichapp.pojo.dto.param.ImgParam;
import com.contest.ichapp.pojo.dto.result.TagResult;
import com.contest.ichapp.util.randomUtil.RandomUtil;
import lombok.SneakyThrows;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.List;

@Service
@CacheConfig(cacheNames = "CacheService")
public class CacheServiceImpl implements CacheService {
    @Resource
    TagMapper tagMapper;
    @Resource
    CollectionMapper collectionMapper;

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


    @Override
    @SneakyThrows
    @Cacheable(cacheNames = "img", key = "#img")
    public ImgParam ioImg(String img) {
        BufferedImage sourceImg;
        sourceImg = ImageIO.read(new URL(img).openStream());
        int width = sourceImg.getWidth();
        int height = sourceImg.getHeight();
        return new ImgParam(height, width);
    }

    @Override
    @Cacheable(cacheNames = "collection")
    public List<Collection> getAllCollection() {
        return collectionMapper.selectAll();
    }

    @Override
    @Cacheable(cacheNames = "collectionLike")
    public List<Collection> getAllCollectionLike(String keyword) {
        return collectionMapper.selectAllLike(keyword);
    }
}
