package com.leishb.leetcode.design;

import java.util.TreeMap;

/**
 * Accepted
 * Created by me on 2019/11/6.
 */
public class _352_Data_Stream_as_Disjoint_Intervals {


    TreeMap<Integer, int[]> map;

    /** Initialize your data structure here. */
    public _352_Data_Stream_as_Disjoint_Intervals() {
        map = new TreeMap<>();
    }

    public void addNum(int val) {
        if (map.containsKey(val)) return;
        Integer l = map.lowerKey(val);
        Integer h = map.higherKey(val);
        if (l!=null && h !=null && map.get(l)[1]+1==val && map.get(h)[0]==val+1){
            map.get(l)[1] = map.get(h)[1];
            map.remove(h);
        }else if (l!=null && map.get(l)[1]+1>=val){
            map.get(l)[1] = Math.max(map.get(l)[1], val);
        }else if (h!=null && map.get(h)[0]-1==val){
            map.get(h)[0] -= 1;
            map.put(val, map.get(h));
            map.remove(h);
        }else {
            map.put(val, new int[]{val, val});
        }
    }

    public int[][] getIntervals() {
        return map.values().toArray(new int[map.size()][2]);
    }


    public static void main(String[] args){
        _352_Data_Stream_as_Disjoint_Intervals intervals = new _352_Data_Stream_as_Disjoint_Intervals();
        intervals.addNum(0);
        intervals.addNum(4);
        intervals.addNum(6);
        intervals.addNum(8);
        intervals.addNum(7);
        intervals.addNum(7);
    }
}
