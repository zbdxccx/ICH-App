package com.contest.ichapp.controller;

import com.contest.ichapp.pojo.domain.UserInfo;
import com.contest.ichapp.pojo.dto.CommonResult;
import com.contest.ichapp.pojo.dto.param.StringParam;
import com.contest.ichapp.pojo.dto.result.CollectionResult;
import com.contest.ichapp.pojo.dto.result.HistoryResult;
import com.contest.ichapp.service.PersonalHomeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin
@Slf4j
public class PersonalHomeController {

    private final PersonalHomeService personalHomeService;

    @Autowired
    public PersonalHomeController(PersonalHomeService personalHomeService) {
        this.personalHomeService = personalHomeService;
    }

    @GetMapping("/personal/collection")
    public CommonResult<CollectionResult> getAllCollection(HttpServletRequest request) {
        log.info(request.getRequestURI());
        return personalHomeService.getAllCollection(request);
    }

    @GetMapping("/personal/history")
    public CommonResult<HistoryResult> getAllHistory(HttpServletRequest request) {
        log.info(request.getRequestURI());
        return personalHomeService.getAllHistory(request);
    }

    @GetMapping("/personal/all")
    public CommonResult<UserInfo> getPersonalInfo(HttpServletRequest request) {
        log.info(request.getRequestURI());
        return personalHomeService.getPersonalInfo(request);
    }

    @PostMapping("/personal/set/name")
    public CommonResult<String> setName(HttpServletRequest request, @RequestBody StringParam param) {
        log.info(request.getRequestURI());
        return personalHomeService.setName(request, param);
    }

    @PostMapping("/personal/set/sign")
    public CommonResult<String> setSign(HttpServletRequest request, @RequestBody StringParam param) {
        log.info(request.getRequestURI());
        return personalHomeService.setSign(request, param);
    }

    @PostMapping("/personal/set/head")
    public CommonResult<String> setHeadImg(HttpServletRequest request, @RequestBody StringParam param) {
        log.info(request.getRequestURI());
        return personalHomeService.setHeadImg(request, param);
    }

}
