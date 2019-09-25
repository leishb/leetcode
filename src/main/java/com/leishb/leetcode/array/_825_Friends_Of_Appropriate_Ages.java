package com.leishb.leetcode.array;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.TreeMap;

/**
 * Created by me on 2019/9/24.
 */
public class _825_Friends_Of_Appropriate_Ages {


    public int numFriendRequests(int[] ages) {
        Arrays.sort(ages);
        TreeMap<Double, Integer> first = new TreeMap<>();
        TreeMap<Double, Integer> last = new TreeMap<>();
        for (int i=0;i<ages.length;i++){
            if (!first.containsKey(ages[i]*1.0)){
                first.put(ages[i]*1.0, i);
            }
            last.put(ages[i]*1.0, i);
        }
        int ans = 0;
        for (int i=0;i<ages.length;i++){
            if(ages[i]<15)continue;
            Double k1 = first.higherKey(ages[i]*0.5+7);
            Double k2 = last.floorKey(ages[i]*1.0);
            if (k1!=null && k2!=null && last.get(k2)-first.get(k1)>=0){
                ans += last.get(k2)-first.get(k1)+1;
                if (i>=first.get(k1) && i<=last.get(k2)){
                    ans -=1;
                }
            }
        }
        return ans;
    }


    public int numFriendRequests2(int[] ages) {
        int[] count = new int[121];
        for (int age:ages)count[age]++;
        int[] preSum = new int[121];
        for (int i=1;i<121;i++){
            preSum[i] = preSum[i-1] + count[i];
        }
        int ans = 0;
        for (int i=0;i<ages.length;i++){
            if (ages[i]<15) continue;
            int first = ages[i]/2+7;
            int last = ages[i];
            ans += preSum[last]-preSum[first];
            if (ages[i]>first && ages[i]<=last)ans-=1;
        }
        return ans;
    }


    @Test
    public void test(){

        Assert.assertTrue(Math.floor(1.23)==1);
        Assert.assertTrue(Math.floor(1.0)==1);
        Assert.assertTrue(numFriendRequests2(new int[]{20,30,100,110,120})==3);
    }
}
