package com.contest.ichapp.controller;

import com.contest.ichapp.pojo.dto.CommonResult;
import com.contest.ichapp.pojo.dto.param.CollectParam;
import com.contest.ichapp.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CollectController {

    private final CollectService collectService;

    @Autowired
    public CollectController(CollectService collectService) {
        this.collectService = collectService;
    }

    @PostMapping("/home/info/collect")
    public CommonResult<String> collect(@RequestBody CollectParam collectParam) {
        return collectService.collect(collectParam);
    }

    @PostMapping("/home/info/cancel")
    public CommonResult<String> cancel(@RequestBody CollectParam collectParam) {
        return collectService.cancel(collectParam);
    }
}
