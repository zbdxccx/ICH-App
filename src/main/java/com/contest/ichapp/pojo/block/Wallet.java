package com.contest.ichapp.pojo.block;

import com.contest.ichapp.pojo.domain.User;

public class Wallet {
    //公钥
    public String publicKey;
    //私钥
    public String privateKey;

    public Wallet(User user) {
        generateKeyPair(user);
    }

    /**
     * 生成公私钥
     */
    public void generateKeyPair(User user) {
        privateKey = user.getUsername() + user.getPassword();
        publicKey = user.getUsername();
    }
}
