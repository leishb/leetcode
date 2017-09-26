package com.leishb.leetcode.math;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by me on 2017/9/25.
 */
public class CountDigitOne {

    @Test
    public void test(){
//        Assert.assertTrue(countDigitOne(9)==1);
        Assert.assertTrue(countDigitOne(10)==2);
    }


    public int countDigitOne(int n){
        int count = 0;
        int iFactor = 1;
        while (iFactor <= n){
            int mode = (n/iFactor)%10;
            if (mode==0){
                count += n/(iFactor*10) * iFactor;
            }
            if (mode==1){
                count += n/(iFactor*10) * iFactor + n%(iFactor*10);
            }
            if (mode >1){
                count += n/(iFactor*10) * iFactor +  iFactor;
            }
            iFactor *= 10;
        }
        return count;
    }
}
