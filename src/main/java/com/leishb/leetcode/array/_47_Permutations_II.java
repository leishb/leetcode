package com.leishb.leetcode.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by me on 2019/10/21.
 */
public class _47_Permutations_II {

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums){
            map.put(num, map.getOrDefault(num, 0)+1);
        }
        backtracking(map, new ArrayList<>(), ans, nums.length);
        return ans;
    }


    private void backtracking(Map<Integer, Integer> map, List<Integer> list, List<List<Integer>> ans, int len){
        if (list.size()==len){
            ans.add(list);
            return;
        }
        for (int k : map.keySet()){
            if (map.get(k)==0) continue;
            map.put(k, map.get(k)-1);
            List<Integer> copy = new ArrayList<>(list);
            copy.add(k);
            backtracking(map, copy, ans, len);
            map.put(k, map.get(k)+1);
        }
    }
}
