package com.leishb.leetcode.array;

import com.leishb.leetcode.tag.BinarySearch;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by me on 2019/11/27.
 */
@BinarySearch
public class _1231_Divide_Chocolate {


    /**
     * Accepted
     * @param sweetness
     * @param K
     * @return
     */
    public int maximizeSweetness(int[] sweetness, int K) {
        int low = 0, hi = 0, ans = 0;
        for (int i=0;i<sweetness.length;i++){
            low = Math.min(low, sweetness[i]);
            hi += sweetness[i];
        }
        while (low<=hi){
            int mid = (hi-low)/2+low;
            if (isPossible(sweetness, mid, K)){
                ans = mid ;
                low = mid + 1;
            }else {
                hi = mid - 1;
            }
        }
        return ans;
    }


    private boolean isPossible(int[] sweetness, int target , int k){
        int count = 0, sum = 0;
        for (int i=0;i<sweetness.length;i++){
            sum += sweetness[i];
            if (sum >= target){
                count++;
                sum = 0;
            }
        }
        return count>=k+1;
    }


    @Test
    public void test(){
        Assert.assertTrue(isPossible(new int[]{5,  6, 7,   8,   9,   1,2,3,4}, 5, 5));
    }
}
