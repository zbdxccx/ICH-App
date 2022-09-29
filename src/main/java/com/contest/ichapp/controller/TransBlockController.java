package com.contest.ichapp.controller;

import com.contest.ichapp.pojo.block.BlockChain;
import com.contest.ichapp.pojo.dto.CommonResult;
import com.contest.ichapp.pojo.dto.param.TransParam;
import com.contest.ichapp.service.TransBlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class TransBlockController {
    private final TransBlockService transBlockService;

    @Autowired
    public TransBlockController(TransBlockService transBlockService) {
        this.transBlockService = transBlockService;
    }

    @PostMapping("/info/trans")
    public CommonResult<BlockChain> transOne(HttpServletRequest request, @RequestBody TransParam param) {
        return transBlockService.transOne(request, param);
    }

    @GetMapping("/info/trans/decode")
    public CommonResult transDecode(HttpServletRequest request) {
        return transBlockService.decode(request);
    }
}
