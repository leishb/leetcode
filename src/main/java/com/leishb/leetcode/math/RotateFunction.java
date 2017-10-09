package com.leishb.leetcode.math;

import org.junit.Assert;
import org.junit.Test;

import java.util.Random;

/**
 * Created by me on 2017/10/9.
 */
public class RotateFunction {

    @Test
    public void test(){
        Assert.assertTrue(maxRotateFunction2(new int[]{4, 3, 2, 6})==26);

        int[] nums = new int[87000];
        for (int i=0;i<nums.length;i++){
            nums[i]= new Random().nextInt(100);
        }
        long start = System.currentTimeMillis();
        System.out.println(maxRotateFunction2(nums));
        System.out.println("cost : " + (System.currentTimeMillis()-start));
    }

    /**
     * Accepted
     * @param A
     * @return
     */
    public int maxRotateFunction2(int[] A) {
        if (A.length == 0) {
            return 0;
        }
        int sum =0;
        int init = 0;
        for (int i=0;i<A.length;i++){
            sum += A[i];
            init += A[i]*i;
        }
        int result = init;
        for (int i=1;i<A.length;i++){
            result = Math.max(result, sum-A.length*A[A.length-i]+init);
            init = sum-A.length*A[A.length-i]+init;
        }
        return result;
    }



    /**
     * time limited exceeded
     * @param A
     * @return
     */
    public int maxRotateFunction(int[] A) {
        if (A.length==0){
            return 0;
        }
        int result = Integer.MIN_VALUE;
        for (int k=0;k<A.length;k++){
            int sum = 0;
            for (int i=0;i<A.length;i++){
                sum += A[i] * ((i+k)%A.length);
            }
            result = Math.max(result, sum);
        }
        return result;
    }
}
