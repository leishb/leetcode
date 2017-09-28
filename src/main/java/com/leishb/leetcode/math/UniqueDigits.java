package com.leishb.leetcode.math;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by me on 2017/9/28.
 */
public class UniqueDigits {

    @Test
    public void test(){
        Assert.assertTrue(uniqueDigits2(1)==10);
        Assert.assertTrue(uniqueDigits2(2)==91);
        Assert.assertTrue(uniqueDigits2(3)==739);
    }

    /**
     * 动态规划
     * Accepted
     * @param n
     * @return
     */
    public int uniqueDigits2(int n) {
        if (n == 0) {
            return 1;
        }
        if (n>10){
            return uniqueDigits2(10);
        }
        int[] digits=new int[n+1];
        digits[0]=1;
        for (int i=1;i<=n;i++){
            int sum=1;
            int k=0;
            while (k<i){
                if (k==0){
                    sum*=9;
                }else {
                    sum*=(10-k);
                }
                k++;
            }
            digits[i]=digits[i-1]+sum;
        }
        return digits[n];
    }

    /**
     * 递归
     * @param n
     * @return
     */
    public int uniqueDigits3(int n) {
        if (n == 0) {
            return 1;
        }
        if (n>10){
            return uniqueDigits2(10);
        }
        int sum = 1;
        int k = 0;
        while (k<n){
            if (k==0){
                sum*=9;
            }else {
                sum*=(10-k);
            }
            k++;
        }
        return sum+uniqueDigits3(n-1);
    }

    /**
     * not accepted
     * @param n
     * @return
     */
    public int uniqueDigits(int n){
        if (n==0){
            return 1;
        }
        if (n>9){
            return uniqueDigits(9);
        }
        int[] tenFac = new int[11];
        tenFac[0]=1;
        for (int i=1;i<=10;i++){
            tenFac[i]=tenFac[i-1]*i;
        }
        int sum=0;
        sum+=tenFac[10]/tenFac[10-n];
        //n个数中有i个0
        for (int i=2;i<n;i++){
            sum+=tenFac[9]/tenFac[9-(n-i)];
            //当只有n-1个0时，包括1~9和10~90
            if (i==n-1){
                sum+=tenFac[9]/tenFac[9-(n-i)];
            }
        }
        //包括0
        if (n>1){
            sum+=1;
        }


        return sum;
    }
}
