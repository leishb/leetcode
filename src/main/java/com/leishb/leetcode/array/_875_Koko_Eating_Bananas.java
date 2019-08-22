package com.leishb.leetcode.array;

import com.leishb.leetcode.tag.BinarySearch;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by me on 2019/8/22.
 */
public class _875_Koko_Eating_Bananas {

    /**
     * Accepted
     * @param piles
     * @param H
     * @return
     */
    @BinarySearch
    public int minEatingSpeed(int[] piles, int H) {
        int low = 1;
        int high = 0;
        for (int p : piles){
            high = Math.max(p, high);
        }
        int ans = high;
        while (low<=high){
            int mid = (low+high)/2;
            if (canFinish(piles, H, mid)){
                high = mid-1;
                ans = Math.min(ans, mid);
            }else {
                low = mid+1;
            }
        }
        return ans;
    }


    private boolean canFinish(int[] piles, int H, int speed){
        int total = 0;
        for (int p : piles){
            if (p<=speed){
                total+=1;
            }else {
                total+=(p%speed==0)?p/speed:(p/speed+1);
            }
        }
        return total<=H;
    }


    @Test
    public void test(){
        Assert.assertTrue(minEatingSpeed(new int[]{3,6,7,11}, 8)==4);
        Assert.assertTrue(minEatingSpeed(new int[]{30,11,23,4,20}, 5)==30);
        Assert.assertTrue(minEatingSpeed(new int[]{30,11,23,4,20}, 6)==23);
    }
}
