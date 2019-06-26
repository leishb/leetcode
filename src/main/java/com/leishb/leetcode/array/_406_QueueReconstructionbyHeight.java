package com.leishb.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by me on 2019/6/25.
 */
public class _406_QueueReconstructionbyHeight {

    /**
     * Accepted
     * @param people
     * @return
     */
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, (o1, o2) -> {
            if (o1[0]!=o2[0]){
                return Integer.compare(o2[0], o1[0]);
            }else {
                return Integer.compare(o1[1], o2[1]);
            }
        });
        List<int[]> list = new ArrayList<>();
        for (int[] pl : people){
            list.add(pl[1], pl);
        }
        return list.toArray(new int[list.size()][]);
    }
}
