package com.leishb.leetcode.design;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Created by me on 2019/12/27.
 */
public class _895_FreqStack {

    class FreqStack {

        Map<Integer, Integer> map;
        Map<Integer, Stack<Integer>> freq;
        int maxFreq = 0;

        public FreqStack() {
            map = new HashMap<>();
            freq = new HashMap<>();
        }

        public void push(int x) {
            map.put(x, map.getOrDefault(x, 0)+1);
            if (map.get(x) > maxFreq){
                maxFreq = map.get(x);
            }
            freq.putIfAbsent(map.get(x), new Stack<>());
            freq.get(map.get(x)).push(x);
        }

        public int pop() {
            int x = freq.get(maxFreq).pop();
            if (freq.get(maxFreq).size()==0){
                maxFreq--;
            }
            map.put(x, map.get(x)-1);
            return x;
        }
    }
}
