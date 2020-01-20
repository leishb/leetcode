package com.leishb.leetcode.design;

import java.util.*;

/**
 * Created by me on 2020/1/16.
 */
public class _381_Insert_Delete_GetRandom {

    class RandomizedCollection {


        Map<Integer, LinkedHashSet<Integer>> map;
        ArrayList<Integer> list;


        /** Initialize your data structure here. */
        public RandomizedCollection() {
            map = new HashMap<>();
            list = new ArrayList<>();
        }

        /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
        public boolean insert(int val) {
            boolean contained = map.containsKey(val) && map.get(val).size()>0;
            int size = list.size();
            map.putIfAbsent(val, new LinkedHashSet<>());
            map.get(val).add(size);
            list.add(val);
            return !contained;
        }

        /** Removes a value from the collection. Returns true if the collection contained the specified element. */
        public boolean remove(int val) {
            if (!map.containsKey(val) || map.get(val).size()==0){
                return false;
            }
            int idx = map.get(val).iterator().next();
            map.get(val).remove(idx);
            if (idx<list.size()-1){
                int lastVal = list.get(list.size()-1);
                map.get(lastVal).remove(list.size()-1);
                map.get(lastVal).add(idx);
                list.set(idx, lastVal);
            }
            list.remove(list.size()-1);
            return true;
        }

        /** Get a random element from the collection. */
        public int getRandom() {
            int size = list.size();
            int k = new Random().nextInt(size);
            return list.get(k);
        }
    }
}
