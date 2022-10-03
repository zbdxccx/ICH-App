package com.contest.ichapp.controller;

import com.contest.ichapp.pojo.dto.CommonResult;
import com.contest.ichapp.pojo.dto.param.CheckBlockOriginParam;
import com.contest.ichapp.pojo.dto.param.TransParam;
import com.contest.ichapp.pojo.dto.result.AllBlockResult;
import com.contest.ichapp.service.TransBlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin
public class TransBlockController {
    private final TransBlockService transBlockService;

    @Autowired
    public TransBlockController(TransBlockService transBlockService) {
        this.transBlockService = transBlockService;
    }

    @PostMapping("/info/trans")
    public CommonResult<String> transOne(HttpServletRequest request, @RequestBody TransParam param) {
        return transBlockService.transOne(request, param);
    }

    @GetMapping("/info/trans/check")
    public CommonResult<CheckBlockOriginParam> checkBlock(HttpServletRequest request, @RequestParam String transId) {
        return transBlockService.checkBlock(request, transId);
    }

    @GetMapping("/info/trans/all")
    public CommonResult<AllBlockResult> getCollectionBlock(HttpServletRequest request) {
        return transBlockService.getCollectionBlock(request);
    }
}
