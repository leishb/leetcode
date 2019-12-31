package com.leishb.leetcode.dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by me on 2019/12/23.
 */
public class _679_24_Game {


    /**
     * Accepted
     * @param nums
     * @return
     */
    public boolean judgePoint24(int[] nums) {
        List<Double> list = new ArrayList<>();
        for (int num : nums){
            list.add((double)num);
        }
        return dfs(list);
    }


    private boolean dfs(List<Double> list){
        if (list.size()==1){
            return Math.abs(list.get(0)-24.0) < 0.001;
        }
        for (int i=0;i<list.size();i++){
            for (int j = i+1;j<list.size();j++){
                double a = list.get(i), b = list.get(j);
                for (double c : Arrays.asList(a+b, a-b, b-a, a*b, a/b, b/a)){
                    List<Double> nextRound = new ArrayList<>();
                    nextRound.add(c);
                    for (int k=0;k<list.size();k++){
                        if (k==i || k==j) continue;
                        nextRound.add(list.get(k));
                    }
                    if (dfs(nextRound)){
                        return true;
                    }
                }
            }
        }
        return false;
    }


}
