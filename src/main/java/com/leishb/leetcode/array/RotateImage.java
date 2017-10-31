package com.leishb.leetcode.array;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by me on 2017/10/30.
 */
public class RotateImage {



    @Test
    public void test(){

        int[][] matrix = new int[][]{{5,1,9,11},{2,4,8,10},{13,3,6,7},{15,14,12,16}};

        rotate2(matrix);

        Assert.assertArrayEquals(matrix, new int[][]{{15,13,2,5},{14,3,4,1},{12,6,8,9},{16,7,10,11}});


        System.out.println(spiralOrder(new int[][]{ { 1, 2, 3 }, {4, 5, 6 }, {7, 8, 9 }}));

        System.out.println(spiralOrder(new int[][]{ { 1, 2, 3,4 }, {5, 6, 7 ,8}, {9, 10, 11,12 }}));
        System.out.println(spiralOrder(new int[][]{{1}}));
        System.out.println(spiralOrder(new int[][]{{1,2},{3,4}}));
        System.out.println(spiralOrder(new int[][]{{1,2,3,4},{5,6,7,8},{9,10,11,12}, {13,14,15,16}}));
        System.out.println(spiralOrder(new int[][]{{1,2,3,4,5,6,7,8,9,10},{11,12,13,14,15,16,17,18,19,20}}));
    }


    /**
     * Accepted
     * @param matrix
     */
    public void rotate(int[][] matrix) {
        int x = 0;
        int y = matrix.length-1;
        int tmp;
        while(x<y){
            for (int k=x;k<y;k++){
                tmp = matrix[x][k];
                matrix[x][k] = matrix[y-k+x][x];
                matrix[y-k+x][x]=matrix[y][y-k+x];
                matrix[y][y-k+x]=matrix[k][y];
                matrix[k][y]=tmp;
            }
            x++;
            y--;
        }
    }

    /**
     * Accepted
     * @param matrix
     */
    public void rotate2(int[][] matrix) {
        int up =0;
        int down = matrix.length-1;
        int temp;
        while (up < down){
            for (int i=0;i<matrix.length;i++){
                temp = matrix[up][i];
                matrix[up][i]=matrix[down][i];
                matrix[down][i]=temp;
            }
            up++;
            down--;
        }
        for (int i=0;i<matrix.length;i++){
            for (int j=i+1;j<matrix.length;j++){
                temp=matrix[i][j];
                matrix[i][j]=matrix[j][i];
                matrix[j][i]=temp;
            }
        }
    }


    /**
     * Accepted
     * @param matrix
     * @return
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<Integer>();
        if (matrix.length==0){
            return result;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int k =0;
        while (true) {
            if (m==0 || n==0){
                break;
            }
            if (m==1){
                for (int i=k;i<=k+n-1;i++){
                    result.add(matrix[k][i]);
                }
                break;
            }else if (n==1){
                for (int i=k;i<=k+m-1;i++){
                    result.add(matrix[i][k]);
                }
                break;
            }


            for (int i=k;i<k+n-1;i++){
                result.add(matrix[k][i]);
            }
            for (int i=k;i<k+m-1;i++){
                result.add(matrix[i][k+n-1]);
            }
            for (int i=k+n-1;i>k;i--){
                result.add(matrix[k+m-1][i]);
            }
            for (int i=k+m-1;i>k;i--){
                result.add(matrix[i][k]);
            }
            k++;
            m-=2;
            n-=2;
        }
        return result;
    }
}
