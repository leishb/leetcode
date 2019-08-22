package com.leishb.leetcode.favorite;

/**
 * Created by me on 2019/8/14.
 */
public class _799_Champagne_Tower {

    /**
     * Accepted
     * @param poured
     * @param query_row
     * @param query_glass
     * @return
     */
    public double champagneTower(int poured, int query_row, int query_glass) {
        double[][] arr = new double[query_row+1][query_row+1];
        arr[0][0] = poured;
        for (int i=0;i<query_row;i++){
            for (int j=0;j<=i;j++){
                if (arr[i][j] > 1){
                    double p = (arr[i][j]-1.0)/2.0;
                    arr[i+1][j] += p;
                    arr[i+1][j+1] += p;
                }
            }
        }
        return Math.min(1.0, arr[query_row][query_glass]);
    }
}
