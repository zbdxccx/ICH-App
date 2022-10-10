package com.contest.ichapp.service.Impl;

import com.contest.ichapp.mapper.AppraiseMapper;
import com.contest.ichapp.pojo.dto.CommonResult;
import com.contest.ichapp.pojo.dto.param.AppraiseParam;
import com.contest.ichapp.pojo.dto.param.AppraisePostParam;
import com.contest.ichapp.pojo.dto.param.DeleteAppraiseParam;
import com.contest.ichapp.pojo.dto.result.AppraiseResult;
import com.contest.ichapp.service.AppraiseService;
import com.contest.ichapp.util.jwtUtil.JWTUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppraiseServiceImpl implements AppraiseService {

    @Resource
    AppraiseMapper appraiseMapper;

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public CommonResult<String> appraise(HttpServletRequest request, AppraisePostParam param) {
        //鉴权
        Integer userId = JWTUtil.getUserId_X(request);
        if (userId == -1) return CommonResult.tokenWrong();
        if (userId == -2) return CommonResult.tokenNull();

        String appraise = param.getAppraise();
        Integer collectionId = param.getCollectionId();
        Integer flag = param.getFlag();

        if (appraiseMapper.insertOne(collectionId, userId, appraise, dateFormat.format(new Date())) == 0)
            return CommonResult.fail("评论失败");
        return CommonResult.success("评论成功");
    }

    @Override
    public CommonResult<AppraiseResult> viewAppraise(HttpServletRequest request, Integer collectionId, Integer pageNum) {
        //鉴权
        Integer userId = JWTUtil.getUserId_X(request);
        if (userId == -1) return CommonResult.tokenWrong();
        if (userId == -2) return CommonResult.tokenNull();

        List<AppraiseParam> appraiseParams = appraiseMapper.selectAllByCollectionId(collectionId, userId);
        //分页n*10
        List<AppraiseParam> params = appraiseParams.stream().skip((pageNum - 1) * 10L).limit(10).collect(Collectors.toList());
        AppraiseResult appraiseResult = new AppraiseResult(params, appraiseParams.size());
        return CommonResult.success(appraiseResult);
    }

    @Override
    public CommonResult<String> deleteAppraise(HttpServletRequest request, DeleteAppraiseParam param) {
        //鉴权
        Integer userId = JWTUtil.getUserId_X(request);
        if (userId == -1) return CommonResult.tokenWrong();
        if (userId == -2) return CommonResult.tokenNull();

        if (appraiseMapper.deleteOne(param.getCollectionId(), userId, param.getTime()) == 0) {
            return CommonResult.fail("删除评论失败");
        }
        return CommonResult.success("删除评论成功");
    }
}
