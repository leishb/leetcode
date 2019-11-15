package com.leishb.leetcode.string;

import org.junit.Test;

import java.util.Stack;

/**
 * Created by me on 2019/11/14.
 */
public class _1209_Remove_All_Adjacent_Duplicates_in_String_II {


    /**
     * Accepted
     * @param s
     * @param k
     * @return
     */
    public String removeDuplicates(String s, int k) {
        if (k==1) return "";
        StringBuffer sb = new StringBuffer();
        int[] counts = new int[s.length()];
        for (int i=0;i<s.length();i++){
            if (sb.length()!=0 && s.charAt(i)==sb.charAt(sb.length()-1)){
                counts[sb.length()] = counts[sb.length()-1]+1;
            }else {
                counts[sb.length()] = 1;
            }
            sb.append(s.charAt(i));
            if (counts[sb.length()-1]==k){
                sb = new StringBuffer(sb.substring(0, sb.length()-k));
            }
        }
        return sb.toString();
    }


    /**
     * Accepted
     * @param s
     * @param k
     * @return
     */
    public String removeDuplicates2(String s, int k) {
        if (k==1) return "";
        Stack<int[]> stack = new Stack<>();
        for (int i = 0;i<s.length();i++){
            int j = s.charAt(i);
            if (!stack.isEmpty() && j == stack.peek()[0]){
                stack.push(new int[]{j, stack.peek()[1]+1});
            }else {
                stack.push(new int[]{j, 1});
            }
            if (stack.peek()[1]==k){
                int n = 0;
                while (n<k){
                    stack.pop();
                    n++;
                }
            }
        }
        StringBuffer sb = new StringBuffer();
        while (!stack.isEmpty()){
            sb.append((char)stack.pop()[0]);
        }
        return sb.reverse().toString();
    }


    /**
     * Accepted
     * @param s
     * @param k
     * @return
     */
    public String removeDuplicates3(String s, int k) {
        if (k == 1) return "";
        StringBuffer sb = new StringBuffer();
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (sb.length()!=0 && s.charAt(i)==sb.charAt(sb.length()-1)){
                stack.push(stack.pop()+1);
            }else {
                stack.push(1);
            }
            sb.append(s.charAt(i));
            if (stack.peek()==k){
                sb.delete(sb.length()-k, sb.length());
                stack.pop();
            }
        }
        return sb.toString();
    }

    public String removeDuplicates(String S) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0;i<S.length();i++){
            if (sb.length()!=0 && S.charAt(i)==sb.charAt(sb.length()-1)){
                sb = new StringBuffer(sb.substring(0, sb.length()-1));
            }else {
                sb.append(S.charAt(i));
            }
        }
        return sb.toString();
    }
    @Test
    public void test(){
        System.out.println(removeDuplicates3("deeedbbcccbdaa",3));
    }
}
