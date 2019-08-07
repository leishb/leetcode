package com.leishb.leetcode.design;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by me on 2019/8/6.
 */
public class _731_My_Calendar_II_2 {

    List<int[]> calendar;
    List<int[]> overlaps;


    public _731_My_Calendar_II_2() {
        calendar = new ArrayList<>();
        overlaps = new ArrayList<>();
    }

    public boolean book(int start, int end) {
        for (int[] arr : overlaps){
            if (!(start>=arr[1] || end<=arr[0])){
                return false;
            }
        }
        for (int[] arr : calendar){
            if (!(start>=arr[1] || end<=arr[0])){
                overlaps.add(new int[]{Math.max(start, arr[0]), Math.min(end, arr[1])});
            }
        }
        calendar.add(new int[]{start, end});
        return true;
    }
}
