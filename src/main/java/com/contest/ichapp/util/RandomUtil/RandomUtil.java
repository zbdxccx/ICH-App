package com.contest.ichapp.util.RandomUtil;

import java.text.DecimalFormat;
import java.util.Random;

public class RandomUtil {
    private static final Random random = new Random();

    private static final DecimalFormat fourDf = new DecimalFormat("0000");

    private static final DecimalFormat sixDf = new DecimalFormat("000000");

    //生成4位随机数
    public static String getFourBitRandom() {
        return fourDf.format(random.nextInt(10000));
    }

    //生成6位随机数
    public static String getSixBitRandom() {
        return sixDf.format(random.nextInt(1000000));
    }
}