package com.leishb.leetcode.array;

import com.leishb.leetcode.tag.BinarySearch;
import org.junit.Test;

import java.util.Arrays;
import java.util.TreeMap;

/**
 * Created by me on 2019/7/4.
 */
@BinarySearch
public class _436_Find_Right_Interval {

    /**
     * Accepted
     * @param intervals
     * @return
     */
    public int[] findRightInterval(int[][] intervals) {
        int[][] vals = new int[intervals.length][3];
        for (int i=0;i<intervals.length;i++){
            vals[i] = new int[]{intervals[i][0],intervals[i][1], i};
        }
        Arrays.sort(vals, (o1, o2) -> {
            if (o1[0]==o2[0]){
                return Integer.compare(o1[2], o2[2]);
            }
            return Integer.compare(o1[0], o2[0]);
        });
        int[] ans = new int[vals.length];
        for (int i=0;i<vals.length;i++){
            int index = binarySearch(vals, i+1, vals[i][1]);
            ans[vals[i][2]] = index==-1?-1:vals[index][2];
        }
        return ans;
    }


    private int binarySearch(int[][] vals, int start , int x){
        if (start>=vals.length){
            return -1;
        }
        if (x>vals[vals.length-1][0]){
            return -1;
        }
        int low = start;
        int hi = vals.length;
        while (low<=hi){
            int mid = (low+hi)/2;
            if (vals[mid][0]==x){
                return mid;
            }
            if (vals[mid][0]>x){
                hi = mid-1;
            }else {
                low = mid+1;
            }
        }
        return low;
    }


    public int[] findRightInterval2(int[][] intervals) {
        int[] ans = new int[intervals.length];
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i=0;i<intervals.length;i++){
            map.put(intervals[i][0], i);
        }
        for (int i=0;i<ans.length;i++){
            Integer key = map.ceilingKey(intervals[i][1]);
            ans[i] = key==null?-1:map.get(key);
        }
        return ans;
    }

    @Test
    public void test(){

    }
}
