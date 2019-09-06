package com.leishb.leetcode.array;

import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by me on 2019/9/4.
 */
public class _950_Reveal_Cards_In_Increasing_Order {

    /**
     * Accepted
     * Simulation
     * @param deck
     * @return
     */
    public int[] deckRevealedIncreasing(int[] deck) {
        Queue<Integer> queue = new LinkedList<>();
        for (int i=0;i<deck.length;i++){
            queue.offer(i);
        }
        Arrays.sort(deck);
        int i=0;
        int[] ans = new int[deck.length];
        while (i<deck.length){
            int index = queue.poll();
            ans[index] = deck[i];
            if (!queue.isEmpty()){
                queue.offer(queue.poll());
            }
            i++;
        }
        return ans;
    }

    @Test
    public void test(){
        deckRevealedIncreasing(new int[]{17,13,11,2,3,5,7});
    }
}
