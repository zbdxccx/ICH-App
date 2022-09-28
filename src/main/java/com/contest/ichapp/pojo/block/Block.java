package com.contest.ichapp.pojo.block;

import com.contest.ichapp.util.CryptoUtil.StringUtil;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * 区块结构
 *
 * @author Steadon
 */
@Data
public class Block implements Serializable {
    public String hash;
    public String prevHash;
    public long timestamp;
    public ArrayList<Transaction> transactions = new ArrayList<>();
    //merkleRoot充当data的作用（因为区块block本质就是个账本，用交易来充当数据最合理）
    public String merkleRoot;

    public Block(String prevHash) {
        this.prevHash = prevHash;
        this.timestamp = new Date().getTime();
        //初始化哈希值必须在其它属性都已初始化之后
        try {
            this.hash = calculateHash();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 计算当前块的哈希值
     */
    public String calculateHash() throws Exception {
        //取消使用data生成hash而使用merkleRoot
        return StringUtil.applySha256(prevHash + merkleRoot + timestamp);
    }

    /**
     * 在将交易添加到块时执行交易
     */
    public void addTransaction(Transaction transaction) {
        //验证交易的有效性
        if (transaction == null) return;
        if (!prevHash.equals("0")) {
            if (!transaction.processTransaction()) {
                System.out.println("交易处理失败！");
                return;
            }
        }
        //将交易记录添加到区块中
        transactions.add(transaction);
        System.out.println("交易成功添加到Block中！");
    }
}
