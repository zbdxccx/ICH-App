package com.contest.ichapp.pojo.dto.param;

import com.contest.ichapp.pojo.block.Transaction;
import com.contest.ichapp.pojo.block.TransactionMore;
import lombok.Data;

@Data
public class CheckBlockParam {
    private String museum;
    private String checkId;
    private Integer transNum;
    private TransactionMore LastTransaction;

    public CheckBlockParam(String museum, TransactionMore transactionMore, Integer transNum, Transaction transaction) {
        this.LastTransaction = transactionMore;
        this.museum = museum;
        this.transNum = transNum;
        this.checkId = transaction.collectionId + transaction.timeNum + transNum;
    }
}
