package com.leishb.leetcode.array;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by me on 2019/9/6.
 */
public class _974_Subarray_Sums_Divisible_by_K {

    public int subarraysDivByK(int[] A, int K) {
        int[] sums = new int[A.length+1];
        int ans = 0;
        for (int i=0;i<A.length;i++){
            sums[i+1] = sums[i]+A[i];
            for (int j=0;j<=i;j++){
                if ((sums[i+1]-sums[j])%K==0){
                    ans++;
                }
            }
        }
        return ans;
    }


    /**
     * Accepted
     * @param A
     * @param K
     * @return
     */
    public int subarraysDivByK2(int[] A, int K) {
        int[] sums = new int[A.length+1];
        int ans = 0;
        for (int i=0;i<A.length;i++){
            sums[i+1] = sums[i]+A[i];
        }
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int i=1;i<sums.length;i++){
            int mod = sums[i]%K;
            if (mod >0 ){
                ans += map.getOrDefault(mod, 0);
                ans += map.getOrDefault(mod-K, 0);
            }else {
                ans += map.getOrDefault(mod, 0);
                ans += map.getOrDefault(mod+K, 0);
            }
            map.put(mod, map.getOrDefault(mod,0)+1);
        }
        return ans;
    }

    public int subarraysDivByK3(int[] A, int K) {
        int[] map = new int[K];
        map[0] = 1;
        int ans = 0;
        int sum = 0;
        for (int num : A){
            sum+=num;
            sum %=K;
            if (sum<0) sum+=K;
            ans+=map[sum];
            map[sum]+=1;
        }
        return ans;
    }

    @Test
    public void test(){
        Assert.assertTrue(subarraysDivByK3(new int[]{4,5,0,-2,-3,1}, 5)==7);
        Assert.assertTrue(subarraysDivByK3(new int[]{-1, -2,-3}, 5)==1);
        Assert.assertTrue(subarraysDivByK3(new int[]{2, 2, 9}, 2)==3);
        Assert.assertTrue(subarraysDivByK3(new int[]{-1, 4}, 4)==1);
        Assert.assertTrue(subarraysDivByK3(new int[]{1, -4}, 4)==1);
        Assert.assertTrue(subarraysDivByK3(new int[]{2, -2, 2, -4}, 6)==2);
        Assert.assertTrue(subarraysDivByK3(new int[]{7,-5,5,-8,-6,6,-4,7,-8,-7}, 7)==11);
    }
}
