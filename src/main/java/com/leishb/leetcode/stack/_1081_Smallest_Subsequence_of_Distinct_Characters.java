package com.leishb.leetcode.stack;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * Created by me on 2019/9/16.
 */
public class _1081_Smallest_Subsequence_of_Distinct_Characters {

    public String smallestSubsequence(String text) {
        int[] last = new int[26];
        for (int i=0;i<text.length();i++){
            last[text.charAt(i)-'a'] = i;
        }
        boolean[] seen = new boolean[26];
        Stack<Integer> stack = new Stack<>();
        for (int i=0;i<text.length();i++){
            int c = text.charAt(i)-'a';
            if (seen[c]) continue;
            while (!stack.isEmpty() && stack.peek() > c && i< last[stack.peek()]){
                seen[stack.pop()] = false;
            }
            stack.push(c);
            seen[c] = true;
        }
        String ans = "";
        while (!stack.isEmpty()){
            char c = (char)(stack.pop()+'a');
            ans = c + ans;
        }
        return ans;
    }

    @Test
    public void test(){
        Assert.assertTrue(smallestSubsequence("cdadabcc").equals("adbc"));
    }
}
