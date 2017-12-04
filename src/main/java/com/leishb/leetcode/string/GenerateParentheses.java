package com.leishb.leetcode.string;

import com.leishb.leetcode.tag.BackTracking;
import com.leishb.leetcode.tag.DynamicProgramming;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * Created by me on 2017/11/21.
 */
@BackTracking
@DynamicProgramming
public class GenerateParentheses {


    @Test
    public void test(){
        System.out.println(generateParenthesis(2));
        System.out.println(generateParenthesis(3));
        System.out.println(generateParenthesis(4));

        Assert.assertTrue(validParenthesis("(())"));


        Assert.assertTrue(longestValidParentheses(")()())")==4);
        Assert.assertTrue(longestValidParentheses("(()")==2);
        Assert.assertTrue(longestValidParentheses2(")(((((()())()()))()(()))(")==22);

        long start = System.currentTimeMillis();
        Assert.assertTrue(longestValidParentheses3(")()())")==4);
        Assert.assertTrue(longestValidParentheses3("(()")==2);
        Assert.assertTrue(longestValidParentheses3("()()")==4);
        Assert.assertTrue(longestValidParentheses3(")(((((()())()()))()(()))(")==22);
        Assert.assertTrue(longestValidParentheses3("(())(())")==8);

        System.out.println("cost : " + (System.currentTimeMillis()-start));
        for (String s : generateParenthesis(4)){
            Assert.assertTrue(longestValidParentheses3(s)==8);
        }

        long ss = System.currentTimeMillis();
        Assert.assertTrue(longestValidParentheses4(")()())")==4);
        Assert.assertTrue(longestValidParentheses4("(()")==2);
        Assert.assertTrue(longestValidParentheses4("()()")==4);
        Assert.assertTrue(longestValidParentheses4(")(((((()())()()))()(()))(")==22);
        Assert.assertTrue(longestValidParentheses4("(())(())")==8);
        Assert.assertTrue(longestValidParentheses4("())")==2);

        System.out.println("cost : " + (System.currentTimeMillis()-ss));
        for (String s : generateParenthesis(4)){
            Assert.assertTrue(longestValidParentheses4(s)==8);
        }
    }

