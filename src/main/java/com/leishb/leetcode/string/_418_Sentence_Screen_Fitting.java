package com.leishb.leetcode.string;

/**
 * Created by me on 2019/12/26.
 */
public class _418_Sentence_Screen_Fitting {


    /**
     * Accepted
     * @param sentence
     * @param rows
     * @param cols
     * @return
     */
    public int wordsTyping(String[] sentence, int rows, int cols) {
        String s = String.join(" ", sentence) + " ";
        int len = s.length();
        int[] map = new int[len];
        for (int i=1;i<len;i++){
            map[i] = s.charAt(i)==' ' ? 1 : map[i-1]-1;
        }
        int start = 0;
        for (int i=0;i<rows;i++){
            start += cols;
            start += map[start%len];
        }
        return start/len;
    }


    /**
     * Accepted
     * @param sentence
     * @param rows
     * @param cols
     * @return
     */
    public int wordsTyping2(String[] sentence, int rows, int cols) {
        String s = String.join(" ", sentence) + " ";
        int len = s.length();
        int start = 0;
        for (int i=0;i<rows;i++){
            start += cols;
            if (s.charAt(start%len)==' '){
                start++;
            }else {
                while (start>=0 && s.charAt(start%len)!=' '){
                    start--;
                }
                start++;
            }
        }
        return start/len;
    }
}
