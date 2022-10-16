package com.contest.ichapp.controller;

import com.contest.ichapp.pojo.dto.CommonResult;
import com.contest.ichapp.pojo.dto.param.RecommendDateParam;
import com.contest.ichapp.pojo.dto.param.RecommendParam;
import com.contest.ichapp.service.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
@CrossOrigin
@RestController
public class RecommendController {
    private final RecommendService recommendService;

    @Autowired
    public RecommendController(RecommendService recommendService) {
        this.recommendService = recommendService;
    }

    @GetMapping("/recommend/img")
    public CommonResult<RecommendParam> recommend(HttpServletRequest request) {
        return recommendService.recommend(request);
    }

    @GetMapping("/recommend/date")
    public CommonResult<RecommendDateParam> localTime() {
        return recommendService.localTime();
    }
}
