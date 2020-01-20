package com.leishb.leetcode.string;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by me on 2020/1/16.
 */
public class _917_Reverse_Only_Letters {


    /**
     * Accepted
     * @param S
     * @return
     */
    public String reverseOnlyLetters(String S) {
        Set<Character> set = new HashSet<>();
        for (char c = 'a', d='A';c<='z';c++, d++){
            set.add(c);
            set.add(d);
        }
        int i = 0, j = S.length()-1;
        char[] cs = S.toCharArray();
        while (i<j){
            if (!set.contains(cs[i])){
                i++;
                continue;
            }
            if (!set.contains(cs[j])){
                j--;
                continue;
            }
            char temp = cs[i];
            cs[i] = cs[j];
            cs[j] = temp;
            i++;j--;
        }
        return new String(cs);
    }
}
