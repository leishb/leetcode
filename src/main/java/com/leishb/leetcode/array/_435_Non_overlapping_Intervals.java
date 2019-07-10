package com.leishb.leetcode.array;

import com.leishb.leetcode.tag.Greedy;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by me on 2019/7/4.
 */
@Greedy
public class _435_Non_overlapping_Intervals {

    /**
     * Accepted
     * @param intervals
     * @return
     */
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length==0)return 0;
        int count = 0;
        Arrays.sort(intervals, (o1,o2)->Integer.compare(o1[0], o2[0]));
        int[] prev = intervals[0];
        for (int i=1;i<intervals.length;i++) {
            if (intervals[i][0]<prev[1]){
                count++;
                if (intervals[i][1]<prev[1]){
                    prev = intervals[i];
                }
            }else {
                prev = intervals[i];
            }
        }
        return count;
    }


    @Test
    public void test(){
        Assert.assertTrue(eraseOverlapIntervals(new int[][]{{0,2},{1,3},{2,4},{3,5},{4,6}})==2);
    }
}
