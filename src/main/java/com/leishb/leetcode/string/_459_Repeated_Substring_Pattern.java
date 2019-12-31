package com.leishb.leetcode.string;

/**
 * Created by me on 2019/12/2.
 */
public class _459_Repeated_Substring_Pattern {

    public boolean repeatedSubstringPattern(String s) {
        int len = s.length();
        for (int l = len/2;l>0;l--){
            if (len%l==0){
                int k = len/l;
                String sub = s.substring(0, l);
                StringBuffer sb = new StringBuffer();
                for (int i=0;i<k;i++){
                    sb.append(sub);
                }
                if (s.equals(sb.toString())) return true;
            }
        }
        return false;
    }
}
