package com.contest.ichapp.controller;

import com.contest.ichapp.pojo.dto.CommonResult;
import com.contest.ichapp.pojo.dto.result.CollectionResult;
import com.contest.ichapp.service.PersonalHomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class PersonalHomeController {

    private final PersonalHomeService personalHomeService;

    @Autowired
    public PersonalHomeController(PersonalHomeService personalHomeService) {
        this.personalHomeService = personalHomeService;
    }

    @GetMapping("/personal/collection")
    public CommonResult<CollectionResult> getAllCollection(HttpServletRequest request) {
        return personalHomeService.getAllCollection(request);
    }
}