package com.leishb.leetcode.design;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by me on 2019/12/16.
 */
public class _1286_Iterator_for_Combination {



    class CombinationIterator {


        Queue<String> pq ;

        public CombinationIterator(String characters, int combinationLength) {
            pq = new PriorityQueue<>();
            backtracking(pq, characters, 0, combinationLength, "");
        }


        private void backtracking(Queue<String> queue, String characters, int start, int len,  String s){
            if (s.length()==len){
                queue.offer(s);
                return;
            }
            for (int i=start;i<characters.length();i++){
                backtracking(queue, characters, i+1, len, s + characters.charAt(i));
            }
        }

        public String next() {
            return pq.poll();
        }

        public boolean hasNext() {
            return !pq.isEmpty();
        }
    }
}
