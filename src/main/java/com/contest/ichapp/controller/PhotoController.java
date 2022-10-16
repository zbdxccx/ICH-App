package com.contest.ichapp.controller;

import com.contest.ichapp.pojo.domain.Collection;
import com.contest.ichapp.pojo.dto.CommonResult;
import com.contest.ichapp.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


@Validated
@CrossOrigin
@RestController
public class PhotoController {
    private final PhotoService photoService;

    @Autowired
    public PhotoController(PhotoService photoService) {
        this.photoService = photoService;
    }

    @GetMapping("/photo/add")
    public CommonResult<String> addImg(@RequestParam @Valid Integer collectionId) {
        return photoService.addImg(collectionId);
    }

    @PostMapping("/photo/search")
    public CommonResult<Collection> searchImg(HttpServletRequest request, @RequestBody @Valid MultipartFile file) {
        return photoService.searchImg(request, file);
    }
}
