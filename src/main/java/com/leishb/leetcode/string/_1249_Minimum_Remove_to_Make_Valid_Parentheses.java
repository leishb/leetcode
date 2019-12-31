package com.leishb.leetcode.string;

/**
 * Created by me on 2019/12/9.
 */
public class _1249_Minimum_Remove_to_Make_Valid_Parentheses {


    /**
     * Accepted
     * @param s
     * @return
     */
    public String minRemoveToMakeValid(String s) {
        StringBuffer sb = new StringBuffer();
        int count = 0;
        for (char c : s.toCharArray()){
            if (c=='('){
                count++;
            }
            if (c==')'){
                count--;
            }
            if (count>=0){
                sb.append(c);
            }else {
                count=0;
            }
        }
        count = 0;
        StringBuffer ans = new StringBuffer();
        for (char c : sb.reverse().toString().toCharArray()){
            if (c==')'){
                count++;
            }
            if (c=='('){
                count--;
            }
            if (count>=0){
                ans.append(c);
            }else {
                count = 0;
            }
        }
        return ans.reverse().toString();
    }
}
