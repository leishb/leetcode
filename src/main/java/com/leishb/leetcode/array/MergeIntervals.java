package com.leishb.leetcode.array;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by me on 2017/10/31.
 */
public class MergeIntervals {

    class Interval{
        int start;
        int end;
        Interval() { start = 0; end = 0; }
        Interval(int s, int e) { start = s; end = e; }
    }


    /**
     * Accepted
     * @param intervals
     * @return
     */
    public List<Interval> merge(List<Interval> intervals) {
        intervals.sort((it1, it2)->it1.start-it2.start );
        List<Interval> ret = new ArrayList<>();
        if (intervals.size()==0){
            return ret;
        }
        Stack<Interval> stack = new Stack<>();
        stack.add(intervals.get(0));
        for (int i=1;i<intervals.size();i++){
            Interval itv = stack.peek();
            Interval it = intervals.get(i);
            if (itv.end>=it.start){
                itv.end = Math.max(itv.end, it.end);
            }else {
                stack.add(it);
            }
        }
        while (!stack.isEmpty()){
            ret.add(stack.pop());
        }
        ret.sort((it1, it2)->it1.start-it2.start );
        return ret;
    }

    /**
     * Accepted
     * @param intervals
     * @return
     */
    public List<Interval> merge2(List<Interval> intervals) {
        intervals.sort((it1, it2) -> it1.start - it2.start);
        List<Interval> ret = new ArrayList<>();
        for (int i=0;i<intervals.size();i++){
            if (i==0){
                ret.add(intervals.get(i));
            }else {
                Interval pre = ret.get(ret.size()-1);
                Interval cur = intervals.get(i);
                if (cur.start <= pre.end){
                    pre.end = Math.max(pre.end, cur.end);
                }else {
                    ret.add(cur);
                }
            }
        }
        return ret;
    }


    /**
     * Accepted
     * @param intervals
     * @param newInterval
     * @return
     */
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> ret = new ArrayList<>();
        int startIndex = -1;
        for (int i=0;i<intervals.size();i++) {
            Interval cur = intervals.get(i);
            if (cur.start >= newInterval.start) {
                startIndex = i;
                break;
            }
        }
        if (startIndex==-1){//注意在末尾的边界条件
            intervals.add(newInterval);
        }else {
            intervals.add(startIndex, newInterval);
        }
        for (int i=0;i<intervals.size();i++){
            Interval cur = intervals.get(i);
            if (i==0){
                ret.add(cur);
            }else  {
                Interval pre = ret.get(ret.size()-1);
                if (cur.start<=pre.end){
                    pre.end = Math.max(pre.end, cur.end);
                }else {
                    ret.add(cur);
                }
            }
        }
        return ret;
    }
}
