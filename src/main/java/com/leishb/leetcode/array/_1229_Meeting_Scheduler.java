package com.leishb.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by me on 2019/11/6.
 */
public class _1229_Meeting_Scheduler {


    /**
     * Accepted
     * @param slots1
     * @param slots2
     * @param duration
     * @return
     */
    public List<Integer> minAvailableDuration(int[][] slots1, int[][] slots2, int duration) {
        Arrays.sort(slots1, (s1, s2)->s1[0]==s2[0]?s1[1]-s2[1]:s1[0]-s2[0]);
        Arrays.sort(slots2, (s1, s2)->s1[0]==s2[0]?s1[1]-s2[1]:s1[0]-s2[0]);
        int i = 0, j = 0;
        while (i<slots1.length && j < slots2.length){
            if (slots1[i][1] <= slots2[j][0]){
                i++;
            }else if (slots2[j][1] <= slots1[i][0]){
                j++;
            }else {
                int start = Math.max(slots1[i][0], slots2[j][0]);
                int end = Math.min(slots1[i][1], slots2[j][1]);
                if (end-start>=duration){
                    return Arrays.asList(start, start+duration);
                }
                if (slots1[i][1]<=slots2[j][1]){
                    i++;
                }else {
                    j++;
                }
            }
        }
        return new ArrayList<>();
    }
}
