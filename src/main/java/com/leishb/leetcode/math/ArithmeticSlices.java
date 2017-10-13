package com.leishb.leetcode.math;

import com.leishb.leetcode.tag.DynamicProgramming;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by me on 2017/10/10.
 */
@DynamicProgramming
public class ArithmeticSlices {

    @Test
    public void test(){
        Assert.assertTrue(numberOfArithmeticSlices(new int[]{1,2,3,4})==3);
        Assert.assertTrue(numberOfArithmeticSlices(new int[]{1,2,3,5,6,7})==2);
    }


    /**
     * Accepted
     * @param A
     * @return
     */
    public int numberOfArithmeticSlices(int[] A) {
        if (A.length<3){
            return 0;
        }
        int[] dp = new int[A.length];
        int[] preDp = new int[A.length];

        //init
        dp[0]=0;
        dp[1]=0;
        preDp[0]=0;
        preDp[1]=0;
        if (A[1]-A[0]==A[2]-A[1]){
            dp[2]=1;
            preDp[2]=1;
        }
        for (int i=3;i<A.length;i++){
            dp[i] +=dp[i-1];
            if (A[i]-A[i-1]==A[i-1]-A[i-2]){
                dp[i] +=1;
                dp[i] +=preDp[i-1];
                preDp[i]=dp[i]-dp[i-1];//contains i and i-1 count
            }else {
                preDp[i]=0;
            }
        }
        return dp[A.length-1];
    }
}
