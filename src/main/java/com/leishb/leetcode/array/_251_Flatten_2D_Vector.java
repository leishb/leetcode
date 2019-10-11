package com.leishb.leetcode.array;

/**
 * Created by me on 2019/10/9.
 */
public class _251_Flatten_2D_Vector {

    int[][] grid = null;
    int i = 0;
    int j = 0;

    public _251_Flatten_2D_Vector(int[][] v) {
        grid = v;
    }

    public int next() {
        while (i<grid.length && grid[i].length==0){
            i++;
        }
        int num = grid[i][j];
        j++;
        if (j>=grid[i].length){
            i++;
            j=0;
        }
        return num;
    }

    public boolean hasNext() {
        while (i<grid.length && grid[i].length==0){
            i++;
        }
        return i<grid.length;
    }


    public static void main(String[] args){
        _251_Flatten_2D_Vector v  = new _251_Flatten_2D_Vector(new int[][]{{1,2},{3},{4}});
        v.next();
        v.next();
        v.next();
        v.hasNext();
        v.hasNext();
        v.next();
        v.hasNext();
    }
}
