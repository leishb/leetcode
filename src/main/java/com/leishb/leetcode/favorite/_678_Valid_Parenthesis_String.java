package com.leishb.leetcode.favorite;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by me on 2019/7/30.
 */
public class _678_Valid_Parenthesis_String {


    /**
     * Accepted
     * @param s
     * @return
     */
    public boolean checkValidString(String s) {
        int n = s.length();
        if (n==0){
            return true;
        }
        boolean[][] dp = new boolean[n+1][n+1];
        dp[0][0] = true;
        for (int i=1;i<=n;i++){
            if (s.charAt(i-1)=='*'){
                dp[i][i] = true;
            }
        }
        for (int i=1;i<=n;i++){
            for (int j=n-i;j>=1;j--){
                char left = s.charAt(j-1);
                char right = s.charAt(i+j-1);
                if (left==')' || right=='('){
                    dp[j][i+j] = false;
                    continue;
                }
                if (left=='(' && right==')'){
                    dp[j][i+j] =  i==1 || dp[j+1][i+j-1];
                }else if (left=='(' && right=='*'){
                    dp[j][i+j] =  i==1 || dp[j+1][i+j-1] || dp[j][i+j-1];
                }else if (left=='*' && right==')'){
                    dp[j][i+j] =  i==1 || dp[j+1][i+j-1] || dp[j+1][i+j];
                }else if (left=='*' && right=='*'){
                    dp[j][i+j] =  i==1 || dp[j+1][i+j-1] || dp[j][i+j-1] ||  dp[j+1][i+j];
                }else {
                    dp[j][i+j] = false;
                }
                if (dp[j][i+j]){
                    continue;
                }
                for (int k=j+1;k+1<=i+j;k++){
                    if (dp[j][k] && dp[k+1][i+j]){
                        dp[j][i+j] = true;
                        break;
                    }
                }
            }
        }
        return dp[1][n];
    }



    Map<String, Boolean> map = new HashMap<>();

    /**
     * Accepted
     * @param s
     * @return
     */
    public boolean checkValidString2(String s) {
        if (s.isEmpty()){
            return true;
        }
        if (s.length()==1){
            return s.equals("*");
        }
        if (map.containsKey(s)){
            return map.get(s);
        }
        boolean valid = false;
        if (s.charAt(0)=='('){
            if ( s.charAt(s.length()-1)==')') {
                valid = checkValidString2(s.substring(1, s.length() - 1));
            }else if (s.charAt(s.length()-1)=='*'){
                valid = checkValidString2(s.substring(1, s.length() - 1)) || checkValidString2(s.substring(0, s.length() - 1));
            }
        }else if (s.charAt(0) == '*'){
            if ( s.charAt(s.length()-1)==')') {
                valid = checkValidString2(s.substring(1, s.length() - 1)) || checkValidString2(s.substring(1));
            }else if (s.charAt(s.length()-1)=='*'){
                valid = checkValidString2(s.substring(1, s.length() - 1)) || checkValidString2(s.substring(1)) || checkValidString2(s.substring(0, s.length()-1)) ;
            }
        }else {
            valid = false;
        }
        if (!valid){
            for (int i=1;i<s.length();i++){
                if (checkValidString2(s.substring(0, i)) && checkValidString2(s.substring(i))){
                    valid = true;
                    break;
                }
            }
        }
        map.put(s, valid);
        return valid;
    }


    @Test
    public void test(){
        Assert.assertTrue(checkValidString("(*)"));
        Assert.assertTrue(checkValidString("(*))"));
        Assert.assertTrue(checkValidString("()()"));
        Assert.assertTrue(checkValidString("()*)"));
        Assert.assertTrue(checkValidString("(*()"));
        Assert.assertTrue(checkValidString("()*()"));
        Assert.assertFalse(checkValidString("*()(())*()(()()((()(()()*)(*(())((((((((()*)(()(*)"));

        Assert.assertTrue(checkValidString2("(*)"));
        Assert.assertTrue(checkValidString2("(*))"));
        Assert.assertTrue(checkValidString2("()()"));
        Assert.assertTrue(checkValidString2("()*)"));
        Assert.assertTrue(checkValidString2("(*()"));
        Assert.assertTrue(checkValidString2("()*()"));
        Assert.assertFalse(checkValidString2("(*(()))((())())*(**(()))((*)()(()))*(())()(())(()"));
        Assert.assertFalse(checkValidString2("*()(())*()(()()((()(()()*)(*(())((((((((()*)(()(*)"));
    }
}
