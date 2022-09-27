package com.contest.ichapp.service.cacheService;

import com.contest.ichapp.pojo.dto.result.TagResult;

public interface CacheService {
    String getVerificationCode(String phoneNum);

    String updateVerificationCode(String phoneNum);

    String deleteVerificationCode(String phoneNum);

    TagResult getTagName();

    TagResult updateTagName();

    TagResult deleteTagName();
}
