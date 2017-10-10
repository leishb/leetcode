package com.leishb.leetcode.math;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by me on 2017/10/10.
 */
public class NthDigit {

    @Test
    public void test(){
        Assert.assertTrue(findNthDigit2(11)==0);
        Assert.assertTrue(findNthDigit2(12)==1);
        Assert.assertTrue(findNthDigit2(8)==8);
    }


    /**
     * Accepted
     * @param n
     * @return
     */
    public int findNthDigit(int n) {
        int k = 1;
        while (n > Math.pow(10, k-1)*9*k){
            n-=Math.pow(10, k-1)*9*k;
            k++;
        }
        int mode = n%k;
        int nth = mode==0 ? n/k : (n/k)+1;
        int num = (int) (Math.pow(10, k-1)+nth-1);
        String value = String.valueOf(num);
        char c = value.charAt(mode==0?value.length()-1:mode-1);
        return c - '0';
    }


    public int findNthDigit2(int n) {
        int k = 1;
        long count = 9; //attention count as long to avoid overflow
        int start = 1;
        while (n > count*k){
            n-=count*k;
            count*=10;
            start*=10;
            k++;
        }
        start += (n-1)/k;
        String value = String.valueOf(start);
        char c = value.charAt((n-1)%k);
        return c - '0';
    }
}
