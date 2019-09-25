package com.leishb.leetcode.string;

/**
 * Created by me on 2019/9/23.
 */
public class _953_Verifying_an_Alien_Dictionary {

    public boolean isAlienSorted(String[] words, String order) {
        int[] count = new int[26];
        for (int i=0;i<26;i++){
            count[order.charAt(i)-'a'] = i;
        }
        for (int i=1;i<words.length;i++){
            if (!isLess(words[i-1], words[i], count)){
                return false;
            }
        }
        return true;
    }


    private boolean isLess(String s1, String s2, int[] count){
        int len = Math.min(s1.length(), s2.length());
        for (int i=0;i<len;i++){
            if (count[s1.charAt(i)-'a'] < count[s2.charAt(i)-'a']) return true;
            if (count[s1.charAt(i)-'a'] > count[s2.charAt(i)-'a']) return false;
        }
        return s1.length()<=s2.length();
    }
}
