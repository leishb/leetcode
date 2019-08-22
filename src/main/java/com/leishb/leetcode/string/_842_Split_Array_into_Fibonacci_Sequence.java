package com.leishb.leetcode.string;

import com.leishb.leetcode.tag.BackTracking;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by me on 2019/8/19.
 */
@BackTracking
public class _842_Split_Array_into_Fibonacci_Sequence {


    private int MAX = (int) (Math.pow(2, 31)-1);

    /**
     * Accepted
     * @param S
     * @return
     */
    public List<Integer> splitIntoFibonacci(String S) {
        if (S==null || S.length()<=2) return new ArrayList<>();
        List<Integer> ans = new ArrayList<>();
        boolean canSplit = dfs(S, 0, ans);
        if (canSplit){
            return ans;
        }
        return new ArrayList<>();
    }


    private boolean dfs(String s, int index, List<Integer> ans){
        if (index==s.length()){
            return ans.size()>2;
        }
        boolean zeroLead = s.charAt(index)=='0';
        for (int i=index+1;i<= (zeroLead?index+1 :s.length());i++){
            long num = Long.parseLong(s.substring(index, i));
            if (num>MAX){
                return false;
            }
            if (ans.size()<=1 || ans.get(ans.size()-1)+ans.get(ans.size()-2)==num){
                ans.add((int) num);
                if (dfs(s, i, ans)){
                    return true;
                }
                ans.remove(ans.size()-1);
            }else if (ans.get(ans.size()-1)+ans.get(ans.size()-2) <num){
                return false;
            }
        }
        return false;
    }



    @Test
    public void test(){
        long start = System.currentTimeMillis();
        System.out.println(splitIntoFibonacci("123456579"));
        System.out.println(splitIntoFibonacci("11235813"));
        System.out.println(splitIntoFibonacci("1101111"));
        System.out.println(splitIntoFibonacci("0123"));
        System.out.println(splitIntoFibonacci("112358130"));
        System.out.println(System.currentTimeMillis()-start);
    }
}
