package com.leishb.leetcode.array;

import java.util.Arrays;

/**
 * Created by me on 2019/7/5.
 */
public class _452_Minimum_Number_of_Arrows_to_Burst_Balloons {

    /**
     * Accepted
     * @param points
     * @return
     */
    public int findMinArrowShots(int[][] points) {
        if(points.length==0){
            return 0;
        }
        Arrays.sort(points, (o1, o2)->Integer.compare(o1[0], o2[0]));
        int minEnd = points[0][1];
        int count = 1;
        for (int i=1;i<points.length;i++){
            if (points[i][0]<=minEnd){
                minEnd = Math.min(minEnd, points[i][1]);
            }else {
                count++;
                minEnd = points[i][1];
            }
        }
        return count;
    }
}
