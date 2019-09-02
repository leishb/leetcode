package com.leishb.leetcode.array;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by me on 2019/9/2.
 */
public class _923_3Sum_With_Multiplicity {

    int MOD = 1_000_000_007;


    /**
     * Accepted
     * @param A
     * @param target
     * @return
     */
    public int threeSumMulti(int[] A, int target) {
        int ans = 0;
        Map<Integer, Long> freq = new HashMap<>();
        for (int i : A){
            freq.put(i, freq.getOrDefault(i, 0L)+1);
        }
        int[] nums = new int[freq.size()];
        int n=0;
        for (int k : freq.keySet()){
            nums[n++] = k;
        }
        Arrays.sort(nums);
        for (int i=0;i<nums.length;i++){
            int j = i;
            int k = nums.length-1;
            int rest = target-nums[i];
            while (j<=k){
                if (nums[j]+nums[k]>rest){
                    k--;
                }else if (nums[j]+nums[k]<rest){
                    j++;
                }else{
                    if (i!=j && j!=k){
                        ans = (int) ((ans + freq.get(nums[i]) * freq.get(nums[j]) * freq.get(nums[k])%MOD)%MOD);
                    }else if (i==j && j==k){
                        long f = freq.get(nums[i]);
                        if (f>=3) ans = (int) ((ans+f*(f-1)*(f-2)/6%MOD)%MOD);
                    } else if (i == j && freq.get(nums[i])>=2){
                        ans = (int) ((ans + freq.get(nums[i]) * (freq.get(nums[i])-1)/2 * freq.get(nums[k])%MOD)%MOD);
                    }else if (j==k && freq.get(nums[j])>=2){
                        ans = (int) ((ans + freq.get(nums[j]) * (freq.get(nums[j])-1)/2 * freq.get(nums[i])%MOD)%MOD);
                    }
                    j++;
                }
            }
        }
        return ans;
    }


    @Test
    public void test(){
        Assert.assertTrue(threeSumMulti(new int[]{1,1,2,2,3,3,4,4,5,5}, 8)==20);
        Assert.assertTrue(threeSumMulti(new int[]{1,1,2,2,2,2}, 5)==12);

        int[] A = new int[3000];
        threeSumMulti(A, 0);
    }
}
