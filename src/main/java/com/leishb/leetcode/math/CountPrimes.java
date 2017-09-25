package com.leishb.leetcode.math;

/**
 * Created by me on 2017/9/25.
 */
public class CountPrimes {


    public int countPrimes(int n){
        if (n<=1){
            return 0;
        }
        boolean[] notPrimes = new boolean[n];
        int count = 0;
        for (int i = 2;i<n;i++){
            if (!notPrimes[i]){
                count++;
                for (int j=2;i*j<n;j++){
                    notPrimes[i*j]=true;
                }
            }
        }
        return count;
    }
}
