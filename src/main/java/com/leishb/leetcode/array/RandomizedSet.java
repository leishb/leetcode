package com.leishb.leetcode.array;

import org.junit.Test;

import java.util.*;

/**
 * Created by me on 2017/11/14.
 */
public class RandomizedSet {

    @Test
    public void test(){
        RandomizedSet randomizedSet = new RandomizedSet();
        for (int i=0;i<100;i++){
            randomizedSet.insert(i);
        }
        for (int i=0;i<10;i++){
            System.out.println(randomizedSet.getRandom());
        }
    }


    private Map<Integer, Integer> indexes;
    private List<Integer> list;
    private Random random;

    /** Initialize your data structure here. */
    public RandomizedSet() {
        indexes = new HashMap<>();
        list = new ArrayList<>();
        random = new Random();
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (indexes.containsKey(val)){
            return false;
        }else {
            list.add(val);
            indexes.put(val, list.size()-1);
            return true;
        }
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if (!indexes.containsKey(val)){
            return false;
        }
        int index = indexes.remove(val);
        if (index<list.size()-1){
            int temp = list.get(list.size()-1);
            list.set(list.size()-1, list.get(index));
            list.set(index, temp);
            indexes.put(temp, index);
        }
        indexes.remove(val);
        list.remove(list.size()-1);
        return true;
    }

    /** Get a random element from the set. */
    public int getRandom() {
        int r = random.nextInt(list.size());
        return list.get(r);
    }
}
