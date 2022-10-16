package com.contest.ichapp.service;

import com.contest.ichapp.pojo.domain.Collection;
import com.contest.ichapp.pojo.dto.CommonResult;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface PhotoService {
    CommonResult<String> addImg(Integer collectionId);

//    CommonResult<Collection> searchImg(HttpServletRequest request,  StringParam param);
    CommonResult<Collection> searchImg(HttpServletRequest request, MultipartFile file);
}
