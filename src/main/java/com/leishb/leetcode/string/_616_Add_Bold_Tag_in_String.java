package com.leishb.leetcode.string;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by me on 2019/10/29.
 */
public class _616_Add_Bold_Tag_in_String {


    /**
     * Accepted
     * @param s
     * @param dict
     * @return
     */
    public String addBoldTag(String s, String[] dict) {
        List<int[]> list = new ArrayList<>();
        for (String d : dict){
            int prev = 0;
            int index = s.indexOf(d, prev);
            while (index!=-1){
                list.add(new int[]{index, index+d.length()-1});
                prev = index+1;
                index = s.indexOf(d, prev);
            }
        }
        Collections.sort(list, (a, b)->a[0]!=b[0]?a[0]-b[0]:a[1]-b[1]);
        List<int[]> merged = new ArrayList<>();
        for (int[] it : list){
            if(merged.isEmpty()){
                merged.add(it);
            }else {
                int[] last = merged.get(merged.size()-1);
                if (it[0] > last[1]+1){
                    merged.add(it);
                }else {
                    last[1] = Math.max(last[1], it[1]);
                }
            }
        }
        StringBuffer sb = new StringBuffer();
        int prev = 0;
        for (int[] it : merged){
            sb.append(s.substring(prev, it[0])).append("<b>")
                    .append(s.substring(it[0], it[1]+1)).append("</b>");
            prev = it[1]+1;
        }
        if (prev < s.length()) sb.append(s.substring(prev));
        return sb.toString();
    }


    @Test
    public void test(){
        System.out.println(addBoldTag("aaabbcc", new String[]{"aaa","aab","bc"}));
    }
}
