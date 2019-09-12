package com.leishb.leetcode.array;

import com.leishb.leetcode.tag.BinarySearch;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by me on 2019/9/10.
 */
@BinarySearch
public class _1011_Capacity_To_Ship_Packages_Within_D_Days {

    public int shipWithinDays(int[] weights, int D) {
        int low = 0;
        int high = 0;
        int ans = Integer.MAX_VALUE;
        for (int weight : weights){
            high+=weight;
            low = Math.max(low, weight);
        }
        while (low<=high){
            int mid = (low+high)/2;
            if (canShip(weights, D, mid)){
                ans = Math.min(mid, ans);
                high = mid-1;
            }else {
                low = mid+1;
            }
        }
        return ans;
    }


    private boolean canShip(int[] weights, int D, int capacity){
        int days = 0;
        int temp = capacity;
        int i=0;
        while (i<weights.length){
            while (i<weights.length && temp-weights[i] >= 0){
                temp-=weights[i];
                i++;
            }
            days+=1;
            temp = capacity;
        }
        return days<=D;
    }

    @Test
    public void test(){
        Assert.assertTrue(shipWithinDays(new int[]{1,2,3,1,1},4)==3);
    }
}
