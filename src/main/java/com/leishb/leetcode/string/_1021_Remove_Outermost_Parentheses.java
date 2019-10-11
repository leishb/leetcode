package com.leishb.leetcode.string;

/**
 * Created by me on 2019/10/10.
 */
public class _1021_Remove_Outermost_Parentheses {

    public String removeOuterParentheses(String S) {
        int count = 1;
        int i=1;
        StringBuffer sb = new StringBuffer();
        for (;i<S.length();i++){
            if (S.charAt(i)=='(')count++;
            else count--;
            if (count==0) break;
            sb.append(S.charAt(i));
        }
        if (i+1<S.length()){
            return sb.toString() + removeOuterParentheses(S.substring(i+1));
        }
        return sb.toString();
    }
}
