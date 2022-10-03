package com.contest.ichapp.service.cacheService;

import com.contest.ichapp.pojo.domain.Collection;
import com.contest.ichapp.pojo.dto.param.ImgParam;
import com.contest.ichapp.pojo.dto.result.TagResult;

import java.net.MalformedURLException;
import java.util.List;

public interface CacheService {
    String getVerificationCode(String phoneNum);

    String updateVerificationCode(String phoneNum);

    String deleteVerificationCode(String phoneNum);

    TagResult getTagName();

    TagResult updateTagName();

    TagResult deleteTagName();

    ImgParam ioImg(String img) throws MalformedURLException;

    List<Collection> getAllCollection();

    List<Collection> getAllCollectionLike(String keyword);
}
