package com.contest.ichapp.controller;

import com.contest.ichapp.pojo.dto.CommonResult;
import com.contest.ichapp.pojo.dto.result.InfoResult;
import com.contest.ichapp.pojo.dto.vo.MoreInfoVo;
import com.contest.ichapp.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Validated
@CrossOrigin
@RestController
public class HomeController {

    private final HomeService homeService;

    @Autowired
    public HomeController(HomeService homeService) {
        this.homeService = homeService;
    }

    @GetMapping("/home/info/all")
    public CommonResult<InfoResult> getAllInfo(@RequestParam(value = "keyword", required = false, defaultValue = "all") String keyword, @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum, @RequestParam(value = "tagId", required = false, defaultValue = "0") Integer tagId, HttpServletRequest request) {
        return homeService.getAllInfo(keyword, pageNum, tagId, request);
    }

    @GetMapping("/home/info/more")
    public CommonResult<MoreInfoVo> getMoreInfo(@RequestParam(value = "collectionId") Integer collectionId, HttpServletRequest request) {
        return homeService.getMoreInfo(collectionId, request);
    }
}
