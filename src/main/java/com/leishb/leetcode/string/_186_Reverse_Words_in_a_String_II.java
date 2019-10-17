package com.leishb.leetcode.string;

/**
 * Created by me on 2019/10/16.
 */
public class _186_Reverse_Words_in_a_String_II {

    public void reverseWords(char[] s) {
        reverse(s, 0, s.length-1);
        int i=0;
        int j=i;
        while (j<s.length){
            if (s[j] == ' '){
                reverse(s, i, j-1);
                i = j+1;
            }
            j++;
        }
        reverse(s, i, j-1);
    }


    private void reverse(char[] s ,int i, int j){
        if (i>=s.length || j>=s.length) return;
        while (i<=j){
            char c = s[i];
            s[i] = s[j];
            s[j] = c;
            i++;
            j--;
        }
    }
}
