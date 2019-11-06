package com.leishb.leetcode.design;

import java.util.ArrayList;
import java.util.List;

/**
 * Accepted
 * Created by me on 2019/11/6.
 */
public class _352_Data_Stream_as_Disjoint_Intervals_2 {


    List<int[]> list;

    /** Initialize your data structure here. */
    public _352_Data_Stream_as_Disjoint_Intervals_2() {
        list = new ArrayList<>();
    }

    public void addNum(int val) {
        if (list.size()==0){
            list.add(new int[]{val, val});
            return;
        }
        int higher = higher(list, val);
        int lower = lower(list, val);
        if ((higher!=-1 && list.get(higher)[0]==val) || (lower!=-1 && list.get(lower)[0]==val) || lower==higher){
            return;
        }
        if (higher!=-1 && list.get(higher)[0]==val+1 && lower!=-1 && list.get(lower)[1]+1==val){
            list.get(lower)[1] = list.get(higher)[1];
            list.remove(higher);
            return;
        }
        if (higher!=-1 && list.get(higher)[0]==val+1){
            list.get(higher)[0] -=1;
            return;
        }
        if (lower!=-1 && list.get(lower)[1]+1>=val){
            list.get(lower)[1]=Math.max(val, list.get(lower)[1]);
            return;
        }
        list.add(lower+1, new int[]{val, val});
    }

    public int[][] getIntervals() {
        return list.toArray(new int[list.size()][2]);
    }


    private int higher(List<int[]> list, int k){
        int ans = -1;
        int i = 0, j = list.size()-1;
        while (i<=j){
            int m = (i+j)/2;
            if (list.get(m)[0] ==k){
                return m;
            }else if (list.get(m)[0] > k){
                ans = m;
                j = m-1;
            }else {
                i = m+1;
            }
        }
        return ans;
    }


    private int lower(List<int[]> list, int k){
        int ans = -1;
        int i = 0, j = list.size()-1;
        while (i<=j){
            int m = (i+j)/2;
            if (list.get(m)[0] ==k){
                return m;
            }else if (list.get(m)[0] > k){
                j = m-1;
            }else {
                ans = m;
                i = m+1;
            }
        }
        return ans;
    }


    public static void main(String[] args){
        _352_Data_Stream_as_Disjoint_Intervals_2 intervals = new _352_Data_Stream_as_Disjoint_Intervals_2();
        intervals.addNum(0);
        intervals.addNum(4);
        intervals.addNum(6);
        intervals.addNum(8);
        intervals.addNum(7);
        intervals.addNum(7);
    }
}
