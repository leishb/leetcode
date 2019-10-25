package com.leishb.leetcode.stack;

import java.util.*;

/**
 * Created by me on 2019/10/23.
 */
public class _316_Remove_Duplicate_Letters {

    public String removeDuplicateLetters(String s) {
        if (s.length()==0) return "";
        int[] count = new int[26];
        for (char c : s.toCharArray()){
            count[c-'a'] +=1;
        }
        int pos = 0;
        for (int i=0;i<s.length();i++){
            if (s.charAt(i) < s.charAt(pos)) pos = i;
            count[s.charAt(i)-'a']--;
            if (count[s.charAt(i)-'a']==0){
                break;
            }
        }
        return s.charAt(pos) + removeDuplicateLetters(s.substring(pos+1).replaceAll(s.charAt(pos)+"", ""));
    }


    public String removeDuplicateLetters2(String s) {
        Stack<Character> stack = new Stack<>();
        Map<Character, Integer> last = new HashMap<>();
        Set<Character> seen = new HashSet<>();
        for (int i=0;i<s.length();i++){
            last.put(s.charAt(i), i);
        }
        for (int i=0;i<s.length();i++){
            char c = s.charAt(i);
            if (!seen.contains(c)){
                while (!stack.isEmpty() && stack.peek() > c && last.get(stack.peek()) > i){
                    seen.remove(stack.peek());
                    stack.pop();
                }
                stack.push(c);
                seen.add(c);
            }
        }
        String ans = "";
        while (!stack.isEmpty()){
            ans = stack.pop()+ans;
        }
        return ans;
    }
}
