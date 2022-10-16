package com.contest.ichapp.controller;

import com.contest.ichapp.pojo.domain.UserInfo;
import com.contest.ichapp.pojo.dto.CommonResult;
import com.contest.ichapp.pojo.dto.param.PersonalParam;
import com.contest.ichapp.pojo.dto.result.CollectionResult;
import com.contest.ichapp.pojo.dto.result.HistoryResult;
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
    CommonResult<String> setHeadImg(HttpServletRequest request, @RequestBody @Valid MultipartFile file) {
        return personalHomeService.setHeadImg(request, file);
    }

}
