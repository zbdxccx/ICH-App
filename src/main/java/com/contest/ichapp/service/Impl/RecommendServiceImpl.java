package com.contest.ichapp.service.Impl;

import cn.hutool.core.date.ChineseDate;
import cn.hutool.core.date.DateTime;
import com.contest.ichapp.mapper.CollectionMapper;
import com.contest.ichapp.mapper.HistoryMapper;
import com.contest.ichapp.pojo.domain.Collection;
import com.contest.ichapp.pojo.dto.CommonResult;
import com.contest.ichapp.pojo.dto.param.HistoryParam;
import com.contest.ichapp.pojo.dto.param.RecommendDateParam;
import com.contest.ichapp.pojo.dto.param.RecommendParam;
import com.contest.ichapp.service.RecommendService;
import com.contest.ichapp.util.JWTUtil.JWTUtil;
import com.contest.ichapp.util.algorithm.AlgorithmUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.contest.ichapp.common.Constant.*;

@Service
@Slf4j
public class RecommendServiceImpl implements RecommendService {

    @Resource
    CollectionMapper collectionMapper;
    @Resource
    HistoryMapper historyMapper;

    @Override
    public CommonResult<RecommendParam> recommend(HttpServletRequest request) {
        //鉴权
        Integer userId = JWTUtil.getUserId_X(request);
        if (userId == -1) return CommonResult.tokenWrong();
        if (userId == -2) return CommonResult.tokenNull();
        //获取个人兴趣map
        List<HistoryParam> historyParams = historyMapper.selectAllById(userId);
        Map<Integer, Integer> map = new HashMap<>();
        int[] integers = new int[9];
        Arrays.fill(integers, 0);
        for (HistoryParam history : historyParams) {
            Integer tagId = collectionMapper.selectById(history.getCollectionId()).getTagId();
            Integer count = history.getCount();
            integers[tagId] += count;
            map.put(tagId, integers[tagId]);
        }
        //根据推荐算法获取的id
        int tagId = AlgorithmUtil.recommend(map);
        log.info("推荐id为：" + tagId);
        Collection collection = collectionMapper.randByTagId(tagId);
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
