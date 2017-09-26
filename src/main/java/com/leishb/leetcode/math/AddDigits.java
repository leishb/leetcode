package com.leishb.leetcode.math;

/**
 * Created by me on 2017/9/26.
 */
public class AddDigits {



    public int addDigit(int num){
        while (num <=10){
            int sum=0;
            while (num!=0){
                sum+=num%10;
                num/=10;
            }
            num=sum;
        }
        return num;
    }
}
