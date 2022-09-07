package com.contest.ichapp.service.Impl;

import cn.hutool.core.date.ChineseDate;
import cn.hutool.core.date.DateTime;
import com.contest.ichapp.common.Constant;
import com.contest.ichapp.mapper.CollectionMapper;
import com.contest.ichapp.pojo.domain.Collection;
import com.contest.ichapp.pojo.dto.CommonResult;
import com.contest.ichapp.pojo.dto.param.RecommendDateParam;
import com.contest.ichapp.pojo.dto.param.RecommendParam;
import com.contest.ichapp.service.RecommendService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

@Service
public class RecommendServiceImpl implements RecommendService {

    @Resource
    CollectionMapper collectionMapper;

    @Override
    public CommonResult<RecommendParam> recommend() {
        int id = 1;
        Collection collection = collectionMapper.selectById(id);
        String img = collection.getImg();
        RecommendParam param = new RecommendParam(img, collection.getName(), collection.getDescription());
        return CommonResult.success(param);
    }

    @Override
    public CommonResult<RecommendDateParam> localTime() {
        DateTime dateTime = new DateTime();

        TimeZone timeZone = TimeZone.getTimeZone(Constant.GMT8);
        dateTime.setTimeZone(timeZone);
        SimpleDateFormat dateFormat = new SimpleDateFormat(Constant.DATE_FORMAT);
        SimpleDateFormat weekFormat = new SimpleDateFormat(Constant.WEEK_FORMAT);

        ChineseDate chineseDate = new ChineseDate(dateTime);
        String chineseDay = chineseDate.getChineseDay();
        String chineseMonth = chineseDate.getChineseMonth();

        RecommendDateParam param = new RecommendDateParam();
        param.setCalendar(chineseMonth + chineseDay);
        param.setWeekNum(weekFormat.format(dateTime));
        param.setTime(dateFormat.format(dateTime));
        return CommonResult.success(param);
    }
}
