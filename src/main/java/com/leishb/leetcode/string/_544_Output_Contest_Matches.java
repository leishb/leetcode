package com.leishb.leetcode.string;

import org.junit.Test;

/**
 * Created by me on 2019/10/28.
 */
public class _544_Output_Contest_Matches {



    public String findContestMatch(int n) {
        int k = 0;
        while (1<<k !=n) k++;
        StringBuffer sb = new StringBuffer();
        for (int i=1;i<=n;i++){
            sb.append(i).append("@");
        }
        String s = sb.substring(0, sb.length()-1);
        for (int i=0;i<k;i++){
            String[] ss = s.split("@");
            int x = 0, y = ss.length-1;
            sb = new StringBuffer();
            while (x < y){
                sb.append("(").append(ss[x]).append(",").append(ss[y])
                        .append(")").append("@");
                x++;
                y--;
            }
            s = sb.substring(0, sb.length()-1);
        }
        s.replaceAll("@", ",");
        return s;
    }

    @Test
    public void test(){
        System.out.println(findContestMatch(8));
    }
}
