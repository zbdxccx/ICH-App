package com.contest.ichapp.service.Impl;

import com.contest.ichapp.mapper.HistoryMapper;
import com.contest.ichapp.mapper.LoveMapper;
import com.contest.ichapp.mapper.UserInfoMapper;
import com.contest.ichapp.pojo.domain.UserInfo;
import com.contest.ichapp.pojo.dto.CommonResult;
import com.contest.ichapp.pojo.dto.param.HistoryParam;
import com.contest.ichapp.pojo.dto.param.PersonalParam;
import com.contest.ichapp.pojo.dto.result.CollectionResult;
import com.contest.ichapp.pojo.dto.result.HistoryResult;
import com.contest.ichapp.pojo.dto.vo.CollectionVo;
import com.contest.ichapp.service.PersonalHomeService;
import com.contest.ichapp.util.jwtUtil.JWTUtil;
import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static com.contest.ichapp.common.Constant.TOKEN_NULL;
import static com.contest.ichapp.common.Constant.TOKEN_WRONG;

@Slf4j
@Service
public class PersonalHomeServiceImpl implements PersonalHomeService {
    @Resource
    LoveMapper loveMapper;
    @Resource
    HistoryMapper historyMapper;
    @Resource
    UserInfoMapper userInfoMapper;

    @Value("${img.filePath}")
    private String imgFilePath;

    @Override
    public synchronized CommonResult<CollectionResult> getAllCollection(HttpServletRequest request) {
        //鉴权
        Integer userId = JWTUtil.getUserIdCheck(request);
        if (userId == TOKEN_WRONG) return CommonResult.tokenWrong();
        if (userId == TOKEN_NULL) return CommonResult.tokenNull();

        List<CollectionVo> collections = loveMapper.selectByUserId(userId);
        for (CollectionVo collectionVo : collections) {
            Integer id = collectionVo.getId();
            boolean isLove = loveMapper.selectToCount(userId, id) != 0;
            collectionVo.setIsLove(isLove);
        }
        return CommonResult.success(new CollectionResult(collections, collections.size()));
    }

    @Override
    public synchronized CommonResult<HistoryResult> getAllHistory(HttpServletRequest request) {
        //鉴权
        Integer userId = JWTUtil.getUserIdCheck(request);
        if (userId == TOKEN_WRONG) return CommonResult.tokenWrong();
        if (userId == TOKEN_NULL) return CommonResult.tokenNull();

        List<HistoryParam> histories = historyMapper.selectAllById(userId);
        for (HistoryParam historyParam : histories) {
            Integer collectionId = historyParam.getCollectionId();
            boolean isLove = loveMapper.selectToCount(userId, collectionId) != 0;
            historyParam.setIsLove(isLove);
        }
        return CommonResult.success(new HistoryResult(histories));
    }

    @Override
    public synchronized CommonResult<UserInfo> getPersonalInfo(HttpServletRequest request) {
        //鉴权
        Integer userId = JWTUtil.getUserIdCheck(request);
        if (userId == TOKEN_WRONG) return CommonResult.tokenWrong();
        if (userId == TOKEN_NULL) return CommonResult.tokenNull();

        UserInfo userInfo = userInfoMapper.selectAllById(userId);
        if (userInfo.getNickname() == null) userInfo.setNickname("游客" + userId);
        if (userInfo.getSign() == null) userInfo.setSign("这个人很懒，什么都没留下。");
        return CommonResult.success(userInfo);
    }

    @Override
    public synchronized CommonResult<String> setNameAndSign(HttpServletRequest request, PersonalParam param) {
        //鉴权
        Integer userId = JWTUtil.getUserIdCheck(request);
        if (userId == TOKEN_WRONG) return CommonResult.tokenWrong();
        if (userId == TOKEN_NULL) return CommonResult.tokenNull();

        if (userInfoMapper.setNameAndSign(param.getName(), param.getSign(), userId) == 0)
            return CommonResult.fail("更改失败");
        return CommonResult.success("更改成功");
    }

    @Override
    @SneakyThrows
    public synchronized CommonResult<String> setHeadImg(HttpServletRequest request, MultipartFile file) {
        //鉴权
        Integer userId = JWTUtil.getUserIdCheck(request);
        if (userId == TOKEN_WRONG) return CommonResult.tokenWrong();
        if (userId == TOKEN_NULL) return CommonResult.tokenNull();

        String imgName = "user" + userId + ".png";
        String imgFilePathAll = imgFilePath + imgName;

        @Cleanup OutputStream out = Files.newOutputStream(Paths.get(imgFilePathAll));
        out.write(file.getBytes());
        out.flush();
        log.info("[NewPng]" + imgFilePathAll);

        if (userInfoMapper.setHeadUrl("http://haorui.xyz:8085/static/" + imgName, userId) == 0) {
            return CommonResult.fail("更新数据库失败");
        }
        return CommonResult.success("设置成功");
    }

}
