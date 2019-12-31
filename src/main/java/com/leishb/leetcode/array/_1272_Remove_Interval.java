package com.leishb.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by me on 2019/12/2.
 */
public class _1272_Remove_Interval {

    /**
     * Accepted
     * @param intervals
     * @param toBeRemoved
     * @return
     */
    public List<List<Integer>> removeInterval(int[][] intervals, int[] toBeRemoved) {
        List<List<Integer>> ans = new ArrayList();
        for (int[] it : intervals){
            if (it[0]>=toBeRemoved[1] || it[1]<=toBeRemoved[0]){
                ans.add(Arrays.asList(it[0], it[1]));
            }else {
                if (it[0] < toBeRemoved[0]){
                    ans.add(Arrays.asList(it[0], toBeRemoved[0]));
                }
                if (it[1] > toBeRemoved[1]){
                    ans.add(Arrays.asList(toBeRemoved[1], it[1]));
                }
            }
        }
        return ans;
    }
}
