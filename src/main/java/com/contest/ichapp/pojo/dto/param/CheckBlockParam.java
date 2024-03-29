package com.contest.ichapp.pojo.dto.param;

import com.contest.ichapp.pojo.block.Transaction;
import com.contest.ichapp.pojo.block.TransactionMore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CheckBlockParam extends CheckBlockOriginParam {
    private String museum;
    private String checkId;
    private String transId;
    private Integer transNum;
    private TransactionMore LastTransaction;

    public CheckBlockParam(String museum, TransactionMore transactionMore, Integer transNum, Transaction transaction, String tansId) {
        this.LastTransaction = transactionMore;
        this.museum = museum;
        this.transNum = transNum;
        this.checkId = transaction.collectionId + transaction.timeNum + transNum;
        this.transId = tansId;
    }
}
