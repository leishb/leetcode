package com.leishb.leetcode.bit;

import com.leishb.leetcode.tag.BitMap;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by me on 2019/6/21.
 */
public class MaxProductOfWordsLength {


    /**
     * Accepted
     * @param words
     * @return
     */
    public int maxProduct(String[] words) {
        Arrays.sort(words, (o1, o2) -> Integer.compare(o2.length(), o1.length()));
        int max = 0;
        int endIndex = words.length;
        Set<Character>[] sets = new Set[words.length];
        for (int i=0;i<words.length;i++){
            int j = i+1;
            while (j<endIndex){
                if (match(words,sets, i, j)){
                    endIndex = j;
                    max = Math.max(max, words[i].length() * words[j].length());
                    break;
                }else {
                    j++;
                }
            }
        }
        return max;
    }


    /**
     * Accepted
     * @param words
     * @return
     */
    @BitMap
    public int maxProduct2(String[] words) {
        Arrays.sort(words, (o1, o2) -> Integer.compare(o2.length(), o1.length()));
        int[] bitMap = new int[words.length];
        for(int i=0; i<words.length; i++) {
            for(int j=0; j<words[i].length(); j++) {
                bitMap[i] |= 1 << (words[i].charAt(j) - 'a');
            }
        }
        int max = 0;
        int endIndex = words.length;
        for (int i=0;i<words.length;i++){
            int j = i+1;
            while (j<endIndex){
                if ((bitMap[i] & bitMap[j]) == 0){
                    endIndex = j;
                    max = Math.max(max, words[i].length() * words[j].length());
                    break;
                }else {
                    j++;
                }
            }
        }
        return max;
    }





    private boolean match(String[] words,Set<Character>[] sets, int i ,int j){
        Set<Character> set = sets[i];
        if (set==null) {
            set = new HashSet<>();
            for (char c : words[i].toCharArray()) {
                set.add(c);
            }
            sets[i] =set;
        }
        for (char c : words[j].toCharArray()){
            if (set.contains(c)){
                return false;
            }
        }
        return true;
    }
}
