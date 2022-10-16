package com.contest.ichapp.controller;

import com.contest.ichapp.pojo.dto.CommonResult;
import com.contest.ichapp.pojo.dto.param.AppraisePostParam;
import com.contest.ichapp.pojo.dto.param.DeleteAppraiseParam;
import com.contest.ichapp.pojo.dto.result.AppraiseResult;
import com.contest.ichapp.service.AppraiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Validated
@CrossOrigin
@RestController
public class AppraiseController {
    private final AppraiseService appraiseService;

    @Autowired
    public AppraiseController(AppraiseService appraiseService) {
        this.appraiseService = appraiseService;
    }

    @PostMapping("/appraise/set")
    public CommonResult<String> appraise(HttpServletRequest request, @RequestBody @Valid AppraisePostParam param) {
        return appraiseService.appraise(request, param);
    }

    @PostMapping("/appraise/delete")
    public CommonResult<String> deleteAppraise(HttpServletRequest request, @RequestBody @Valid DeleteAppraiseParam param) {
        return appraiseService.deleteAppraise(request, param);
    }

    @GetMapping("/appraise/get")
    public CommonResult<AppraiseResult> viewAppraise(HttpServletRequest request, @RequestParam @Valid Integer collectionId, @RequestParam @Valid Integer pageNum) {
        return appraiseService.viewAppraise(request, collectionId, pageNum);
    }
}
