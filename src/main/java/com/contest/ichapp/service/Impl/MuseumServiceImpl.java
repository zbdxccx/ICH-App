package com.contest.ichapp.service.Impl;

import com.contest.ichapp.mapper.MuseumMapper;
import com.contest.ichapp.pojo.domain.Museum;
import com.contest.ichapp.pojo.dto.CommonResult;
import com.contest.ichapp.pojo.dto.param.MuseumParam;
import com.contest.ichapp.service.MuseumService;
import com.contest.ichapp.util.searchUtil.SearchUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;

@Service
public class MuseumServiceImpl implements MuseumService {
    @Resource
    MuseumMapper museumMapper;

    public final static String REGEX_ALL_BRACKETS = "\\[.*?\\]";

    @Override
    public synchronized CommonResult<MuseumParam> getMuseumInfo(String museumName) {
        Museum museum = museumMapper.selectAllByName(museumName);
        String description;
        String content;
        try {
            description = SearchUtil.searchFromBaidu(museumName);
            content = description.replaceAll(REGEX_ALL_BRACKETS, "");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        MuseumParam param = new MuseumParam(museum, content);
        return CommonResult.success(param);
    }
}
