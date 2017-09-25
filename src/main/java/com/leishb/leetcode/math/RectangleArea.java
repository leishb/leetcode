package com.leishb.leetcode.math;

/**
 * Created by me on 2017/9/25.
 */
public class RectangleArea {


    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        int left = Math.max(A,E);
        int right = Math.min(C,G);
        int bottom= Math.max(B,F);
        int top = Math.min(D,H);

        int area1 = (C-A)*(D-B);
        int area2 = (G-E)*(H-F);

        int overlap = 0;

        if (right > left && top > bottom){
            overlap = (right-left)*(top-bottom);
        }
        return area1+area2-overlap;
    }
}
