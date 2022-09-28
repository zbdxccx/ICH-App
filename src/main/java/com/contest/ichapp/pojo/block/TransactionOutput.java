package com.contest.ichapp.pojo.block;

import lombok.ToString;

@ToString
public class TransactionOutput {
    /**
     * 交易输出编号id
     */
    public String id;
    /**
     * 这笔交易输出的接收方公钥（类似收款方银行账号）
     */
    public String recipient;
    /**
     * 交易输出额
     */
    public float value;
    /**
     * 创建这个交易输出的交易id
     */
    public String parentTransactionId;


    public TransactionOutput(String recipient, float value, String parentTransactionId) {
        this.recipient = recipient;
        this.value = value;
        this.parentTransactionId = parentTransactionId;
        //前面属性均赋值后再计算id
    }

}
