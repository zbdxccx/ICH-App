package com.contest.ichapp.pojo.block;


import com.contest.ichapp.pojo.domain.User;

public class Test {
    public static void main(String[] args) {
        //初始化区块链
        BlockChain chain = new BlockChain();
        User user1 = new User(1, "wbr", "123");
        User user2 = new User(2, "museum", "0000");
        //创建钱包
        Wallet wallet = new Wallet(user1);
        //初始交易的钱包
        Wallet coinBase = new Wallet(user2);

        //创建初始交易
        //最初的交易的value从coinbase凭空出现
        System.out.println("第一次交易：coinbase向wallet转移物品X");
        BlockChain.genesisTransaction = new Transaction(coinBase.publicKey, wallet.publicKey, null);
        //初始交易id设为0
        BlockChain.genesisTransaction.transactionId = "0";
        //因为初始交易是凭空生成，与普通交易不同，所以很多参数需要手动设置
        BlockChain.genesisTransaction.outputs.add(new TransactionOutput(BlockChain.genesisTransaction.recipient, BlockChain.genesisTransaction.transactionId));
        //将本次交易输出添加到UTXOs
        BlockChain.UTXOs.put(BlockChain.genesisTransaction.outputs.get(0).id, BlockChain.genesisTransaction.outputs.get(0));
        //前面的哈希值为手动设为0
        Block genesis = new Block("0");
        System.out.println("生成创世区块");
        //添加交易
        genesis.addTransaction(BlockChain.genesisTransaction);
        //将该块加入区块链中
        chain.addBlock(genesis);
        //新生成一个区块用于记账
        Block block1 = new Block(genesis.hash);
        System.out.println("生成第二个区块");
        //将该块加入区块链中
        chain.addBlock(block1);
        System.out.println("第一次交易结束");
        if (!chain.isChainValid()) System.out.println("区块链无效");
        for (Block block : BlockChain.blockChain) {
            if (block.getTransactions().size() != 0) {
                System.out.println(block);
                System.out.println(block.getTransactions().get(0).outputs.get(0).toString());
                System.out.println(chain.toJson());
            }
        }
    }
}
