package com.contest.ichapp.pojo.block;


import lombok.Data;

import java.util.ArrayList;

@Data
public class Transaction {
    /**
     * 交易号
     */
    public String transactionId;
    /**
     * 交易序号，用于记录交易数量
     */
    public static int sequence = 0;
    /**
     * 发送方的地址/public key
     */
    public String sender;
    /**
     * 接收方的地址/public key
     */
    public String recipient;
    /**
     * 交易物id
     */
    public int collectionId;
    /**
     * 本次交易所涉及到的所有交易输入
     */
    public ArrayList<TransactionInput> inputs;
    /**
     * 本次交易所涉及到的所有交易输出（第0位output是发给别人的，第1位output是发给自己的）
     */
    public ArrayList<TransactionOutput> outputs = new ArrayList<>();

    public Transaction(String from, String to, int collectionId, ArrayList<TransactionInput> inputs) {
        this.sender = from;
        this.recipient = to;
        this.collectionId = collectionId;
        this.inputs = inputs;
    }

    /**
     * 实现一次交易
     */
    public boolean processTransaction() {

        //根据交易输出的id从整个区块链中有效的UTXO集合中获取对应的UTXO
        for (TransactionInput input : inputs) {
            input.UTXO = BlockChain.UTXOs.get(input.transactionOutputId);
        }

        //将本次交易中的所有交易输出添加到整个区块链的UTXO集合中（实现向所有用户通报这笔交易）
        for (TransactionOutput output : outputs) {
            BlockChain.UTXOs.put(output.id, output);
        }
        //移除整个区块链中本次交易中所有交易输入所对应的UTXO（每个UTXO只能用来支付一次）
        for (TransactionInput input : inputs) {
            if (input.UTXO != null) {
                BlockChain.UTXOs.remove(input.UTXO.id);
            }
        }
        return true;
    }
}