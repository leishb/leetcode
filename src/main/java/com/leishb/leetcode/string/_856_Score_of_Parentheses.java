package com.leishb.leetcode.string;

import org.junit.Assert;
import org.junit.Test;

import java.util.Stack;

/**
 * Created by me on 2019/8/28.
 */
public class _856_Score_of_Parentheses {


    /**
     * Accepted
     * @param S
     * @return
     */
    public int scoreOfParentheses(String S) {
        boolean[][] p = new boolean[S.length()][S.length()];
        for (int i=0;i<S.length()-1;i++){
            if (S.charAt(i)=='(' && S.charAt(i+1)==')'){
                p[i][i+1] = true;
            }
        }
        for (int i=3;i<S.length();i+=2){
            for (int j=0;i+j<S.length();j++){
                if (S.charAt(j)=='(' && S.charAt(i+j)==')'){
                    if (p[j+1][i+j-1]){
                        p[j][i+j] = true;
                    }else {
                        for (int k=j+1;k<i+j;k++){
                            if (p[j][k] && p[k+1][i+j]){
                                p[j][i+j] = true;
                                break;
                            }
                        }
                    }
                }
            }
        }
        return scores(p, S, 0, S.length()-1);
    }


    private int scores(boolean[][] p, String S, int start, int end){
        if (end-start==1 && S.charAt(start)=='(' && S.charAt(end) == ')'){
            return 1;
        }
        if (p[start+1][end-1]){
            return 2 * scores(p, S, start+1, end-1);
        }else {
            int k = start+1;
            while (k<=end){
                if (p[start][k]){
                    return scores(p, S, start, k) + scores(p, S, k+1, end);
                }
                k++;
            }
        }
        return 0;
    }

    /**
     * Accepted
     * @param S
     * @return
     */
    public int scoreOfParentheses2(String S) {
        return scores(S, 0, S.length()-1);
    }

    private int scores(String S, int i, int j){
        int ans = 0, bal = 0;
        for (int k=i;k<=j;k++){
            bal += S.charAt(k)=='('?1:-1;
            if (bal==0){
                if (k-i==1) ans +=1;
                else ans += 2* scores(S, i+1, k-1);
                i = k+1;
            }

        }
        return ans;
    }


    public int scoreOfParentheses3(String S) {
        Stack<Integer> stack = new Stack<>();
        int cur = 0;
        for (char c : S.toCharArray()){
            if (c=='('){
                stack.push(cur);
                cur = 0;
            }else {
                cur = stack.pop() + Math.max(2*cur, 1);
            }
        }
        return cur;
    }

    @Test
    public void test(){
        Assert.assertTrue(scoreOfParentheses3("(()(()))")==6);
        Assert.assertTrue(scoreOfParentheses3("(())")==2);
        Assert.assertTrue(scoreOfParentheses3("(())(())")==4);
        Assert.assertTrue(scoreOfParentheses3("()(()())")==5);
    }
}
