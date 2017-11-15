package com.leishb.leetcode.array;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * Created by me on 2017/11/14.
 */
public class RandomizedCollection {

    @Test
    public void test(){
        RandomizedCollection rc = new RandomizedCollection();
        Assert.assertTrue(rc.insert(0));
        Assert.assertTrue(rc.remove(0));
        Assert.assertTrue(rc.insert(-1));
        Assert.assertFalse(rc.remove(0));
        System.out.println(rc.getRandom());
        System.out.println(rc.getRandom());
        System.out.println(rc.getRandom());
        System.out.println(rc.getRandom());
    }



    private List<Integer> list;

    private Map<Integer, Set<Integer>> map;

    private Random random;



    /** Initialize your data structure here. */
    public RandomizedCollection() {
        list = new ArrayList<>();
        map = new HashMap<>();
        random = new Random();
    }

    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        Set<Integer> set = map.get(val);
        if (set==null){
            set = new HashSet<>();
            map.put(val, set);
        }
        if (set.size()==0){
            list.add(val);
            set.add(list.size()-1);
            return true;
        }else {
            list.add(val);
            set.add(list.size()-1);
            return false;
        }
    }

    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        Set<Integer> set = map.get(val);
        if (set==null || set.size()==0){
            return false;
        }
        int index = set.iterator().next();
        set.remove(index);
        if (index<list.size()-1){
            int lastOne = list.get(list.size()-1);
            list.set(index, lastOne);
            map.get(lastOne).remove(list.size()-1);
            map.get(lastOne).add(index);
        }
        list.remove(list.size()-1);
        return true;
    }

    /** Get a random element from the collection. */
    public int getRandom() {
        return list.get(random.nextInt(list.size()));
    }
}
