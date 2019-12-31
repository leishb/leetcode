package com.leishb.leetcode.dp;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by me on 2019/12/9.
 */
public class _1187_Make_Array_Strictly_Increasing {


    /**
     * Accepted
     * @param arr1
     * @param arr2
     * @return
     */
    public int makeArrayIncreasing(int[] arr1, int[] arr2) {
        TreeMap<Integer, Integer> map =  new TreeMap<>();
        for (int num : arr2){
            map.put(num, num);
        }
        int ans = dfs(arr1, Integer.MIN_VALUE, 0, map, new HashMap<>());
        return ans==Integer.MAX_VALUE?-1:ans;
    }


    private int dfs(int[] arr1, int prev, int index, TreeMap<Integer, Integer> map, Map<String, Integer> memo){
        if (index==arr1.length){
            return 0;
        }
        String key =  prev +","+index;
        if (memo.containsKey(key)) return memo.get(key);
        Integer next = map.higherKey(prev);
        int replace = Integer.MAX_VALUE;
        int noReplace = Integer.MAX_VALUE;
        if (next!=null){
            replace = dfs(arr1, next, index+1, map, memo);
        }
        if (arr1[index]>prev){
            noReplace = dfs(arr1, arr1[index], index+1, map, memo);
        }
        if (replace!=Integer.MAX_VALUE) {
            replace +=1;
        }
        int ans = Math.min(replace, noReplace);
        memo.put(key, ans);
        return ans;
    }
}
