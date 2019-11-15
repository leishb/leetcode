package com.leishb.leetcode.slidewindow;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by me on 2019/11/11.
 */
public class _1100_Find_K_Length_Substrings_With_No_Repeated_Characters {


    /**
     * Accepted
     * @param S
     * @param K
     * @return
     */
    public int numKLenSubstrNoRepeats(String S, int K) {
        if (K>S.length()) return 0;
        Set<Character> set = new HashSet<>();
        int j = 0;
        int res = 0;
        for (int i=0;i<S.length();i++){
            while (set.contains(S.charAt(i)) || i-j+1>K){
                set.remove(S.charAt(j));
                j++;
            }
            set.add(S.charAt(i));
            if (i-j+1==K){
                res++;
            }
        }
        return res;
    }
}
