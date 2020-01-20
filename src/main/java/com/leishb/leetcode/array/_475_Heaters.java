package com.leishb.leetcode.array;

import java.util.Arrays;
import java.util.TreeSet;

/**
 * Created by me on 2020/1/16.
 */
public class _475_Heaters {


    /**
     * Accepted
     * @param houses
     * @param heaters
     * @return
     */
    public int findRadius(int[] houses, int[] heaters) {
        TreeSet<Integer> set = new TreeSet<>();
        for (int h : heaters)set.add(h);
        int res = 0;
        for (int h : houses){
            int cur = Integer.MAX_VALUE;
            Integer low = set.ceiling(h);
            Integer hi = set.floor(h);
            if (low!=null)cur = Math.min(cur, Math.abs(h-low));
            if (hi!=null)cur = Math.min(cur, Math.abs(h-hi));
            res = Math.max(cur, res);
        }
        return res;
    }


    /**
     * Accepted
     * @param houses
     * @param heaters
     * @return
     */
    public int findRadius2(int[] houses, int[] heaters) {
        Arrays.sort(houses);
        Arrays.sort(heaters);
        int res= 0 ;
        for (int h : houses){
            int cur = Integer.MAX_VALUE;
            int i = Arrays.binarySearch(heaters, h);
            if (i>=0)continue;
            i = -(i+1);
            if (i>0){
                cur = Math.min(cur, h- heaters[i-1]);
            }
            if (i<heaters.length){
                cur = Math.min(cur, heaters[i]-h);
            }
            res = Math.max(res, cur);
        }
        return res;
    }
}
