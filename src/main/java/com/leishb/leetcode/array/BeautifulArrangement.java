package com.leishb.leetcode.array;

import com.leishb.leetcode.tag.BackTracking;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by me on 2017/12/6.
 */
@BackTracking
public class BeautifulArrangement {


    @Test
    public void test(){
        Assert.assertArrayEquals(constructArray(3,1), new int[]{1,2,3});
        Assert.assertArrayEquals(constructArray(3,2), new int[]{1,3,2});
        Assert.assertArrayEquals(constructArray(9,5), new int[]{1,6,2,5,3,4,7,8,9});
        Assert.assertArrayEquals(constructArray(9,1), new int[]{1,2,3,4,5,6,7,8,9});
        Assert.assertArrayEquals(constructArray(9,8), new int[]{1,9,2,8,3,7,4,6,5});
        Assert.assertArrayEquals(constructArray(5,2), new int[]{1,3,2,4,5});


        Assert.assertTrue(new BeautifulArrangement().countArrangement(3)==3);
    }



    private int count = 0;

    /**
     * Accepted
     * @param N
     * @return
     */
    public int countArrangement(int N) {
        boolean[] used = new boolean[N+1];
        backtracking(1, N, used);
        return count;
    }


    private void backtracking(int level, int n, boolean[] used){
        if(level>n){
            count+=1;
            return;
        }
        for(int i=1;i<=n;i++){
            if(!used[i] && (level%i==0 || i%level==0)){
                used[i] = true;
                backtracking(level+1, n, used);
                used[i] = false;
            }
        }
    }

    /**
     * Accepted
     * @param n
     * @param k
     * @return
     */
    public int[] constructArray(int n, int k) {
        int[] nums = new int[n];
        int i=1;
        int j=k+1;
        int size = 0;
        while (size<=k){
            if (size%2==0){
                nums[size++] = i++;
            }else {
                nums[size++] = j--;
            }
        }
        int temp = k+2;
        while (size<n){
            nums[size++] = temp++;
        }
        return nums;
    }

    /**
     * Accepted
     * @param n
     * @param k
     * @return
     */
    public int[] constructArray2(int n, int k) {
        int[] nums = new int[n];
        int i=1;
        int j=n;
        int size=0;
        while (size<k){
            nums[size++] = i++;
            if (size<k){
                nums[size++] = j--;
            }
        }
        if (k%2==0){
            while (size<n){
                nums[size++] = j--;
            }
        }else {
            while (size<n){
                nums[size++] = i++;
            }
        }
        return nums;
    }

}
