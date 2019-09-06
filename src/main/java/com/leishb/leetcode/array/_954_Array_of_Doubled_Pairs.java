package com.leishb.leetcode.array;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * Created by me on 2019/9/5.
 */
public class _954_Array_of_Doubled_Pairs {

    public boolean canReorderDoubled(int[] A) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : A){
            freq.put(num, freq.getOrDefault(num, 0)+1);
        }
        return helper(freq, A.length);
    }




    private boolean helper(Map<Integer, Integer> freq, int count){
        Set<Integer> set = new HashSet<>(freq.keySet());
        for (int k : set){
            freq.put(k, freq.get(k)-1);
            if (freq.get(k)==0){
                freq.remove(k);
            }
            if (k%2==1){
                if (freq.containsKey(2*k)){
                    freq.put(2*k, freq.get(2*k)-1);
                    if (freq.get(2*k)==0){
                        freq.remove(2*k);
                    }
                    return helper(freq, count-2);
                }else {
                    return false;
                }
            }else {
                if (freq.containsKey(2*k)){
                    freq.put(2*k, freq.get(2*k)-1);
                    if (freq.get(2*k)==0){
                        freq.remove(2*k);
                    }
                    if (helper(freq,count-2)){
                        return true;
                    }
                    freq.put(2*k, freq.getOrDefault(2*k, 0)+1);
                }
                if (freq.containsKey(k/2)){
                    freq.put(k/2, freq.get(k/2)-1);
                    if (freq.get(k/2)==0){
                        freq.remove(k/2);
                    }
                    return helper(freq, count-2);
                }else {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean canReorderDoubled2(int[] A) {
        Map<Integer, Integer> odds = new HashMap<>();
        Map<Integer, Integer> evens = new HashMap<>();
        for (int num : A){
            if (num%2==1 || num%2==-1){
                odds.put(num, odds.getOrDefault(num, 0)+1);
            }else {
                evens.put(num, evens.getOrDefault(num, 0) + 1);
            }
        }
        for (int k : odds.keySet()){
            if (odds.get(k) > evens.getOrDefault(2*k, 0)){
                return false;
            }
            evens.put(2*k, evens.get(2*k)-odds.get(k));
            if (evens.get(2*k)==0) evens.remove(2*k);
        }
        return helper(evens);
    }

    private boolean helper(Map<Integer, Integer> evens){
        Set<Integer> set = new HashSet<>(evens.keySet());
        for (int k : set){
            evens.put(k, evens.get(k)-1);
            if (evens.get(k)==0){
                evens.remove(k);
            }
            if (evens.containsKey(2*k)){
                evens.put(2*k, evens.get(2*k)-1);
                if (evens.get(2*k)==0){
                    evens.remove(2*k);
                }
                if (helper(evens)){
                    return true;
                }
                evens.put(2*k, evens.getOrDefault(2*k, 0)+1);
            }
            if (evens.containsKey(k/2)){
                evens.put(k/2, evens.get(k/2)-1);
                if (evens.get(k/2)==0){
                    evens.remove(k/2);
                }
                return helper(evens);
            }else {
                return false;
            }
        }
        return true;
    }


    /**
     * Accepted
     * @param A
     * @return
     */
    public boolean canReorderDoubled3(int[] A) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int num : A){
            map.put(num, map.getOrDefault(num, 0)+1);
        }
        for (int k : map.keySet()){
            if (map.getOrDefault(k, 0)==0){
                continue;
            }
            if (k < 0 && k%2==-1){
                return false;
            }else if (k<0 && k%2==0){
                if (map.getOrDefault(k/2, 0) >= map.get(k)){
                    map.put(k/2, map.get(k/2)-map.get(k));
                }else {
                    return false;
                }
            }else if (k==0 && map.get(k)%2==1){
                return false;
            }else if (k > 0){
                if (map.getOrDefault(k*2, 0) >= map.get(k)){
                    map.put(k*2, map.get(k*2)-map.get(k));
                }else {
                    return false;
                }
            }
        }
        return true;
    }
    @Test
    public void test(){
        Assert.assertTrue(canReorderDoubled3(new int[]{4,-2,2,-4}));
        Assert.assertTrue(canReorderDoubled3(new int[]{1,2,2,4,8,16}));
        Assert.assertFalse(canReorderDoubled3(new int[]{1,2,2,4,8,9}));
        Assert.assertFalse(canReorderDoubled3(new int[]{3,1,3,6}));
        Assert.assertFalse(canReorderDoubled3(new int[]{6,-3,8,-4,3,0,4,-6,3,0,4,-6,6,-3,0,0,-2,4}));
    }
}
