package com.leishb.leetcode.favorite;

import com.leishb.leetcode.tag.DivideAndConquer;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by me on 2019/8/13.
 */
public class _795_Number_of_Subarrays_with_Bounded_Maximum {

    /**
     * TLE
     * @param A
     * @param L
     * @param R
     * @return
     */
    public int numSubarrayBoundedMax(int[] A, int L, int R) {
        int ans = 0;
        for (int i=0;i<A.length;i++){
            int max = A[i];
            for (int j=i;j<A.length;j++){
                max = Math.max(A[j], max);
                if (max>=L && max<=R){
                    ans++;
                }
            }
        }
        return ans;
    }


    /**
     * Accepted
     * @param A
     * @param L
     * @param R
     * @return
     */
    @DivideAndConquer
    public int numSubarrayBoundedMax2(int[] A, int L, int R) {
        return numSubarrayBoundedMax(A, 0, A.length-1,L, R);
    }

    public int numSubarrayBoundedMax(int[] A, int start, int end , int L, int R) {
        int ans = 0;
        for (int i=start;i<=end;i++){
            if (A[i]>R){
                return numSubarrayBoundedMax(A, start, i-1, L, R) + numSubarrayBoundedMax(A, i+1, end, L, R);
            }
        }
        int prev = 0;
        for (int i=start;i<=end;i++){
            if (A[i]>=L){
                ans += i-start+1;
                prev = i-start+1;
            }else if (A[i] < L) {
                ans += prev;
            }
        }
        return ans;
    }


    /**
     * Accepted
     * @param A
     * @param L
     * @param R
     * @return
     */
    public int numSubarrayBoundedMax3(int[] A, int L, int R) {
        int j=0,prev = 0, ans=0;
        for (int i=0;i<A.length;i++){
            if (A[i]>=L && A[i]<=R){
                ans += i-j+1;
                prev = i-j+1;
            }else if (A[i]<L){
                ans += prev;
            }else {
                j=i+1;
                prev = 0;
            }
        }
        return ans;
    }



    @Test
    public void test(){
        Assert.assertTrue(numSubarrayBoundedMax2(new int[]{2,9,2,5,6}, 2, 8)==7);
        Assert.assertTrue(numSubarrayBoundedMax2(new int[]{2,9,2,5,6,1}, 2, 8)==10);
        Assert.assertTrue(numSubarrayBoundedMax2(new int[]{2,9,2,5,6,1, 0}, 2, 8)==13);
        Assert.assertTrue(numSubarrayBoundedMax2(new int[]{2,9,2,5,6,1,0,2}, 2, 8)==19);
        Assert.assertTrue(numSubarrayBoundedMax2(new int[]{2,1,4,3}, 2, 3)==3);

        Assert.assertTrue(numSubarrayBoundedMax(new int[]{2,9,2,5,6}, 2, 8)==7);
        Assert.assertTrue(numSubarrayBoundedMax(new int[]{2,9,2,5,6,1}, 2, 8)==10);
        Assert.assertTrue(numSubarrayBoundedMax(new int[]{2,9,2,5,6,1, 0}, 2, 8)==13);
        Assert.assertTrue(numSubarrayBoundedMax(new int[]{2,9,2,5,6,1,0,2}, 2, 8)==19);
        Assert.assertTrue(numSubarrayBoundedMax(new int[]{2,1,4,3}, 2, 3)==3);

        Assert.assertTrue(numSubarrayBoundedMax3(new int[]{2,9,2,5,6}, 2, 8)==7);
        Assert.assertTrue(numSubarrayBoundedMax3(new int[]{2,9,2,5,6,1}, 2, 8)==10);
        Assert.assertTrue(numSubarrayBoundedMax3(new int[]{2,9,2,5,6,1, 0}, 2, 8)==13);
        Assert.assertTrue(numSubarrayBoundedMax3(new int[]{2,9,2,5,6,1,0,2}, 2, 8)==19);
        Assert.assertTrue(numSubarrayBoundedMax3(new int[]{2,1,4,3}, 2, 3)==3);
    }
}
