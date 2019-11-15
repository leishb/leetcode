package com.leishb.leetcode.design;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by me on 2019/11/7.
 */
public class _271_Encode_and_Decode_Strings_2 {


    private String intToStr(String s){
        int x = s.length();
        char[] cs = new char[4];
        for (int i=0;i<4;i++){
            cs[i] = (char) ((x<<(i*8))&255);
        }
        return new String(cs);
    }


    private int strToInt(String s){
        char[] cs = s.toCharArray();
        int res = 0;
        for (int i=0;i<4;i++){
            res += (cs[i]&255)<<i;
        }
        return res;
    }

    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {
        StringBuffer sb = new StringBuffer();
        for (String s : strs){
            sb.append(intToStr(s));
            sb.append(s);
        }
        return sb.toString();
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
        List<String> res = new ArrayList<>();
        int i = 0;
        while (i<s.length()){
            int len = strToInt(s.substring(i, i+4));
            res.add(s.substring(i+4, i+4+len));
            i = i+4+len;
        }
        return res;
    }
}
