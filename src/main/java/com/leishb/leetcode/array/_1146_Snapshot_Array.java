package com.leishb.leetcode.array;

import java.util.TreeMap;

/**
 * Created by me on 2019/9/20.
 */
public class _1146_Snapshot_Array {


    TreeMap<Integer, Integer>[] maps;

    int snapId = 0;

    public _1146_Snapshot_Array(int length) {
        maps = new TreeMap[length];
        for (int i=0;i<length;i++){
            maps[i] = new TreeMap<>();
        }
    }

    public void set(int index, int val) {
        maps[index].put(snapId, val);
    }

    public int snap() {
        return snapId++;
    }

    public int get(int index, int snap_id) {
        Integer k = maps[index].floorKey(snap_id);
        return k==null?0:maps[index].get(k);
    }
}
