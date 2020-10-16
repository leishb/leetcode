package com.leishb.leetcode.geometry;

import org.junit.Test;

public class _1515_Best_Position_for_a_Service_Centre {

    /**
     * Accepted
     * @param positions
     * @return
     */
    public double getMinDistSum(int[][] positions) {
        double left = 100, bottom = 100, right = 0, top = 0;
        for (int[] p : positions){
            left = Math.min(left, p[0]);
            bottom = Math.min(bottom, p[1]);
            right = Math.max(right, p[0]);
            top = Math.max(top, p[1]);
        }
        double ans = Double.MAX_VALUE, minX = 0.0, minY = 0.0;
        for (double delta = 10; delta >= 0.0001; delta /=10){
            for (double x=left;x<=right;x+=delta) {
                for (double y=bottom;y<=top;y+=delta){
                    double dist = 0;
                    for (int[] p : positions) {
                        dist += Math.sqrt((p[0] - x) * (p[0] - x) + (p[1] - y) * (p[1] - y));
                    }
                    if (dist < ans) {
                        ans = dist;
                        minX = x;
                        minY = y;
                    }
                }
            }
            left = minX - delta;
            right = minX + delta;
            bottom = minY - delta;
            top = minY + delta;
        }
        return  ans == Double.MAX_VALUE ? 0 : ans;
    }


    @Test
    public void test(){
        System.out.println(getMinDistSum(new int[][]{{0,1},{3,2},{4,5},{7,6},{8,9},{11,1},{2,12}}));
    }


}
