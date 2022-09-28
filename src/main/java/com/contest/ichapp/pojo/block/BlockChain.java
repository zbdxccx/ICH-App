package com.contest.ichapp.pojo.block;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

@Data
@AllArgsConstructor
public class BlockChain {
    //区块链
    List<BlockChain> blockchain = new ArrayList<>();
    //哈希值，判断挖矿成功与否
    private String hash;
    //前块哈希值，便于之后验证成功与否
    private String previousHash;
    //所需存储数据
    private String data;
    //时间戳
    private long timeStamp;
    //用于判断”挖矿“成功的密码学数字
    private int nonce;

    //构析方法，得到所需信息
    public BlockChain(String data, String previousHash, long timeStamp) {
        this.data = data;
        this.previousHash = previousHash;
        this.timeStamp = timeStamp;
        this.hash = calculateBlockHash();
    }

    private String calculateBlockHash() {
        //生成独属的字符串，方便之后转换
        String dataToHash = previousHash + timeStamp + nonce + data;
        MessageDigest digest;
        byte[] bytes = null;
        try {
            //创建一个提供信息摘要算法的对象，初始化为SHA-256算法对象
            digest = MessageDigest.getInstance("SHA-256");
            //用对象调用，信息摘要计算方法，计算后获得字节数组
            //dataToHash.getBytes(UTF_8)，将字符串转换为字节数组
            bytes = digest.digest(dataToHash.getBytes(UTF_8));
        } catch (Exception e) {
            e.printStackTrace();
        }
        //用缓存字符串
        StringBuffer buffer = new StringBuffer();
        for (byte b : bytes) {
            //延伸字符串
            //String.format("%02x", b)，以十六进制输出,2为指定的输出字段的宽度.如果位数小于2,则左端补0
            buffer.append(String.format("%02x", b));
        }
        //返回字符串
        return buffer.toString();
    }
}

