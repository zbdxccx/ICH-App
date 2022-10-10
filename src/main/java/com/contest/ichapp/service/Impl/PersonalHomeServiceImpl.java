package com.contest.ichapp.service.Impl;

import com.contest.ichapp.mapper.HistoryMapper;
import com.contest.ichapp.mapper.LoveMapper;
import com.contest.ichapp.mapper.UserInfoMapper;
import com.contest.ichapp.pojo.domain.UserInfo;
import com.contest.ichapp.pojo.dto.CommonResult;
import com.contest.ichapp.pojo.dto.param.HistoryParam;
import com.contest.ichapp.pojo.dto.param.StringParam;
import com.contest.ichapp.pojo.dto.result.CollectionResult;
import com.contest.ichapp.pojo.dto.result.HistoryResult;
import com.contest.ichapp.pojo.dto.vo.CollectionVo;
import com.contest.ichapp.service.PersonalHomeService;
import com.contest.ichapp.util.cryptoUtil.StringUtil;
import com.contest.ichapp.util.jwtUtil.JWTUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.nio.file.Files;
import java.util.List;

import static sun.nio.ch.IOStatus.EOF;

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
        Integer userId = JWTUtil.getUserId_X(request);
        if (userId == -1) return CommonResult.tokenWrong();
        if (userId == -2) return CommonResult.tokenNull();

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
        Integer userId = JWTUtil.getUserId_X(request);
        if (userId == -1) return CommonResult.tokenWrong();
        if (userId == -2) return CommonResult.tokenNull();

        List<HistoryParam> histories = historyMapper.selectAllById(userId);
        for (HistoryParam historyParam : histories) {
            Integer collectionId = historyParam.getCollectionId();
            boolean isLove = loveMapper.selectToCount(userId, collectionId) != 0;
            historyParam.setIsLove(isLove);
        }
        return CommonResult.success(new HistoryResult(histories));
    }

    @Override
    public CommonResult<UserInfo> getPersonalInfo(HttpServletRequest request) {
        //鉴权
        Integer userId = JWTUtil.getUserId_X(request);
        if (userId == -1) return CommonResult.tokenWrong();
        if (userId == -2) return CommonResult.tokenNull();

        UserInfo userInfo = userInfoMapper.selectAllById(userId);
        if (userInfo.getNickname() == null) userInfo.setNickname("游客" + userId);
        if (userInfo.getSign() == null) userInfo.setSign("这个人很懒，什么都没留下。");
        return CommonResult.success(userInfo);
    }

    @Override
    public CommonResult<String> setName(HttpServletRequest request, StringParam param) {
        //鉴权
        Integer userId = JWTUtil.getUserId_X(request);
        if (userId == -1) return CommonResult.tokenWrong();
        if (userId == -2) return CommonResult.tokenNull();

        if (userInfoMapper.setNickname(param.getString(), userId) == 0) return CommonResult.fail("更改失败");
        return CommonResult.success("更改成功");
    }

    @Override
    public CommonResult<String> setSign(HttpServletRequest request, StringParam param) {
        //鉴权
        Integer userId = JWTUtil.getUserId_X(request);
        if (userId == -1) return CommonResult.tokenWrong();
        if (userId == -2) return CommonResult.tokenNull();

        if (userInfoMapper.setSign(param.getString(), userId) == 0) return CommonResult.fail("更改失败");
        return CommonResult.success("更改成功");
    }

    @Override
    public CommonResult<String> setHeadImg(HttpServletRequest request, StringParam param) {
        //鉴权
        Integer userId = JWTUtil.getUserId_X(request);
        if (userId == -1) return CommonResult.tokenWrong();
        if (userId == -2) return CommonResult.tokenNull();

        String imgName = "user" + userId + ".png";

        String imgStr = param.getString();
        if (imgStr != null && imgStr.length() > 0) {
//            byte[] bytes = imgStr.getBytes();
            byte[] bytes = StringUtil.hex2byte(imgStr);
            File file = new File(imgFilePath, imgName);
            try (InputStream in = new ByteArrayInputStream(bytes); OutputStream out = Files.newOutputStream(file.toPath())) {
                byte[] b = new byte[1024];
                int nRead;
                while ((nRead = in.read(b)) != EOF) {
                    out.write(b, 0, nRead);
                }
                out.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            if (userInfoMapper.setHeadUrl("http://haorui.xyz:8085/static/" + imgName, userId) == 0) {
                return CommonResult.fail("更新数据库失败");
            }
        }
        return CommonResult.success("设置成功");
    }
}
