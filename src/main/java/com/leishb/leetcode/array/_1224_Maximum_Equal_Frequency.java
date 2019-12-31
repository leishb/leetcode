package com.leishb.leetcode.array;

import java.util.*;

/**
 * Created by me on 2019/12/10.
 */
public class _1224_Maximum_Equal_Frequency {


    /**
     * Accepted
     * @param nums
     * @return
     */
    public int maxEqualFreq(int[] nums) {
        Map<Integer, Integer> freq = new HashMap<>();
        Map<Integer, Set<Integer>> map = new HashMap<>();
        int ans = 0;
        for (int i=0;i<nums.length;i++){
            freq.put(nums[i], freq.getOrDefault(nums[i], 0)+1);
            int f = freq.get(nums[i]);
            map.getOrDefault(f-1, new HashSet<>()).remove(nums[i]);
            if (map.getOrDefault(f-1, new HashSet<>()).size()==0){
                map.remove(f-1);
            }
            map.putIfAbsent(f, new HashSet<>());
            map.get(f).add(nums[i]);
            if (map.size()==1 && (map.get(map.keySet().iterator().next()).size()==1
                    || map.keySet().iterator().next()==1)){
                ans = i+1;
            }else if (map.size()==2){
                int min = Integer.MAX_VALUE;
                int max = Integer.MIN_VALUE;
                for (int k : map.keySet()){
                    min = Math.min(min, k);
                    max = Math.max(max ,k);
                }
                if (min==1 && map.get(min).size()==1){
                    ans  =  i+1;
                }else if (max-min==1 && map.get(max).size()==1){
                    ans = i+1;
                }
            }
        }
        return ans;
    }
}
