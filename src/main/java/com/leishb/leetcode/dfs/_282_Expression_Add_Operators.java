package com.leishb.leetcode.dfs;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by me on 2019/9/23.
 */
public class _282_Expression_Add_Operators {

    public List<String> addOperators(String num, int target) {
        List<String> ans = new ArrayList<>();
        backtracking(num, "", ans, 0, target, 0, 0);
        return ans;
    }



    private void backtracking(String num, String s, List<String> ans , int index,  int target, long eval, long prev){
        if (index==num.length()){
            if (eval==target){
                ans.add(s);
            }
            return;
        }
        int end = num.charAt(index)=='0'?index+1:num.length();
        for (int i=index;i<end;i++){
            Long cur = Long.parseLong(num.substring(index, i+1));
            if (s.length()==0){
                backtracking(num, s+cur, ans, i+1, target, cur, cur);
            }else {
                backtracking(num, s+"+"+cur, ans, i+1, target, eval+cur, cur);
                backtracking(num, s+"-"+cur, ans, i+1, target, eval-cur, -cur);
                backtracking(num, s+"*"+cur, ans, i+1, target, eval-prev+prev*cur, prev*cur);
            }
        }
    }




    @Test
    public void test(){
        System.out.println(addOperators("123", 6));
    }
}
