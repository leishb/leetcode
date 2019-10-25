package com.leishb.leetcode.slidewindow;

import org.junit.Test;

import java.util.*;

/**
 * Created by me on 2019/10/21.
 */
public class _632_Smallest_Range_Covering_Elements_from_K_Lists {

    public int[] smallestRange(List<List<Integer>> nums) {
        int[] ans = new int[]{0, Integer.MAX_VALUE};
        Map<Integer, List<Integer>> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        for (int i = 0;i<nums.size();i++){
            for (Integer j : nums.get(i)){
                if (!map.containsKey(j)){
                    list.add(j);
                }
                map.putIfAbsent(j, new ArrayList<>());
                map.get(j).add(i);
            }
        }
        Collections.sort(list);
        int[] counts = new int[nums.size()];
        int j = 0;
        int i = 0;
        while (i<list.size()){
            addCounts(counts, map, list.get(i));
            while (valid(counts)){
                if (list.get(i) - list.get(j) < ans[1]-ans[0]){
                    ans = new int[]{list.get(j), list.get(i)};
                }
                removeCounts(counts, map, list.get(j));
                j++;
            }
            i++;
        }
        return ans;
    }

    private boolean valid(int[] counts){
        for (int i : counts){
            if (i<=0) return false;
        }
        return true;
    }

    private void addCounts(int[] counts, Map<Integer, List<Integer>> map, int num){
        for (int index : map.get(num)){
            counts[index]+=1;
        }
    }

    private void removeCounts(int[] counts, Map<Integer, List<Integer>> map, int num){
        for (int index : map.get(num)){
            counts[index] -=1;
        }
    }


    @Test
    public void test(){
        List<List<Integer>> list = new ArrayList<>();
        list.add(Arrays.asList(4, 10, 15,24,26));
        list.add(Arrays.asList(0,9,12,20));
        list.add(Arrays.asList(5,18,22,30));

        int[] ranget = smallestRange(list);
        System.out.println(ranget[0] + "-" + ranget[1]);
    }
}
