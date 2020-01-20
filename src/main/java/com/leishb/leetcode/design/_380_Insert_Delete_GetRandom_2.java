package com.leishb.leetcode.design;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by me on 2020/1/16.
 */
public class _380_Insert_Delete_GetRandom_2 {

    class RandomizedSet {

        Map<Integer, Integer> map;
        ArrayList<Integer> list;

        /** Initialize your data structure here. */
        public RandomizedSet() {
            map = new HashMap<>();
            list = new ArrayList<>();
        }

        /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
        public boolean insert(int val) {
            if (map.containsKey(val)) return false;
            int size = list.size();
            map.put(val, size);
            list.add(val);
            return true;
        }

        /** Removes a value from the set. Returns true if the set contained the specified element. */
        public boolean remove(int val) {
            if (!map.containsKey(val)){
                return false;
            }
            int idx = map.get(val);
            int last = list.get(list.size()-1);
            list.set(idx, last);
            list.remove(list.size()-1);
            map.put(last, idx);
            map.remove(val);
            return true;
        }

        /** Get a random element from the set. */
        public int getRandom() {
            int size = list.size();
            int k = new Random().nextInt(size);
            return list.get(k);
        }
    }
}
