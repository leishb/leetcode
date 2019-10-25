package com.leishb.leetcode.array;

import java.util.*;

/**
 * Created by me on 2019/10/22.
 */
public class _759_Employee_Free_Time {

    class Interval {
        public int start;
        public int end;

        public Interval() {}

        public Interval(int _start,int _end) {
            start = _start;
            end = _end;
        }
    }

    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        List<Interval> ans = new ArrayList<>();
        List<Interval> schedules = new ArrayList<>();
        for (List<Interval> list : schedule){
            schedules.addAll(list);
        }
        if (schedules.size()==0) return ans;
        Collections.sort(schedules, (s1, s2)->s1.start==s2.start?s1.end-s2.end:s1.start-s2.start);
        List<Interval> list = new ArrayList<>();
        list.add(schedules.get(0));
        for (int i=1;i<schedules.size();i++){
            Interval it = schedules.get(i);
            if (list.get(list.size()-1).end>=it.start){
                list.get(list.size()-1).end = Math.max(it.end, list.get(list.size()-1).end);
            }else {
                list.add(it);
            }
        }
        for (int i=1;i<list.size();i++){
            if (list.get(i).start > list.get(i-1).end){
                ans.add(new Interval(list.get(i-1).end, list.get(i).start));
            }
        }
        return ans;
    }


    public List<Interval> employeeFreeTime2(List<List<Interval>> schedule) {
        List<Interval> ans = new ArrayList<>();
        Queue<Interval> pq = new PriorityQueue<>((t1, t2)->t1.start-t2.start);
        schedule.forEach(s->pq.addAll(s));
        Interval temp = pq.poll();
        while (!pq.isEmpty()){
            if (pq.peek().start<=temp.end){
                temp.end = Math.max(pq.peek().end, temp.end);
                pq.poll();
            }else {
                ans.add(new Interval(temp.end, pq.peek().start));
                temp = pq.poll();
            }
        }
        return ans;
    }
}
