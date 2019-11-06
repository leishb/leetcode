package com.leishb.leetcode.array;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by me on 2019/11/1.
 */
public class _254_Factor_Combinations {

    public List<List<Integer>> getFactors(int n) {
        if (n<=1) return new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        dfs(res, new ArrayList<>(), n, 2);
        return res;
    }



    private void dfs(List<List<Integer>> res, List<Integer> list , int n, int start){
        if (n==1){
            if (list.size()>1)res.add(list);
            return;
        }
        for (int i=start; i<=n ;i++){
            if (n%i!=0) continue;
            List<Integer> copy = new ArrayList<>(list);
            copy.add(i);
            dfs(res, copy, n/i, i);
        }
    }
}
