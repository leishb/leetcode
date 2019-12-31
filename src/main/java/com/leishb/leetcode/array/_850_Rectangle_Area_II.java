package com.leishb.leetcode.array;

import java.util.Arrays;

/**
 * Created by me on 2019/11/25.
 */
public class _850_Rectangle_Area_II {



    public int rectangleArea(int[][] rectangles) {
        Arrays.sort(rectangles, (r1, r2)->{
            if (r1[0]!=r2[0]) return r1[0]-r2[0];
            if (r1[1]!=r2[1]) return r1[1]-r2[1];
            if (r1[2]!=r2[2]) return r1[3]-r2[2];
            if (r1[3]!=r2[3]) return r1[3]-r2[3];
            return 0;
        });



        return 0;
    }
}
