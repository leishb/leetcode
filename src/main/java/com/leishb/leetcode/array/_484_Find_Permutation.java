package com.leishb.leetcode.array;

import java.util.Stack;
import java.util.TreeSet;

/**
 * Created by me on 2019/11/26.
 */
public class _484_Find_Permutation {


    /**
     * Accepted
     * @param s
     * @return
     */
    public int[] findPermutation(String s) {
        int[] ans = new int[s.length()+1];
        int[] dec = new int[s.length()];
        dec[s.length()-1] = s.charAt(s.length()-1)=='D'?1:0;
        for (int i=s.length()-2;i>=0;i--){
            if (s.charAt(i)=='D'){
                dec[i] = dec[i+1] + 1;
            }
        }
        TreeSet<Integer> ts = new TreeSet<>();
        for (int i=1;i<=s.length()+1;i++){
            ts.add(i);
        }
        for (int i=0;i<s.length();i++){
            if (dec[i]==0){
                ans[i] = ts.pollFirst();
            }else {
                ans[i] = ts.first()+dec[i];
                ts.remove(ans[i]);
            }
        }
        ans[s.length()] = ts.pollFirst();
        return ans;
    }


    /**
     * Accepted
     * @param s
     * @return
     */
    public int[] findPermutation2(String s) {
        int[] ans = new int[s.length() + 1];
        Stack<Integer> stack = new Stack<>();
        int j = 0;
        for (int i=1;i<=s.length();i++){
            stack.push(i);
            if (s.charAt(i-1)=='I'){
                while (!stack.isEmpty()){
                    ans[j++] = stack.pop();
                }
            }
        }
        stack.push(s.length()+1);
        while (!stack.isEmpty()){
            ans[j++] = stack.pop();
        }
        return ans;
    }
}
