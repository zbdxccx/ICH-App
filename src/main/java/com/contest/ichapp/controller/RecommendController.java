package com.contest.ichapp.controller;

import com.contest.ichapp.pojo.dto.CommonResult;
import com.contest.ichapp.service.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RecommendController {
    private final RecommendService recommendService;

    @Autowired
    public RecommendController(RecommendService recommendService) {
        this.recommendService = recommendService;
    }

    @GetMapping("/recommend/img")
    public CommonResult<String> recommend() {
        return recommendService.recommend();
    }
}
