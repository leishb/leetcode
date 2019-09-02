package com.leishb.leetcode.design;

import java.util.*;

/**
 * Created by me on 2019/8/28.
 */
public class _855_Exam_Room {


    int N;

    Queue<Interval> queue;

    class Interval {
        int x;
        int y;
        int dist;
        Interval(int x, int y){
            this.x = x;
            this.y = y;
            if (x==-1){
                this.dist = y;
            }else if (y==N){
                this.dist = N-1-x;
            }else {
                this.dist = Math.abs(x-y)/2;
            }
        }
    }



    public _855_Exam_Room(int N) {
        queue = new PriorityQueue<>((t1, t2) -> {
            if (t1.dist == t2.dist) {
                return t1.x - t2.x;
            }
            return t2.dist - t1.dist;
        });
        this.N = N;
        queue.offer(new Interval(-1,N));
    }

    public int seat() {
        Interval interval = queue.poll();
        int seat = 0;
        if (interval.x==-1){
            seat = 0;
        }else if (interval.y==N){
            seat = N-1;
        }else {
            seat = (interval.x+interval.y)/2;
        }
        queue.offer(new Interval(interval.x, seat));
        queue.offer(new Interval(seat, interval.y));
        return seat;
    }


    public void leave(int p) {
        List<Interval> list = new ArrayList<>(queue);
        Interval head =null;
        Interval tail =null;
        for (Interval interval : list){
            if (interval.x==p){
                tail = interval;
            }else if (interval.y==p){
                head = interval;
            }
            if (head!=null && tail!=null){
                break;
            }
        }
        queue.remove(head);
        queue.remove(tail);
        queue.offer(new Interval(head.x, tail.y));
    }

    public static void main(String[] args){
        _855_Exam_Room room = new _855_Exam_Room(10);
        room.seat();
        room.seat();
        room.seat();
        room.leave(4);
        room.seat();
    }
}
