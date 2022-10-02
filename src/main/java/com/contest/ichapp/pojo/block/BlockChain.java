package com.contest.ichapp.pojo.block;

import lombok.NoArgsConstructor;

import java.util.ArrayList;

@NoArgsConstructor
public class BlockChain {
    public ArrayList<Block> blockChain = new ArrayList<>();

    /**
     * 检查区块链的有效性
     *
     * @return boolean
     */
    public boolean isChainValid() {
        Block curBlock;
        Block preBlock;

        //遍历blockchain，从1开始，保证preBlock的有效性
        for (int i = 1; i < blockChain.size(); i++) {
            curBlock = blockChain.get(i);
            preBlock = blockChain.get(i - 1);

            try {
                //检查hash值计算有效性
                if (!curBlock.hash.equals(curBlock.calculateHash())) {
                    System.out.println("block的hash值计算错误");
                    return false;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            //检查hash值前后对应关系正确性
            if (!preBlock.hash.equals(curBlock.preHash)) {
                System.out.println("当前block与前面block的hash值不对应");
                return false;
            }
        }
        return true;
    }
}
