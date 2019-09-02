package com.leishb.leetcode.string;

import org.junit.Assert;
import org.junit.Test;

import java.util.Stack;

/**
 * Created by me on 2019/9/2.
 */
public class _921_Minimum_Add_to_Make_Parentheses_Valid {


    /**
     * Accepted
     * @param S
     * @return
     */
    public int minAddToMakeValid(String S) {
        Stack<Character> stack = new Stack<>();
        for (char c : S.toCharArray()){
            if (c=='('){
                stack.push(c);
            }else if (!stack.isEmpty() && stack.peek()=='('){
                stack.pop();
            }else {
                stack.push(c);
            }
        }
        return stack.size();
    }

    public int minAddToMakeValid2(String S) {
        int ans = 0, bal = 0;
        for (char c : S.toCharArray()){
            if (c=='('){
                bal+=1;
            }else {
                if (bal==0){
                    ans +=1;
                }else {
                    bal-=1;
                }
            }
        }
        return ans+bal;
    }

    @Test
    public void test(){
        Assert.assertTrue(minAddToMakeValid2("()))((")==4);
    }
}
