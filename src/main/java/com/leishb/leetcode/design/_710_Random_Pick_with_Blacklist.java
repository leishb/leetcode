package com.leishb.leetcode.design;

import java.util.Map;
import java.util.Random;

/**
 * Created by me on 2020/1/16.
 */
public class _710_Random_Pick_with_Blacklist {


    /**
     * Accepted
     */
    class Solution {


        int M;
        Map<Integer, Integer> map;

        Random random;

        public Solution(int N, int[] blacklist) {
            random = new Random();
            M = N-blacklist.length;
            for (int b : blacklist){
                map.put(b, -1);
            }
            for (int b : blacklist){
                if(b < M){
                    while (map.containsKey(N-1)){
                        N--;
                    }
                    map.put(b, N-1);
                    N--;
                }
            }
        }

        public int pick() {
            int k = random.nextInt(M);
            if (map.containsKey(k)){
                return map.get(k);
            }
            return k;
        }
    }

}
