package com.contest.ichapp.util.algorithm;

import java.util.Map;
import java.util.Random;

public class AlgorithmUtil {
    public static int total = 0;
    public static int border = 0;

    /**
     * 简易推荐算法
     *
     * @author steadon
     */
    public static Integer recommend(Map<Integer, Integer> map) {
        total = 0;
        border = 0;
        int k;
        for (int i = 1; i <= 8; i++) {
            if (map.get(i) != null) total += map.get(i);
        }
        int random = new Random().nextInt(total) + 1;
        for (k = 1; k <= 8; k++) {
            if (map.get(k) == null) continue;
            Integer integer = map.get(k);
            if (integer == 0) continue;
            border += integer;
            if (random <= border) break;
        }
        return k;
    }
}
