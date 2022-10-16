package com.contest.ichapp.service.Impl;

import com.contest.ichapp.mapper.CollectionMapper;
import com.contest.ichapp.mapper.HistoryMapper;
import com.contest.ichapp.mapper.LoveMapper;
import com.contest.ichapp.mapper.MuseumMapper;
import com.contest.ichapp.pojo.domain.Collection;
import com.contest.ichapp.pojo.dto.CommonResult;
import com.contest.ichapp.pojo.dto.param.ImgParam;
import com.contest.ichapp.pojo.dto.param.InfoParam;
import com.contest.ichapp.pojo.dto.result.InfoResult;
import com.contest.ichapp.pojo.dto.vo.MoreInfoVo;
import com.contest.ichapp.service.HomeService;
import com.contest.ichapp.service.cacheService.CacheService;
import com.contest.ichapp.util.jwtUtil.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.contest.ichapp.common.Constant.TOKEN_NULL;
import static com.contest.ichapp.common.Constant.TOKEN_WRONG;

@Service
@Slf4j
public class HomeServiceImpl implements HomeService {
    @Resource
    CollectionMapper collectionMapper;
    @Resource
    HistoryMapper historyMapper;
    @Resource
    LoveMapper loveMapper;
    @Resource
    MuseumMapper museumMapper;
    private final CacheService cacheService;
    public final static String REGEX_ALL_BRACKETS = "\\[.*?\\]|\\-";

    @Autowired
    public HomeServiceImpl(CacheService cacheService) {
        this.cacheService = cacheService;
    }

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    @Override
    public synchronized CommonResult<InfoResult> getAllInfo(String keyword, Integer pageNum, Integer tagId, HttpServletRequest request) {
        boolean isLogin = false;
        Integer userId = JWTUtil.getUserIdCheck(request);
        if (userId == TOKEN_WRONG || userId == TOKEN_NULL) {
            log.info("未检测到登录，无收藏状态");
        } else isLogin = true;

        List<Collection> collections;
        if ("all".equals(keyword)) collections = cacheService.getAllCollection();
        else collections = cacheService.getAllCollectionLike(keyword);
        if (collections.isEmpty()) return CommonResult.fail("无相关数据");

        //筛出不符合tagId的数据
        if (tagId != 0) collections.removeIf(next -> !Objects.equals(next.getTagId(), tagId));

        //分页n*10
        List<Collection> collectionList = collections.stream()
                .skip((pageNum - 1) * 10L)
                .limit(10)
                .collect(Collectors.toList());

        List<InfoParam> params = new ArrayList<>();
        for (Collection collection : collectionList) {
            Integer id = collection.getId();
            String name = collection.getName();
            String img = collection.getFullImg();
            String location = museumMapper.selectNameById(collection.getMuseumId());
            boolean isLove = false;

            ImgParam imgParam;
            try {
                //缓存图片高和宽，减少io开支
                imgParam = cacheService.ioImg(img);
            } catch (Exception e) {
                log.info("图片为空，已跳过 [" + collection.getName() + "]");
                continue;
            }
            if (isLogin && loveMapper.selectToCount(userId, id) != 0) isLove = true;
            InfoParam param = new InfoParam(id, name, location, img, imgParam.getHeight(), imgParam.getWidth(), isLove);
            params.add(param);
        }
        InfoResult result = new InfoResult(params);
        return CommonResult.success(result);
    }

    @Override
    public synchronized CommonResult<MoreInfoVo> getMoreInfo(Integer collectionId, HttpServletRequest request) {
        //鉴权
        Integer userId = JWTUtil.getUserIdCheck(request);
        if (userId == TOKEN_WRONG) return CommonResult.tokenWrong();
        if (userId == TOKEN_NULL) return CommonResult.tokenNull();
        //添加历史记录
        int count = 0;
        boolean isLove = false;
        if (historyMapper.countToUpdate(collectionId, userId) != 0) {
            count = historyMapper.countNum(collectionId, userId);
            if (historyMapper.deleteToUpdate(collectionId, userId) == 0) return CommonResult.fail("delete failed");
        }
        if (historyMapper.insertOne(collectionId, userId, dateFormat.format(new Date().getTime()), ++count) == 0) {
            return CommonResult.fail("Insert failed");
        }
        MoreInfoVo collection = collectionMapper.selectAllInfoById(collectionId);
        if (loveMapper.selectToCount(userId, collectionId) != 0) isLove = true;
        collection.setIsLove(isLove);
        String content = collection.getMore().replaceAll(REGEX_ALL_BRACKETS, "");
        collection.setMore(content);
        return CommonResult.success(collection);
    }
}
