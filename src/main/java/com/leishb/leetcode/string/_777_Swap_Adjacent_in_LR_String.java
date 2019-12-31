package com.leishb.leetcode.string;

/**
 * Created by me on 2019/12/25.
 */
public class _777_Swap_Adjacent_in_LR_String {




    public boolean canTransform(String start, String end) {
        if (!start.replaceAll("X", "").equals(end.replaceAll("X", ""))){
            return false;
        }
        int p1=0, p2=0;
        while (p1<start.length() && p2<end.length()){
            while (p1<start.length() && start.charAt(p1)=='X')p1++;
            while (p2<end.length() && end.charAt(p2)=='X') p2++;
            if (p1==start.length() && p2==end.length()) {
                return true;
            }
            if (p1==start.length() || p2==end.length()) {
                return false;
            }
            if (start.charAt(p1)!=end.charAt(p2)){
                return false;
            }
            if (start.charAt(p1)=='L' && p1 < p2){
                return false;
            }
            if (start.charAt(p1)=='R' && p1 > p2){
                return false;
            }
            p1++;
            p2++;
        }
        return true;
    }
}
