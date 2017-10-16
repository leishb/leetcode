package com.leishb.leetcode.math;

/**
 * Created by me on 2017/10/16.
 */
public class RangeAddition {


    /**
     * Accepted
     * @param m
     * @param n
     * @param ops
     * @return
     */
    public int maxCount(int m, int n, int[][] ops) {
        int minX = m;
        int minY = n;
        for(int i=0;i<ops.length;i++){
            int[] a = ops[i];
            if(a[0] < minX){
                minX = a[0];
            }
            if(a[1] < minY){
                minY = a[1];
            }
        }
        return minX * minY;
    }
}
