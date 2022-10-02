package com.contest.ichapp.service.Impl;

import com.contest.ichapp.mapper.CollectionMapper;
import com.contest.ichapp.mapper.MuseumMapper;
import com.contest.ichapp.mapper.TransInfoMapper;
import com.contest.ichapp.mapper.UserMapper;
import com.contest.ichapp.pojo.block.Block;
import com.contest.ichapp.pojo.block.BlockChain;
import com.contest.ichapp.pojo.block.Transaction;
import com.contest.ichapp.pojo.block.TransactionMore;
import com.contest.ichapp.pojo.dto.CommonResult;
import com.contest.ichapp.pojo.dto.param.AllBlockParam;
import com.contest.ichapp.pojo.dto.param.CheckBlockParam;
import com.contest.ichapp.pojo.dto.param.TransParam;
import com.contest.ichapp.pojo.dto.result.AllBlockResult;
import com.contest.ichapp.service.TransBlockService;
import com.contest.ichapp.util.JWTUtil.JWTUtil;
import com.contest.ichapp.util.redis.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class TransBlockServiceImpl implements TransBlockService {
    @Resource
    RedisUtil redisUtil;
    @Resource
    TransInfoMapper transInfoMapper;
    @Resource
    MuseumMapper museumMapper;
    @Resource
    CollectionMapper collectionMapper;
    @Resource
    UserMapper userMapper;

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    SimpleDateFormat dateFormatNum = new SimpleDateFormat("yyyyMMdd");

    @Override
    @SuppressWarnings("unchecked")
    public CommonResult<String> transOne(HttpServletRequest request, TransParam transParam) {
        String token = JWTUtil.getToken(request);
        Integer userId = JWTUtil.getUserId(token);
        Integer receiverId = transParam.getReceiverId();
        Integer collectionId = transParam.getCollectionId();
        String transId = transParam.getTransId();
        if (transInfoMapper.countForConfirm(transId, userId) != 1) return CommonResult.fail("当前用户无此藏品");
        ArrayList<Block> chain = (ArrayList<Block>) redisUtil.get(transId);
        BlockChain blockChain = new BlockChain();
        log.info("开始交易");
        Date date = new Date();
        log.info("生成事务");
        Transaction transaction = new Transaction();
        transaction.receiverId = receiverId;
        transaction.collectionId = collectionId;
        transaction.senderId = userId;
        transaction.time = dateFormat.format(date);
        transaction.timeNum = dateFormatNum.format(date);
        log.info("生成区块");
        Block block;
        if (chain == null) block = new Block("0", transaction);
        else {
            blockChain.blockChain = chain;
            block = new Block(chain.get(chain.size() - 1).hash, transaction);
        }
        if (transInfoMapper.updateUser(transId, receiverId, collectionId) != 1) return CommonResult.fail("转交藏品失败");
        blockChain.blockChain.add(block);
        log.info("添加区块成功");
        redisUtil.remove(transId);
        if (!blockChain.isChainValid()) {
            blockChain.blockChain.remove(blockChain.blockChain.size() - 1);
            log.info("区块异常，清空区块链");
            return CommonResult.fail("block error");
        }
        redisUtil.set(transId, blockChain.blockChain);
        log.info("完成事务");
        return CommonResult.success("succeed");
    }

    @Override
    @SuppressWarnings("unchecked")
    public CommonResult<CheckBlockParam> checkBlock(HttpServletRequest request, String transId) {
        ArrayList<Block> chain = (ArrayList<Block>) redisUtil.get(transId);
        BlockChain blockChain = new BlockChain();
        if (chain == null) return CommonResult.success("该藏品未有任何转交记录");
        blockChain.blockChain = chain;
        if (!blockChain.isChainValid()) {
            log.info("藏品交易有问题");
            return CommonResult.success("该藏品交易记录有问题");
        } else log.info("藏品为正品");
        Transaction firstTransaction = chain.get(0).transaction;
        String museumName = museumMapper.selectNameById(firstTransaction.senderId);
        //整理返回信息
        TransactionMore more = new TransactionMore();
        Transaction lastTransaction = chain.get(chain.size() - 1).transaction;
        more.time = lastTransaction.time;
        more.collection = collectionMapper.selectById(lastTransaction.collectionId).getName();
        more.receiver = userMapper.selectById(lastTransaction.receiverId).getUsername();
        more.sender = userMapper.selectById(lastTransaction.senderId).getUsername();
        //封装
        CheckBlockParam checkParam = new CheckBlockParam(museumName, more, chain.size(), lastTransaction);
        return CommonResult.success(checkParam);
    }

    @Override
    public CommonResult<AllBlockResult> getCollectionBlock(HttpServletRequest request) {
        String token = JWTUtil.getToken(request);
        Integer userId = JWTUtil.getUserId(token);
        List<AllBlockParam> allBlockParams = transInfoMapper.selectByUserId(userId);
        if (allBlockParams.size() == 0) return CommonResult.success("未拥有任何藏品");
        AllBlockResult result = new AllBlockResult(allBlockParams, allBlockParams.size());
        return CommonResult.success(result);
    }
}
