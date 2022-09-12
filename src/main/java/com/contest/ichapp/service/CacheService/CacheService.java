package com.contest.ichapp.service.CacheService;

public interface CacheService {
    String getVerificationCode(String phoneNum);

    String updateVerificationCode(String phoneNum);

    String deleteVerificationCode(String phoneNum);
}
