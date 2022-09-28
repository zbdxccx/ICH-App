package com.contest.ichapp.pojo.domain;

import lombok.Data;

@Data
public class TransInfo {
    private Integer id;
    private Integer collectionId;
    private String transactionId;
    private String transactionBlock;
}
