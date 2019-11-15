package com.leishb.leetcode.array;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by me on 2019/11/8.
 */
public class _403_Frog_Jump {


    /**
     * Accepted
     * @param stones
     * @return
     */
    public boolean canCross(int[] stones) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i=0;i<stones.length;i++){
            map.put(stones[i], i);
        }
        return dfs(map, 0, 1, new HashMap<>());
    }



    private boolean dfs(Map<Integer, Integer> map, int pos, int units, Map<String, Boolean> memo){
        String key = pos + "-" + units;
        if (memo.containsKey(key)) return memo.get(key);
        if (units==0 || !map.containsKey(pos)) {
            memo.put(key, false);
            return false;
        }
        if (map.get(pos)==map.size()-1){
            memo.put(key, true);
            return true;
        }
        if (dfs(map, pos+units, units, memo) || dfs(map, pos+units, units+1, memo)|| dfs(map, pos+units, units-1, memo)) {
            memo.put(key, true);
            return true;
        }
        memo.put(key, false);
        return false;
    }
}
