package com.contest.ichapp.pojo.block;

import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
public class Transaction implements Serializable {
    public Integer senderId;
    public Integer receiverId;
    public Integer collectionId;
    public String time;
    public String timeNum;
}
