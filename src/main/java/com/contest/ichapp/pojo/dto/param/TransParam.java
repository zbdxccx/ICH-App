package com.contest.ichapp.pojo.dto.param;

import lombok.Data;

@Data
public class TransParam {
    private Integer collectionId;
    private Integer receiverId;
    private String transId;
}