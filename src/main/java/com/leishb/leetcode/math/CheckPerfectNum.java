package com.leishb.leetcode.math;

/**
 * Created by me on 2017/10/14.
 */
public class CheckPerfectNum {


    /**
     * Accepted
     * @param num
     * @return
     */
    public boolean checkPerfectNumber(int num){
        int sum =1;
        for (int i=2;i<=Math.sqrt(num);i++){
            if (num%i==0){
                sum+=i;
                if (num/i!=i){
                    sum+=num/i;
                }
            }
        }
        return num!=1 && sum == num;
    }
}
