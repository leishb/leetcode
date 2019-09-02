package com.leishb.leetcode.array;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by me on 2019/8/23.
 */
public class _846_Hand_of_Straights {

    /**
     * Accepted
     * @param hand
     * @param W
     * @return
     */
    public boolean isNStraightHand(int[] hand, int W) {
        if (hand.length%W!=0)return false;
        Map<Integer, Integer> freq = new HashMap<>();
        for (int h : hand){
            freq.put(h, freq.getOrDefault(h, 0)+1);
        }
        Arrays.sort(hand);
        for (int i=0;i<hand.length;i++){
            if (freq.containsKey(hand[i])){
                int cur = hand[i];
                for (int j=0;j<W;j++){
                    if (!freq.containsKey(cur)){
                        return false;
                    }
                    freq.put(cur, freq.get(cur)-1);
                    if (freq.get(cur)==0){
                        freq.remove(cur);
                    }
                    cur+=1;
                }
            }
        }
        return true;
    }


    public boolean isNStraightHand2(int[] hand, int W) {
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        for (int h : hand){
            treeMap.put(h, treeMap.getOrDefault(h, 0)+1);
        }
        for (int k : treeMap.keySet()){
            if (treeMap.get(k)>0){
                for (int i=1;i<=W-1;i++){
                    if (treeMap.getOrDefault(k+i, 0)<treeMap.get(k)) return false;
                    treeMap.put(k+i, treeMap.get(k+i)-treeMap.get(k));
                }
            }
        }
        return true;
    }


    @Test
    public void test(){
        Assert.assertTrue(isNStraightHand2(new int[]{1,2,3,6,2,3,4,7,8}, 3));
        Assert.assertFalse(isNStraightHand2(new int[]{1,2,3,6,2,3,4,7,9}, 3));
        Assert.assertTrue(isNStraightHand2(new int[]{1,2,3,6,2,3,4,5,7}, 3));
    }
}
