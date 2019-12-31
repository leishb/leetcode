package com.leishb.leetcode.array;

import org.junit.Test;

/**
 * Created by me on 2019/12/13.
 */
public class _302_Smallest_Rectangle_Enclosing_Black_Pixels {


    public int minArea(char[][] image, int x, int y) {
        dfs(image, x, y);
        return (maxRow-minRow+1) * (maxCol-minCol+1);
    }


    int minRow = Integer.MAX_VALUE, minCol = Integer.MAX_VALUE, maxRow = -1, maxCol = -1;
    int[] d = new int[]{0, 1, 0, -1, 0};

    private void dfs(char[][] image, int i, int j){
        minRow = Math.min(minRow, i);
        minCol = Math.min(minCol, j);
        maxRow = Math.max(maxRow, i);
        maxCol = Math.max(maxCol, j);
        image[i][j] = '0';
        for (int x=0;x+1<d.length;x++){
            int ni = i + d[x];
            int nj = j + d[x+1];
            if (ni>=0 && nj>=0 && ni<image.length && nj<image[0].length && image[ni][nj]=='1'){
                dfs(image, ni, nj);
            }
        }
    }


    @Test
    public void test(){
        minArea(new char[][]{{'1', '1'}}, 0, 1);
    }
}
