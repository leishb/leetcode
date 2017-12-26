package com.leishb.leetcode.tree;

import com.leishb.leetcode.tag.DivideAndConquer;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by me on 2017/12/21.
 */
@DivideAndConquer
public class DiffWaysToCompute {

    @Test
    public void test(){
        System.out.println(diffWaysToComputeWithMemo("2*3-4*5", new HashMap<>()));
    }


    /**
     * Accepted
     * @param input
     * @return
     */
    public List<Integer> diffWaysToCompute(String input) {
        List<Integer> ans = new ArrayList<>();
        if (!input.contains("+") && !input.contains("-") && !input.contains("*")){
            ans.add(Integer.parseInt(input));
            return ans;
        }
        char[] cs = input.toCharArray();
        StringBuffer left = new StringBuffer();
        for (int i=0;i<cs.length; i++){
            char c = cs[i];
            if (c == '+'){
                List<Integer> leftList = diffWaysToCompute(new String(left));
                List<Integer> rightList = diffWaysToCompute(input.substring(i+1));
                for (Integer ll : leftList){
                    for (Integer rr : rightList){
                        ans.add(ll + rr);
                    }
                }
            }else if (c == '-'){
                List<Integer> leftList = diffWaysToCompute(new String(left));
                List<Integer> rightList = diffWaysToCompute(input.substring(i+1));
                for (Integer ll : leftList){
                    for (Integer rr : rightList){
                        ans.add(ll - rr);
                    }
                }
            }else if (c =='*'){
                List<Integer> leftList = diffWaysToCompute(new String(left));
                List<Integer> rightList = diffWaysToCompute(input.substring(i+1));
                for (Integer ll : leftList){
                    for (Integer rr : rightList){
                        ans.add(ll * rr);
                    }
                }
            }
            left.append(c);
        }
        return ans;
    }



    public List<Integer> diffWaysToComputeWithMemo(String input, Map<String, List<Integer>> memo) {
        if (memo.containsKey(input)){
            return memo.get(input);
        }
        List<Integer> ans = new ArrayList<>();
        if (!input.contains("+") && !input.contains("-") && !input.contains("*")){
            ans.add(Integer.parseInt(input));
            memo.put(input, ans);
            return ans;
        }
        char[] cs = input.toCharArray();
        StringBuffer left = new StringBuffer();
        for (int i=0;i<cs.length; i++){
            char c = cs[i];
            if (c == '+'){
                List<Integer> leftList = diffWaysToComputeWithMemo(new String(left), memo);
                List<Integer> rightList = diffWaysToComputeWithMemo(input.substring(i+1),  memo);
                for (Integer ll : leftList){
                    for (Integer rr : rightList){
                        ans.add(ll + rr);
                    }
                }
            }else if (c == '-'){
                List<Integer> leftList = diffWaysToComputeWithMemo(new String(left),  memo);
                List<Integer> rightList = diffWaysToComputeWithMemo(input.substring(i+1),  memo);
                for (Integer ll : leftList){
                    for (Integer rr : rightList){
                        ans.add(ll - rr);
                    }
                }
            }else if (c =='*'){
                List<Integer> leftList = diffWaysToComputeWithMemo(new String(left),  memo);
                List<Integer> rightList = diffWaysToComputeWithMemo(input.substring(i+1),  memo);
                for (Integer ll : leftList){
                    for (Integer rr : rightList){
                        ans.add(ll * rr);
                    }
                }
            }
            left.append(c);
        }
        memo.put(input, ans);
        return ans;
    }

}
