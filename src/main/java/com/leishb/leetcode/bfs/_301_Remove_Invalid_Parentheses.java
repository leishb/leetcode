package com.leishb.leetcode.bfs;

import org.junit.Test;

import java.util.*;

/**
 * Created by me on 2019/9/23.
 */
public class _301_Remove_Invalid_Parentheses {

    public List<String> removeInvalidParentheses(String s) {
        List<String> ans = new ArrayList<>();
        helper(ans, s, 0, 0, '(', ')');
        return ans;
    }


    private void helper(List<String> ans, String s, int iStart, int jStart, char open, char close){
        int numOpen=0, numClose=0;
        for (int i=iStart;i<s.length();i++){
            if (s.charAt(i)==open)numOpen++;
            if (s.charAt(i)==close)numClose++;
            if (numClose > numOpen){
                for (int j=jStart;j<=i;j++){
                    if (s.charAt(j)==close && (j==jStart || s.charAt(j-1)!=close)){
                        helper(ans, s.substring(0, j)+s.substring(j+1), i, j, open, close);
                    }
                }
                return;//Stop here. The recursive calls handle the rest of the string.
            }
        }
        String reverse = new StringBuffer(s).reverse().toString();
        if (open=='('){
            helper(ans, reverse, 0, 0, ')', '(');
        }else {
            ans.add(reverse);
        }
    }


    public List<String> removeInvalidParentheses2(String s) {
        List<String> ans = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        visited.add(s);
        queue.add(s);
        boolean found = false;
        while (!queue.isEmpty()){
            String cur = queue.poll();
            if (valid(cur)){
                ans.add(cur);
                found = true;
            }
            if (found){//current level is the terminate length
                continue;
            }

            for (int i=0;i<cur.length();i++){
                if (cur.charAt(i)!='(' && cur.charAt(i)!=')')continue;
                String next = cur.substring(0,i)+cur.substring(i+1);
                if (!visited.contains(next)){
                    visited.add(next);
                    queue.offer(next);
                }
            }
        }
        return ans;
    }


    private boolean valid(String s){
        int count = 0;
        for (char c : s.toCharArray()){
            if (c=='(') count++;
            if (c==')') count--;
            if (count<0)return false;
        }
        return count==0;
    }

    @Test
    public void test(){
        System.out.println(removeInvalidParentheses2("()"));
        System.out.println(removeInvalidParentheses2("(r(()()("));
        System.out.println(removeInvalidParentheses2("((()"));
        System.out.println(removeInvalidParentheses2("((()))())"));
    }
}
