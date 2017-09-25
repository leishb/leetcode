package com.leishb.leetcode.math;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by me on 2017/9/21.
 */
public class TrailingZeroes {

    @Test
    public void test(){
        Assert.assertTrue(trailingZeroes(5)==1);
    }



    public int trailingZeroes(int n){
        int result = 0;
        while (n >0){
            result = result + n/5;
            n/=5;
        }
        return result;
    }
}
