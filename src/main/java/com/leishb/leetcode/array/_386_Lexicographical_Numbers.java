package com.leishb.leetcode.array;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by me on 2019/6/27.
 */
public class _386_Lexicographical_Numbers {


    /**
     * Accepted
     * @param n
     * @return
     */
    public List<Integer> lexicalOrder(int n) {
        String[] arr  = new String[n];
        for(int i=0;i<n;i++){
            arr[i] = (i+1) + "";
        }
        Arrays.sort(arr);
        List<Integer> ans = new ArrayList();
        for(int i=0;i<n;i++){
            ans.add(Integer.parseInt(arr[i]));
        }
        return ans;
    }


    /**
     * Accepted
     * @param n
     * @return
     */
    public List<Integer> lexicalOrder2(int n) {
        List<Integer> result = new ArrayList<>();
        for (int i=1;i<=9;i++){
            dfs(i, n, result);
        }
        return result;
    }

    private void dfs(int cur, int n, List<Integer> result){
        if(cur > n){
            return;
        }
        result.add(cur);
        for (int i=0;i<=9;i++){
            if (cur * 10 + i >n){
                return;
            }
            dfs(cur * 10 + i, n, result);
        }
    }


    @Test
    public void test(){

    }
}
