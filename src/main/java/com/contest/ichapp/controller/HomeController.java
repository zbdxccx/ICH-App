package com.contest.ichapp.controller;

import com.contest.ichapp.pojo.dto.CommonResult;
import com.contest.ichapp.pojo.dto.result.InfoResult;
import com.contest.ichapp.pojo.dto.vo.MoreInfoVo;
import com.contest.ichapp.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    private final HomeService homeService;

    @Autowired
    public HomeController(HomeService homeService) {
        this.homeService = homeService;
    }

    @GetMapping("/home/info/all")
    public CommonResult<InfoResult> getAllInfo(@RequestParam(value = "keyword", required = false, defaultValue = "all") String keyword) {
        return homeService.getAllInfo(keyword);
    }

    @GetMapping("/home/info/more")
    public CommonResult<MoreInfoVo> getMoreInfo(@RequestParam(value = "collectionId") Integer collectionId) {
        return homeService.getMoreInfo(collectionId);
    }
}
