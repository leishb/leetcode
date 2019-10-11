package com.leishb.leetcode.design;

import java.util.Random;
import java.util.TreeMap;

/**
 * Created by me on 2019/10/10.
 */
public class _528_Random_Pick_with_Weight {

    int sum = 0;
    TreeMap<Integer, Integer> map = new TreeMap<>();

    public _528_Random_Pick_with_Weight(int[] w) {
        for (int i=0;i<w.length;i++){
            sum += w[i];
            map.put(sum, i);
        }
    }

    public int pickIndex() {
        Random random = new Random();
        int r = random.nextInt(sum);
        return map.higherEntry(r).getValue();
    }
}
