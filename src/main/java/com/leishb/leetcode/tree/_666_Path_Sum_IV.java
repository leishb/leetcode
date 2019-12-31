package com.leishb.leetcode.tree;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by me on 2019/11/22.
 */
public class _666_Path_Sum_IV {


    /**
     * Accepted
     * @param nums
     * @return
     */
    public int pathSum(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i=nums.length-1;i>=0;i--){
            int level = nums[i]/100;
            int pos = (nums[i]%100)/10;
            if (level==4 || map.getOrDefault((level+1)*10 + 2*pos-1, 0) + map.getOrDefault((level+1)*10 + 2*pos, 0)==0){
                map.put(nums[i]/10, 1);
            }else {
                map.put(nums[i]/10, map.getOrDefault((level+1)*10 + 2*pos-1, 0) + map.getOrDefault((level+1)*10 + 2*pos, 0));
            }
        }
        int ans = 0;
        for (int i=0;i<nums.length;i++){
            ans += nums[i]%10 * map.get(nums[i]/10);
        }
        return ans;
    }


    int ans = 0;


    /**
     * Accepted
     * @param nums
     * @return
     */
    public int pathSum2(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums){
            map.put(num/10, num%10);
        }
        dfs(nums[0]/10, 0, map);
        return ans;
    }


    private void dfs(int node, int sum, Map<Integer, Integer> map){
        if (!map.containsKey(node)) return;
        sum += map.get(node);
        int level = node/10;
        int pos = node%10;
        int left = (level+1)*10 + 2*pos-1;
        int right = (level+1)*10 + 2*pos;
        if (!map.containsKey(left) && !map.containsKey(right)){
            ans += sum;
            return;
        }
        dfs(left, sum, map);
        dfs(right, sum, map);
    }
}
