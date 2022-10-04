package com.contest.ichapp.controller;

import com.contest.ichapp.pojo.dto.CommonResult;
import com.contest.ichapp.pojo.dto.param.CheckBlockOriginParam;
import com.contest.ichapp.pojo.dto.param.TransParam;
import com.contest.ichapp.pojo.dto.result.AllBlockResult;
import com.contest.ichapp.service.TransBlockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin
@Slf4j
public class TransBlockController {
    private final TransBlockService transBlockService;

    @Autowired
    public TransBlockController(TransBlockService transBlockService) {
        this.transBlockService = transBlockService;
    }

    @PostMapping("/info/trans")
    public CommonResult<String> transOne(HttpServletRequest request, @RequestBody TransParam param) {
        log.info("info/trans");
        return transBlockService.transOne(request, param);
    }

    @GetMapping("/info/trans/check")
    public CommonResult<CheckBlockOriginParam> checkBlock(HttpServletRequest request, @RequestParam String transId) {
        log.info("info/trans/check");
        return transBlockService.checkBlock(request, transId);
    }

    @GetMapping("/info/trans/all")
    public CommonResult<AllBlockResult> getCollectionBlock(HttpServletRequest request) {
        log.info("info/trans/all");
        return transBlockService.getCollectionBlock(request);
    }
}
