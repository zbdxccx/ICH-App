package com.contest.ichapp.controller;

import com.contest.ichapp.pojo.domain.Collection;
import com.contest.ichapp.pojo.dto.CommonResult;
import com.contest.ichapp.pojo.dto.param.StringParam;
import com.contest.ichapp.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class PhotoController {
    private final PhotoService photoService;

    @Autowired
    public PhotoController(PhotoService photoService) {
        this.photoService = photoService;
    }

    @GetMapping("/photo/add")
    public CommonResult<String> addImg(@RequestParam Integer collectionId) {
        return photoService.addImg(collectionId);
    }

    @PostMapping("/photo/search")
    public CommonResult<Collection> searchImg(HttpServletRequest request, @RequestBody StringParam param) {
        return photoService.searchImg(request, param);
    }
}
