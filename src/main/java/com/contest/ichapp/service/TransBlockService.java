package com.contest.ichapp.service;

import com.contest.ichapp.pojo.block.BlockChain;
import com.contest.ichapp.pojo.dto.CommonResult;
import com.contest.ichapp.pojo.dto.param.TransParam;

import javax.servlet.http.HttpServletRequest;

public interface TransBlockService {
    CommonResult<BlockChain> transOne(HttpServletRequest request, TransParam transParam);
}
