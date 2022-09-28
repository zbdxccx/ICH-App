package com.contest.ichapp.pojo.block;


import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

@Slf4j
public class BlockChain {
    public static ArrayList<Block> blockChain = new ArrayList<>();
    /**
     * 用于记录所有有效的UTXO，键是String类型的TransactionOutputId
     */
    public static HashMap<String, TransactionOutput> UTXOs = new HashMap<>();
    /**
     * 初始交易（创建区块链时初始化第一笔交易）
     */
    public static Transaction genesisTransaction;

    /**
     * 检查区块链的有效性
     */
    public boolean isChainValid() {
        Block curBlock;
        Block prevBlock;

        //遍历blockchain，从1开始，保证prevBlock的有效性
        for (int i = 1; i < blockChain.size(); i++) {
            curBlock = blockChain.get(i);
            prevBlock = blockChain.get(i - 1);

            try {
                //检查hash值计算有效性
                if (!curBlock.hash.equals(curBlock.calculateHash())) {
                    log.info("block的hash值计算错误");
                    return false;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            //检查hash值前后对应关系正确性
            if (!prevBlock.hash.equals(curBlock.prevHash)) {
                log.info("当前block与前面block的hash值不对应");
                return false;
            }

            for (int t = 0; i < curBlock.transactions.size(); t++) {
                Transaction currentTransaction = curBlock.transactions.get(t);


                if (!Objects.equals(currentTransaction.outputs.get(0).recipient, currentTransaction.recipient)) {
                    log.info("第" + t + "个交易的交易输出目的方错误！");
                    return false;
                }
            }
        }
        log.info("区块链有效！");
        return true;
    }

    /**
     * 向区块链中添加块
     */
    public void addBlock(Block newBlock) {
        blockChain.add(newBlock);
    }

    /**
     * 将blockChain转换为json字符串本地存储
     */
    public String toJson() {
        return JSON.toJSONString(blockChain);
    }
}
