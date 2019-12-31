package com.leishb.leetcode.string;

import java.util.Arrays;

/**
 * Created by me on 2019/12/31.
 */
public class _87_Scramble_String {


    /**
     * Accepted
     * @param s1
     * @param s2
     * @return
     */
    public boolean isScramble(String s1, String s2) {
        if (s1.length()!=s2.length())return false;
        if (s1.equals(s2)) return true;
        int[] counts = new int[26];
        int len = s1.length();
        for (int i=0;i<len;i++){
            counts[s1.charAt(i)-'a']++;
            counts[s2.charAt(i)-'a']--;
        }
        if (!Arrays.equals(counts, new int[26])){
            return false;
        }
        for (int i=1;i<len;i++){
            if (isScramble(s1.substring(0, i), s2.substring(0, i)) && isScramble(s1.substring(i), s2.substring(i))){
                return true;
            }
            if (isScramble(s1.substring(0, i), s2.substring(len-i)) && isScramble(s1.substring(i), s2.substring(0, len-i))){
                return true;
            }
        }
        return false;
    }
}
