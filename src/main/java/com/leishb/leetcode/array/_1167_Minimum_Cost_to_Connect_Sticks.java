package com.leishb.leetcode.array;

import org.junit.Assert;
import org.junit.Test;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by me on 2019/10/24.
 */
public class _1167_Minimum_Cost_to_Connect_Sticks {


    /**
     * Accepted
     * @param sticks
     * @return
     */
    public int connectSticks(int[] sticks) {
        Queue<Integer> pq = new PriorityQueue<>();
        for (int num : sticks){
            pq.offer(num);
        }
        int res = 0;
        while (pq.size()>1){
            int len = pq.poll() + pq.poll();
            res += len;
            pq.offer(len);
        }
        return res;
    }



    @Test
    public void test(){
        Assert.assertTrue(connectSticks(new int[]{4,2,3,6})==29);
    }
}
