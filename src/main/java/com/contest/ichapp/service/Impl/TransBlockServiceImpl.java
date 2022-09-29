package com.contest.ichapp.service.Impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.contest.ichapp.mapper.TransInfoMapper;
import com.contest.ichapp.mapper.UserMapper;
import com.contest.ichapp.pojo.block.*;
import com.contest.ichapp.pojo.domain.TransInfo;
import com.contest.ichapp.pojo.domain.User;
import com.contest.ichapp.pojo.dto.CommonResult;
import com.contest.ichapp.pojo.dto.param.TransParam;
import com.contest.ichapp.service.TransBlockService;
import com.contest.ichapp.util.JWTUtil.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@Slf4j
public class TransBlockServiceImpl implements TransBlockService {
    @Resource
    UserMapper userMapper;
    @Resource
    TransInfoMapper transInfoMapper;
    SimpleDateFormat dateFormat = new SimpleDateFormat("MMdd");

    @Override
    public CommonResult<BlockChain> transOne(HttpServletRequest request, TransParam transParam) {
        String token = JWTUtil.getToken(request);
        Integer userId = JWTUtil.getUserId(token);
        User user = userMapper.selectById(userId);
        Integer recipientId = transParam.getRecipientId();
        User recipientUser = userMapper.selectById(recipientId);
        Wallet sender = new Wallet(user);
        Wallet recipient = new Wallet(recipientUser);
        BlockChain chain = new BlockChain();
        log.info("交易开始");
        BlockChain.genesisTransaction = new Transaction(sender.publicKey, recipient.publicKey, null);
        BlockChain.genesisTransaction.transactionId = dateFormat.format(new Date()) + userId;
        BlockChain.genesisTransaction.outputs.add(new TransactionOutput(BlockChain.genesisTransaction.recipient, BlockChain.genesisTransaction.transactionId));
        BlockChain.UTXOs.put(BlockChain.genesisTransaction.outputs.get(0).id, BlockChain.genesisTransaction.outputs.get(0));
        Block genesis = new Block("0");
        log.info("生成创世区块");
        genesis.addTransaction(BlockChain.genesisTransaction);
        genesis.setMerkleRoot(sender + " send to " + recipient);
        chain.addBlock(genesis);
        Block block = new Block(genesis.hash);
        log.info("生成新区块");
        chain.addBlock(block);
        log.info("交易结束");
        if (!chain.isChainValid()) return CommonResult.fail("区块链校验未通过");
        transInfoMapper.updateAll(transParam.getCollectionId(), recipientId, transParam.getTransId(), chain.toJson());
        return CommonResult.success("交易成功");
    }

    @Override
    public CommonResult decode(HttpServletRequest request) {
        TransInfo transInfo = transInfoMapper.selectById("1");
        String transactionBlock = transInfo.getTransactionBlock();
        JSONArray jsonObject = JSONObject.parseArray(transactionBlock);
        return CommonResult.success(jsonObject);
    }

}
