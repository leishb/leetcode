package com.leishb.leetcode.array;

import org.junit.Test;

import java.util.*;

/**
 * Created by me on 2019/9/15.
 */
public class _1054_Distant_Barcodes {


    public int[] rearrangeBarcodes(int[] barcodes) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : barcodes){
            freq.put(num, freq.getOrDefault(num, 0)+1);
        }
        int[] ans = new int[barcodes.length];
        int i=0;
        Queue<Map.Entry<Integer, Integer>> queue = new PriorityQueue<>((e1, e2)->e1.getValue()==e2.getValue()?e1.getKey()-e2.getKey():e2.getValue()-e1.getValue());
        queue.addAll(freq.entrySet());
        while (!queue.isEmpty()){
            int k = 2;
            List<Map.Entry<Integer, Integer>> tempList = new ArrayList<>();
            while (k-->0 && !queue.isEmpty()){
                Map.Entry<Integer, Integer> e = queue.poll();
                ans[i++] = e.getKey();
                e.setValue(e.getValue()-1);
                tempList.add(e);
            }
            for (Map.Entry<Integer, Integer> e : tempList){
                if (e.getValue()>0){
                    queue.offer(e);
                }
            }
        }
        return ans;
    }


    @Test
    public void test(){
        rearrangeBarcodes(new int[]{1,1,1,1,2,2,3,3});
    }
}
