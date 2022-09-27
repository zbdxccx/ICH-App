package com.contest.ichapp.service.Impl;

import cn.hutool.core.date.ChineseDate;
import cn.hutool.core.date.DateTime;
import com.contest.ichapp.mapper.CollectionMapper;
import com.contest.ichapp.pojo.domain.Collection;
import com.contest.ichapp.pojo.dto.CommonResult;
import com.contest.ichapp.pojo.dto.param.RecommendDateParam;
import com.contest.ichapp.pojo.dto.param.RecommendParam;
import com.contest.ichapp.service.RecommendService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Random;
import java.util.TimeZone;

import static com.contest.ichapp.common.Constant.*;

@Service
@Slf4j
public class RecommendServiceImpl implements RecommendService {

    @Resource
    CollectionMapper collectionMapper;

    @Override
    public CommonResult<RecommendParam> recommend() {
        int id = new Random().nextInt(collectionMapper.selectCountAll()) + 1;
        log.info(String.valueOf(id));
        Collection collection = collectionMapper.selectById(id);
        String img = collection.getImg();
        Integer collectionId = collection.getId();
        RecommendParam param = new RecommendParam(collectionId, img, collection.getName(), collection.getDescription());
        return CommonResult.success(param);
    }

    @Override
    public CommonResult<RecommendDateParam> localTime() {
        DateTime dateTime = new DateTime();

        TimeZone timeZone = TimeZone.getTimeZone(GMT8);
        dateTime.setTimeZone(timeZone);
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        SimpleDateFormat weekFormat = new SimpleDateFormat(WEEK_FORMAT, Locale.SIMPLIFIED_CHINESE);

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
