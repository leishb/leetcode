package com.leishb.leetcode.math;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by me on 2017/10/9.
 */
public class SuperPow {

    @Test
    public void test(){

        Assert.assertTrue(superPow(2, new int[]{1,0})==1024);
    }


    public int superPow(int a, int[] b){
        if (a<=1){
            return a;
        }

        return 0;
    }
}
