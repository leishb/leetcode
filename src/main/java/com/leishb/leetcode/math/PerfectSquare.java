package com.leishb.leetcode.math;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

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
        Assert.assertTrue(judgeSumOfSqares2(0));
        Assert.assertTrue(judgeSumOfSqares2(5));
        Assert.assertTrue(judgeSumOfSqares2(10));
        Assert.assertTrue(!judgeSumOfSqares2(6));
        long start = System.currentTimeMillis();
        boolean isSq = judgeSumOfSqares2(2147482647);
        System.out.println("result :"+ isSq + ", time: " + (System.currentTimeMillis()-start));
    }



    /**
     * Accepted
     * @param num
     * @return
     */
    public boolean judgeSumOfSqares(int num){
        for (int i=0;i<Math.sqrt(num);i++){
            if ((int)Math.sqrt(num -i*i) == Math.sqrt(num -i*i)){
                return true;
            }
        }
        return false;
    }

    /**
     * Accepted
     * @param num
     * @return
     */
    public boolean judgeSumOfSqares2(int num){
        int i=0;
        int j= (int) Math.sqrt(num);
        while (i<=j){
            int cur = i*i + j*j;
            if (num==cur){
                return true;
            }else if (num > cur){
                i++;
            }else {
                j--;
            }
        }
        return false;
    }


    /**
     * Accepted
     * @param num
     * @return
     */
    public boolean sumOfSquares(int num){
        Set<Integer> squares = new HashSet<Integer>();
        //i*i<=num && i<=Math.sqrt(num)时间差别较大
        for (int i=0;i<=Math.sqrt(num);i++){
            int k = num - i*i;
            squares.add(i*i);
            if (squares.contains(k)){
                return true;
            }
        }
        return false;
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
