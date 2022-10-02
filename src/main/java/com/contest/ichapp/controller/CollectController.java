package com.contest.ichapp.controller;

import com.contest.ichapp.pojo.dto.CommonResult;
import com.contest.ichapp.pojo.dto.param.CollectParam;
import com.contest.ichapp.service.CollectService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Validated
@RestController
@CrossOrigin
@Slf4j
public class CollectController {

    private final CollectService collectService;

    @Autowired
    public CollectController(CollectService collectService) {
        this.collectService = collectService;
    }

    @PostMapping("/home/info/collect")
    public CommonResult<String> collect(@RequestBody @Valid CollectParam collectParam, HttpServletRequest request) {
        log.info("/home/info/collect");
        return collectService.collect(collectParam, request);
    }

    @PostMapping("/home/info/cancel")
    public CommonResult<String> cancel(@RequestBody @Valid CollectParam collectParam, HttpServletRequest request) {
        log.info("/home/info/cancel");
        return collectService.cancel(collectParam, request);
    }
}