    /**
     * Accepted
     * @param n
     * @return
     */
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        backtracking(n, 0,0, "", ans);
        return ans;
    }


    private void backtracking(int n,  int open, int close , String s, List<String> ans){
        if (s.length()==2 *n){
            ans.add(s);
            return;
        }
        if (open <n){
            backtracking(n, open+1, close, s + "(", ans);
        }
        if (close< open){
            backtracking(n, open, close+1, s + ")", ans);
        }

    }


    private void backtracking(int n,  int count, String s, List<String> ans){
        if (s.length()==2 *n){
            if (validParenthesis(s)){
                ans.add(s);
                return;
            }else {
                return;
            }
        }
        if (count == s.length()/2){
            s = s + "(";
            count++;
            backtracking(n, count, s, ans);
        }else if (s.length()== 2 *n -1){
            backtracking(n, count, s + ")", ans);
        } else if (count==s.length() && count==n){
            backtracking(n, count, s + ")", ans);
        } else{
            backtracking(n, count + 1, s + "(", ans);
            backtracking(n, count, s + ")", ans);
        }
    }


    private boolean validParenthesis(String s){
        Stack<Character> stack = new Stack<>();
        char[] cs = s.toCharArray();
        for (int i=0;i<cs.length;i++){
            if (cs[i]=='('){
                stack.push(cs[i]);
            }else {
                if (!stack.isEmpty() && stack.pop()!='('){
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }


    /**
     * Wrong
     * @param s
     * @return
     */
    public int longestValidParentheses(String s) {
        if (s.length()<2){
            return 0;
        }
        int low=0;
        int high=s.length()%2==0 ? s.length() : s.length()-1;
        while (low<=high){
            int mid = (low+high)/2;
            if (mid%2==0){
                boolean exist = false;
                for (int i=0;i<s.length();i++){
                    if (i+mid-1>=s.length()){
                        break;
                    }
                    if (valid(s, i, i+mid-1)){
                        exist = true;
                        break;
                    }
                }
                if (exist){
                    low = mid+1;
                }else {
                    high = mid-1;
                }
            }else {
                boolean exist = false;
                for (int i=0;i<s.length();i++){
                    if (i+mid>=s.length()){
                        break;
                    }
                    if (valid(s, i, i+mid)){
                        exist = true;
                        break;
                    }
                }
                if (exist){
                    low = mid+1;
                }else {
                    high = mid-1;
                }
            }
        }
        return high;
    }


    /**
     * TLE
     * @param s
     * @return
     */
    public int longestValidParentheses3(String s) {
        if (s.length()<2){
            return 0;
        }
        int max=0;
        boolean[][] dp = new boolean[s.length()][s.length()];
        for (int i=0;i<s.length()-1;i++){
            if (s.charAt(i+1)==')' && s.charAt(i)=='('){
                dp[i][i+1]=true;
                max = Math.max(max, 2);
            }
        }
        for (int j=4;j<=s.length();j=j+2){//gap is j
            for (int i=0;i+j-1<s.length();i++) {
                if (s.charAt(i+j-1)=='('){
                    continue;
                }
                if (dp[i + 1][i + j - 2] && s.charAt(i) == '(' && s.charAt(i + j - 1) == ')') {
                    dp[i][i + j - 1] = true;
                    max = Math.max(max, j);
                }else if (dp[i][i + j - 3] && s.charAt(i + j - 1) == ')' && s.charAt(i + j - 2) == '(') {
                    dp[i][i + j - 1] = true;
                    max = Math.max(max, j);
                }else if (s.charAt(i)=='(' && s.charAt(i+1)==')' && dp[i+2][i+j-1]){
                    dp[i][i + j - 1] = true;
                    max = Math.max(max, j);
                } else {
                    for (int k = 2; k <= j - 2; k += 2) {
                        if (dp[i][i + k - 1] && dp[i + k][i + j - 1]) {
                            dp[i][i + j - 1] = true;
                            max = Math.max(max, j);
                            break;
                        }
                    }
                }
            }
        }
        return max;
    }


    /**
     * Accepted
     * @param s
     * @return
     */
    public int longestValidParentheses4(String s) {
        int[] dp = new int[s.length()];
        int max = 0;
        for (int i=1;i<s.length();i++){
            if (s.charAt(i)==')'){
                if (s.charAt(i-1)=='('){
                    dp[i] = (i>=2? dp[i-2]:0) + 2;
                }else {
                    if ((i-dp[i-1]-1>= 0) && s.charAt(i-dp[i-1]-1)=='('){
                        dp[i] = ((i-dp[i-1]-2)>0 ? dp[i-dp[i-1]-2] :0) + (dp[i-1] +2);
                    }
                }
                max = Math.max(max, dp[i]);
            }
        }
        return max;
    }

    /**
     * TLE
     * @param s
     * @return
     */
    public int longestValidParentheses2(String s) {
        if (s.length() < 2) {
            return 0;
        }
        int max = 0;
        for (int i=0;i<s.length();i++){
            for (int j=i+1;j<s.length();j++){
                if (valid(s, i, j)){
                    max = Math.max(max, j-i+1);
                }
            }
        }
        return max;
    }

    private boolean valid(String s, int start, int end){
        if ((end-start+1)%2!=0){
            return false;
        }
        Stack<Character> stack = new Stack<>();
        char[] cs = s.toCharArray();
        for (int i=start;i<=end;i++){
            if (cs[i]=='('){
                stack.push(cs[i]);
            }else {
                if (stack.isEmpty()){
                    return false;
                }else if (stack.pop()!='('){
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
