package com.leishb.leetcode.string;

/**
 * Created by me on 2019/10/14.
 */
public class _151_Reverse_Words_in_a_String {

    public String reverseWords(String s) {
        if(s==null || s.length()==0) return s;
        String[] ss = s.split(" ");
        StringBuffer sb = new StringBuffer();
        for (int i=ss.length-1;i>=0;i--){
            if (" ".equals(ss[i]) || "".equals(ss[i])) continue;
            sb.append(ss[i]);
            sb.append(" ");
        }
        return sb.length()>0?sb.substring(0, sb.length()-1):sb.toString();
    }
}
