package com.contest.ichapp.controller;

import com.contest.ichapp.pojo.dto.CommonResult;
import com.contest.ichapp.pojo.dto.param.CollectParam;
import com.contest.ichapp.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Validated
@CrossOrigin
@RestController
public class CollectController {

    private final CollectService collectService;

    @Autowired
    public CollectController(CollectService collectService) {
        this.collectService = collectService;
    }

    @PostMapping("/home/info/collect")
    public CommonResult<String> collect(@RequestBody @Valid CollectParam collectParam, HttpServletRequest request) {
        return collectService.collect(collectParam, request);
    }

    @PostMapping("/home/info/cancel")
    public CommonResult<String> cancel(@RequestBody @Valid CollectParam collectParam, HttpServletRequest request) {
        return collectService.cancel(collectParam, request);
    }
}
