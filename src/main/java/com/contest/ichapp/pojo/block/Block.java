package com.contest.ichapp.pojo.block;

import com.contest.ichapp.util.cryptoUtil.StringUtil;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
@NoArgsConstructor
public class Block implements Serializable {
    public String hash;
    public String preHash;
    public long timeStamp;
    public Transaction transaction;

    public Block(String preHash, Transaction transaction) {
        this.preHash = preHash;
        this.transaction = transaction;
        this.timeStamp = new Date().getTime();
        this.hash = calculateHash();
    }

    public String calculateHash() {
        String sha256;
        try {
            sha256 = StringUtil.SHA256(preHash + timeStamp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return sha256;
    }
}
