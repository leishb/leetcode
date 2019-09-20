package com.leishb.leetcode.array;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by me on 2019/9/15.
 */
public class _1072_Flip_Columns_For_Maximum_Number_of_Equal_Rows {

    public int maxEqualRowsAfterFlips(int[][] matrix) {
        Map<String, Integer> map = new HashMap<>();
        int max = 0;
        for (int i=0;i<matrix.length;i++){
            String s1 = "";
            String s2 = "";
            for (int j=0;j<matrix[i].length;j++){
                s1 = s1 + matrix[i][j];
                s2 = s2 + Math.abs(1-matrix[i][j]);
            }
            map.put(s1,map.getOrDefault(s1, 0)+1);
            map.put(s2,map.getOrDefault(s2, 0)+1);
            max = Math.max(max, map.get(s1));
            max = Math.max(max, map.get(s2));
        }
        return max;
    }


    public int maxEqualRowsAfterFlips2(int[][] matrix) {
        Map<String, Integer> map = new HashMap<>();
        int max = 0;
        for (int i=0;i<matrix.length;i++){
            StringBuffer s1 = new StringBuffer();
            StringBuffer s2 = new StringBuffer();
            for (int j=0;j<matrix[i].length;j++){
                s1.append(matrix[i][j]);
                s2.append(1-matrix[i][j]);
            }
            map.put(s1.toString(),map.getOrDefault(s1.toString(), 0)+1);
            map.put(s2.toString(),map.getOrDefault(s2.toString(), 0)+1);
            max = Math.max(max, map.get(s1.toString()));
            max = Math.max(max, map.get(s2.toString()));
        }
        return max;
    }
}
