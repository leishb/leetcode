package com.leishb.leetcode.array;

import java.util.*;

/**
 * Created by me on 2019/12/4.
 */
public class _1215_Stepping_Numbers {


    /**
     * Accepted
     * @param low
     * @param high
     * @return
     */
    public List<Integer> countSteppingNumbers(int low, int high) {
        List<Integer> ans = new ArrayList<>();
        String s1 = String.valueOf(low);
        String s2 = String.valueOf(high);
        for (int i=s1.length();i<=s2.length();i++){
            backtracking(ans, "", i, low, high);
        }
        return ans;
    }


    private void backtracking(List<Integer> ans,  String s, int len,  int low, int high){
        long num = s.length()==0?0:Long.parseLong(s);
        if (s.length()==len){
            if (num >= low && num <= high){
                ans.add((int) num);
            }
            return;
        }
        if (s.length()==0){
            for (int i=len==1?0:1;i<=9;i++){
                backtracking(ans, s + i,len,  low, high);
            }
        }else {
            int last = s.charAt(s.length()-1)-'0';
            if(last==9){
                backtracking(ans, s + 8, len,  low, high);
            }else if (last==0){
                backtracking(ans, s + 1 , len,  low, high);
            }else {
                backtracking(ans, s + (last-1) , len,  low, high);
                backtracking(ans, s + (last+1) , len,  low, high);
            }
        }
    }


    /**
     * Accepted
     * @param low
     * @param high
     * @return
     */
    public List<Integer> countSteppingNumbers2(int low, int high) {
        List<Integer> ans = new ArrayList<>();
        if (low==0) ans.add(0);
        for (int i=1;i<=9;i++){
            backtracking(i, ans, low, high);
        }
        Collections.sort(ans);
        return ans;
    }


    private void backtracking(long cur, List<Integer> ans, int low, int high){
        if (cur > high) return;
        if (cur >=low) ans.add((int) cur);
        int last = (int) (cur%10);
        if (last <9) backtracking(cur * 10 + (last+1), ans, low, high);
        if (last >0) backtracking(cur * 10 + (last-1), ans, low, high);
    }


    /**
     * Accepted
     * @param low
     * @param high
     * @return
     */
    public List<Integer> countSteppingNumbers3(int low, int high) {
        List<Integer> ans = new ArrayList<>();
        if (low==0) ans.add(0);
        Queue<Long> queue = new LinkedList<>();
        for (long i=1;i<=9;i++){
            queue.offer(i);
        }
        while (!queue.isEmpty()){
            long cur = queue.poll();
            if (cur > high){
                continue;
            }
            int last = (int)(cur%10);
            if (last>0){
                queue.offer(cur*10 + last-1);
            }
            if (last<9){
                queue.offer(cur*10 + last+1);
            }
            if (cur>=low){
                ans.add((int) cur);
            }
        }
        return ans;
    }
}
