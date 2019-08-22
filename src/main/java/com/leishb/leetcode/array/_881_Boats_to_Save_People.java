package com.leishb.leetcode.array;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.TreeMap;

/**
 * Created by me on 2019/8/22.
 */
public class _881_Boats_to_Save_People {


    /**
     * Accepted
     * @param people
     * @param limit
     * @return
     */
    public int numRescueBoats(int[] people, int limit) {
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        for (int p : people){
            treeMap.put(p, treeMap.getOrDefault(p, 0)+1);
        }
        int ans = 0;
        for (int i=0;i<people.length;i++){
            if (treeMap.containsKey(people[i])){
                ans+=1;
                treeMap.put(people[i], treeMap.get(people[i])-1);
                if (treeMap.get(people[i])==0) {
                    treeMap.remove(people[i]);
                }
                if (limit==people[i]){
                    continue;
                }
                int rest = limit - people[i];
                int second = 0;
                 if (!treeMap.containsKey(rest)){
                    Integer k = treeMap.lowerKey(rest);
                    if (k!=null){
                        treeMap.put(k, treeMap.get(k)-1);
                        second = k;
                    }
                }else {
                     treeMap.put(rest, treeMap.get(rest)-1);
                     second = rest;
                }
                if (second>0 && treeMap.get(second)==0){
                    treeMap.remove(second);
                }
            }
        }
        return ans;
    }


    public int numRescueBoats2(int[] people, int limit) {
        Arrays.sort(people);
        int i = 0;
        int j = people.length-1;
        int ans = 0;
        while (i<=j){
            if (people[i]+people[j]<=limit){
                i++;
                j--;
            }else {
                j--;
            }
            ans++;
        }
        return ans;
    }


    @Test
    public void test(){
        Assert.assertEquals(numRescueBoats2(new int[]{1,2}, 3), 1);
        Assert.assertEquals(numRescueBoats2(new int[]{3,5,3,4}, 5), 4);
        Assert.assertEquals(numRescueBoats2(new int[]{3,2,2,1}, 3), 3);
        Assert.assertEquals(numRescueBoats2(new int[]{1,6,7,11}, 12), 3);
        Assert.assertEquals(numRescueBoats2(new int[]{1,6,7,11}, 17), 2);
    }
}
