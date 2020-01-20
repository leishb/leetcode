package com.leishb.leetcode.design;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by me on 2020/1/16.
 */
public class _380_Insert_Delete_GetRandom {

    class RandomizedSet {

        Map<Integer, Integer> map;
        Map<Integer, Integer> idMap;

        /** Initialize your data structure here. */
        public RandomizedSet() {
            map = new HashMap<>();
            idMap = new HashMap<>();
        }

        /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
        public boolean insert(int val) {
            if (map.containsKey(val))return false;
            int size = map.size();
            map.put(val, size);
            idMap.put(size,val);
            return true;
        }

        /** Removes a value from the set. Returns true if the set contained the specified element. */
        public boolean remove(int val) {
            if (!map.containsKey(val)) return false;
            int size = map.size();
            int id = map.get(val);
            int lastVal = idMap.get(size-1);
            idMap.put(id, lastVal);
            map.put(lastVal, id);
            map.remove(val);
            idMap.remove(size-1);
            return true;
        }

        /** Get a random element from the set. */
        public int getRandom() {
            int size = map.size();
            int k = new Random().nextInt(size);
            return idMap.get(k);
        }
    }
}
