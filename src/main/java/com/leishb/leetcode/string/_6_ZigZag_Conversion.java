package com.leishb.leetcode.string;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by me on 2019/11/4.
 */
public class _6_ZigZag_Conversion {


    /**
     * Accepted
     * @param s
     * @param numRows
     * @return
     */
    public String convert(String s, int numRows) {
        if (numRows==1) return s;
        List<StringBuffer> rows = new ArrayList<>();
        for (int i=0;i<Math.min(numRows, s.length());i++){
            rows.add(new StringBuffer());
        }

        int curRow = 0;
        boolean goDown = false;
        for (int i = 0; i<s.length();i++){
            char c = s.charAt(i);
            rows.get(curRow).append(c);
            if (curRow==0 || curRow == rows.size()-1) goDown = !goDown;
            curRow += goDown?1:-1;
        }
        StringBuffer res = new StringBuffer();
        for (StringBuffer sb : rows){
            res.append(sb);
        }
        return res.toString();
    }
}
