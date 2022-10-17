package com.contest.ichapp.controller;

import com.contest.ichapp.pojo.domain.UserInfo;
import com.contest.ichapp.pojo.dto.CommonResult;
import com.contest.ichapp.pojo.dto.param.AddParam;
import com.contest.ichapp.pojo.dto.param.PersonalParam;
import com.contest.ichapp.pojo.dto.param.museumIdParam;
import com.contest.ichapp.pojo.dto.result.CollectionResult;
import com.contest.ichapp.pojo.dto.result.HistoryResult;
import com.contest.ichapp.pojo.dto.result.LikeToGoResult;
import com.contest.ichapp.service.PersonalHomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


@Validated
@CrossOrigin
@RestController
public class PersonalHomeController {

    private final PersonalHomeService personalHomeService;

    @Autowired
    public PersonalHomeController(PersonalHomeService personalHomeService) {
        this.personalHomeService = personalHomeService;
    }

    @GetMapping("/personal/collection")
    public CommonResult<CollectionResult> getAllCollection(HttpServletRequest request) {
        return personalHomeService.getAllCollection(request);
    }

    @GetMapping("/personal/history")
    public CommonResult<HistoryResult> getAllHistory(HttpServletRequest request) {
        return personalHomeService.getAllHistory(request);
    }

    @GetMapping("/personal/all")
    public CommonResult<UserInfo> getPersonalInfo(HttpServletRequest request) {
        return personalHomeService.getPersonalInfo(request);
    }

    @PostMapping("/personal/set/info")
    public CommonResult<String> setNameAndSign(HttpServletRequest request, @RequestBody @Valid PersonalParam param) {
        return personalHomeService.setNameAndSign(request, param);
    }

    @PostMapping("/personal/set/head")
    public CommonResult<String> setHeadImg(HttpServletRequest request, @RequestBody @Valid MultipartFile file) {
        return personalHomeService.setHeadImg(request, file);
    }

    @PostMapping("/like/go/set")
    public CommonResult<String> setLikeGo(HttpServletRequest request, @RequestBody @Valid museumIdParam param) {
        return personalHomeService.setLikeGo(request, param.getMuseumId());
    }

    @PostMapping("/like/go/delete")
    public CommonResult<String> deleteLikeGo(HttpServletRequest request, @RequestBody @Valid museumIdParam param) {
        return personalHomeService.deleteLikeGo(request, param.getMuseumId());
    }

    @GetMapping("/like/go/get")
    public CommonResult<LikeToGoResult> getLikeGo(HttpServletRequest request) {
        return personalHomeService.getLikeGo(request);
    }

    @PostMapping("/add/content")
    CommonResult<String> addCollection(HttpServletRequest request, @RequestBody @Valid AddParam param) {
        return personalHomeService.addCollection(request, param);
    }

    @PostMapping("/add/img")
    CommonResult<String> addUrl(HttpServletRequest request, @RequestBody @Valid MultipartFile file) {
        return personalHomeService.addUrl(request, file);
    }
}
