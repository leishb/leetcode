package com.leishb.leetcode.string;

/**
 * Created by me on 2020/1/3.
 */
public class _809_Expressive_Words {


    /**
     * Accepted
     * @param S
     * @param words
     * @return
     */
    public int expressiveWords(String S, String[] words) {
        int count = 0;
        for (String word : words){
            if (stretchy(S, word)) count++;
        }
        return count;
    }


    private boolean stretchy(String S, String word){
        int i = 0, j = 0;
        while (i<S.length() && j<word.length()){
            if (S.charAt(i) != word.charAt(j)) return false;
            int len1 = getRepeatedLength(S, i);
            int len2 = getRepeatedLength(word, j);

            if ((len1<3 && len1!=len2) || (len1>=3 && len1<len2)){
                return false;
            }
            i+=len1;
            j+=len2;
        }
        return i==S.length() && j== word.length();
    }


    private int getRepeatedLength(String str, int i){
        int count = 1;
        while (i + 1<str.length()) {
            if (str.charAt(i+1)==str.charAt(i)){
                count++;
                i++;
            }else {
                break;
            }
        }
        return count;
    }
}
