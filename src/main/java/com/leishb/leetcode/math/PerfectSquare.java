package com.leishb.leetcode.math;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by me on 2017/9/29.
 */
public class PerfectSquare {

    @Test
    public void test(){
        int sum=0;
        for (int i=1,j=1;i<100;i+=2,j++){
            sum+=i;
//            Assert.assertTrue(j*j==sum);
            Assert.assertTrue(isPerfectSquare(sum));
//            Assert.assertTrue(isPerfectSquare2(sum));
        }
        Assert.assertTrue(sumOfSquares(5));
        Assert.assertTrue(sumOfSquares(10));
        Assert.assertTrue(!sumOfSquares(6));
    }

    public boolean sumOfSquares(int num){
        if (num==0){
            return true;
        }
        if (isPerfectSquare2(num)){
            return true;
        }
        for (int i=1;i*i<num;i++){
            int k = num - i*i;
            if (isPerfectSquare2(k)){
                return true;
            }
        }
        return false;
    }

    /**
     *  1+3+5+7
     * @param num
     * @return
     */
    public boolean isPerfectSquare2(int num){
        int k=1;
        while (k*k<num){
            k++;
        }
        return k*k==num;
    }

    /**
     * 二分法
     * @param num
     * @return
     */
    public boolean isPerfectSquare(int num){
        if (num==1){
            return true;
        }
        int left=0;
        int right = num;
/*        while (left<=right){
            int mid = left + (right-left)/2;
            if (num%mid==0 && num/mid==mid){
                return true;
            }
            if (num/mid > mid){
                left=mid+1;
            }
            if (num/mid<mid){
                right=mid-1;
            }
        }*/

        for (int i=left;i<right;i++){
            int mid=left + (right-left)/2;
            if (left+1==right){
                return false;
            }
            if (num%mid == 0 && num/mid==mid){
                return true;
            }
            if (num/mid > mid){
                left=mid;
            }
            if (num/mid<mid){
                right=mid;
            }
        }
        return false;
    }
}
