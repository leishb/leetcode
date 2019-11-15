package com.leishb.leetcode.design;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by me on 2019/11/7.
 */
public class _271_Encode_and_Decode_Strings {

    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {
        if (strs.size()==0) return Character.toString((char)258);
        String d = Character.toString((char)257);
        StringBuffer sb = new StringBuffer();
        for (String s : strs){
            sb.append(s);
            sb.append(d);
        }
        return sb.substring(0,sb.length()-1);
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
        String d = Character.toString((char)258);
        if (d.equals(s)) return new ArrayList<>();
        d = Character.toString((char)257);
        return Arrays.asList(s.split(d, -1));
    }
}
