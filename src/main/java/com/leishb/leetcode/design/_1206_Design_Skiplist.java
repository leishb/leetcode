package com.leishb.leetcode.design;

import java.util.TreeMap;

/**
 * Created by me on 2019/12/18.
 */
public class _1206_Design_Skiplist {


    class Skiplist {

        TreeMap<Integer, Integer> map;

        public Skiplist() {
            map = new TreeMap<>();
        }

        public boolean search(int target) {
            return  map.containsKey(target);
        }

        public void add(int num) {
            map.put(num, map.getOrDefault(num, 0)+1);
        }

        public boolean erase(int num) {
            if (!map.containsKey(num)) return false;
            map.put(num, map.get(num)-1);
            if (map.get(num)==0) map.remove(num);
            return true;
        }
    }
}
