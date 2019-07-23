package com.leishb.leetcode.array;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by me on 2019/7/19.
 */
public class _554_Brick_Wall {


    /**
     * Accepted
     * @param wall
     * @return
     */
    public int leastBricks(List<List<Integer>> wall) {
        Map<Integer, Integer> map = new HashMap<>();
        int total = 0;
        for (List<Integer> list : wall){
            int sum = 0;
            for (int i=0;i<list.size();i++){
                sum += list.get(i);
                map.put(sum, map.getOrDefault(sum, 0)+1);
            }
            total = sum;
        }
        int max = 0;
        for (Integer key : map.keySet()){
            if (key!=total){
                max = Math.max(max, map.get(key));
            }
        }
        return wall.size()-max;
    }
}
