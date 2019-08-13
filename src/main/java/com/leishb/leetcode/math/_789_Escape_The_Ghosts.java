package com.leishb.leetcode.math;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by me on 2019/8/13.
 */
public class _789_Escape_The_Ghosts {


    /**
     * Accepted
     * @param ghosts
     * @param target
     * @return
     */
    public boolean escapeGhosts(int[][] ghosts, int[] target) {
        int dist = Math.abs(target[0])+Math.abs(target[1]);
        for (int[] ghost : ghosts){
            if (Math.abs(ghost[0]-target[0])+Math.abs(ghost[1]-target[1])<=dist){
                return false;
            }
        }
        return true;
    }


    @Test
    public void test(){
        Assert.assertFalse(escapeGhosts(new int[][]{{1,9},{2,-5},{3,8},{9,8},{-1,3}}, new int[]{8,-10}));
    }
}
