package com.leishb.leetcode.dp;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * Created by me on 2019/12/17.
 */
public class _818_Race_Car {


    /**
     * Accepted
     * @param target
     * @return
     */
    public int racecar(int target) {
        Queue<State> queue = new LinkedList<>();
        Set<String> set = new HashSet<>();
        State start = new State(0, 1);
        queue.offer(start);
        set.add(start.toString());
        int step = 0;
        while (!queue.isEmpty()){
            int size = queue.size();
            while (size-->0){
                State cur = queue.poll();
                if (cur.position == target) return step;
                int speed = cur.speed;
                //A
                State n1 = new State(cur.position+speed, speed*2);
                if (n1.position >0 && n1.position < (target<<1) &&  !set.contains(n1.toString())){
                    set.add(n1.toString());
                    queue.offer(n1);
                }
                //R
                State n2 = new State(cur.position, speed>0?-1:1);
                if (n2.position >0 && n2.position < (target<<1) && !set.contains(n2.toString())){
                    set.add(n2.toString());
                    queue.offer(n2);
                }
            }
            step++;
        }
        return 0;
    }

    class State{
        int position;
        int speed;

        State(int position, int speed){
            this.position = position;
            this.speed = speed;
        }

        public String toString(){
            return position + "," + speed;
        }
    }
}
