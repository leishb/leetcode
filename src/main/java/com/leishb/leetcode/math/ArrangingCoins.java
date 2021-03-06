package com.leishb.leetcode.math;

import com.leishb.leetcode.tag.BinarySearch;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by me on 2017/10/12.
 */
@BinarySearch
public class ArrangingCoins {


    @Test
    public void test(){
        Assert.assertTrue(arrangeCoins(1804289383)==60070);
    }

    /**
     * Accepted
     * Attention : convert int to long to avoid overflow
     * 解方程：k*(k+1)/2 < =n && (k+1)*(k+2)/2 >n
     * @param n
     * @return
     */
    public int arrangeCoins(int n) {
        long left = 0;
        long right = n;
        while(left <= right){
            long mid = (right-left)/2 + left;
            long low = (mid+1)*mid/2;
            long upper = (mid+1)*(mid+2)/2;

            if(n >= low && n <upper){
                return (int) mid;
            }
            if(n==upper){
                return (int) (mid+1);
            }
            if(n >upper){
                left = mid+1;
            }
            if(n < low){
                right = mid-1;
            }
        }
        return (int) left;
    }
}
