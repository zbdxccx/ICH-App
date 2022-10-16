package com.contest.ichapp.controller;

import com.contest.ichapp.pojo.dto.CommonResult;
import com.contest.ichapp.pojo.dto.param.MuseumParam;
import com.contest.ichapp.service.MuseumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Validated
@CrossOrigin
@RestController
public class MuseumController {
    private final MuseumService service;

    @Autowired
    public MuseumController(MuseumService service) {
        this.service = service;
    }

    @GetMapping("/museum/info")
    public CommonResult<MuseumParam> getMuseumInfo(@RequestParam @Valid String museumName) {
        return service.getMuseumInfo(museumName);
    }
}
