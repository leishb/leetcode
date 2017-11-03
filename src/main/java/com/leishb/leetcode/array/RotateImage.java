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


        System.out.println(spiralOrder2(new int[][]{ { 1, 2, 3 }, {4, 5, 6 }, {7, 8, 9 }}));

        System.out.println(spiralOrder2(new int[][]{ { 1, 2, 3,4 }, {5, 6, 7 ,8}, {9, 10, 11,12 }}));
        System.out.println(spiralOrder2(new int[][]{{1}}));
        System.out.println(spiralOrder2(new int[][]{{1,2},{3,4}}));
        System.out.println(spiralOrder2(new int[][]{{1,2,3,4},{5,6,7,8},{9,10,11,12}, {13,14,15,16}}));
        System.out.println(spiralOrder2(new int[][]{{1,2,3,4,5,6,7,8,9,10},{11,12,13,14,15,16,17,18,19,20}}));

        System.out.println(generateMatrix(3));
        System.out.println(generateMatrix(4));
        System.out.println(generateMatrix(2));

        Assert.assertArrayEquals(generateMatrix(2), generateMatrix2(2));
        Assert.assertArrayEquals(generateMatrix(3), generateMatrix2(3));
        Assert.assertArrayEquals(generateMatrix(4), generateMatrix2(4));
        Assert.assertArrayEquals(generateMatrix(1), generateMatrix2(1));
        Assert.assertArrayEquals(generateMatrix(0), generateMatrix2(0));
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


    /**
     * Accepted
     * @param n
     * @return
     */
    public int[][] generateMatrix(int n) {
        int[][] result = new int[n][n];
        int k=0;
        int c=1;
        while (true){
            if (n==0){
                break;
            }
            if (n==1){
                result[k][k]=c;
                break;
            }
            for (int i=k;i<k+n-1;i++){
                result[k][i]=c++;
            }
            for (int i=k;i<k+n-1;i++){
                result[i][k+n-1]=c++;
            }
            for (int i=k+n-1;i>k;i--){
                result[k+n-1][i]=c++;
            }
            for (int i=k+n-1;i>k;i--){
                result[i][k]=c++;
            }
            k++;
            n-=2;
        }
        return result;
    }


    public List<Integer> spiralOrder2(int[][] matrix) {
        List<Integer> ret = new ArrayList<>();
        if (matrix.length==0){
            return ret;
        }
        int rowStart = 0;
        int rowEnd = matrix.length-1;
        int colStart =0;
        int colEnd = matrix[0].length-1;
        while (colStart<=colEnd && rowStart<=rowEnd){
            for (int i=colStart;i<=colEnd;i++){
                ret.add(matrix[rowStart][i]);
            }
            rowStart++;
            for (int i=rowStart;i<=rowEnd;i++){
                ret.add(matrix[i][colEnd]);
            }
            colEnd--;
            if (rowStart<=rowEnd){
                for (int i=colEnd;i>=colStart;i--){
                    ret.add(matrix[rowEnd][i]);
                }
            }
            rowEnd--;
            if (colStart<=rowEnd){
                for (int i=rowEnd;i>=rowStart;i--){
                    ret.add(matrix[i][colStart]);
                }
            }
            colStart++;
        }
        return ret;
    }


    /**
     * Accepted
     * @param n
     * @return
     */
    public int[][] generateMatrix2(int n) {
        int[][] ret = new int[n][n];
        int rowStart = 0;
        int rowEnd = n-1;
        int colStart =0;
        int colEnd = n-1;
        int k=1;
        while (colStart<=colEnd && rowStart<=rowEnd){
            for (int i=colStart;i<=colEnd;i++){
                ret[rowStart][i]=k++;
            }
            rowStart++;
            for (int i=rowStart;i<=rowEnd;i++){
                ret[i][colEnd]=k++;
            }
            colEnd--;
            for (int i=colEnd;i>=colStart;i--){
                ret[rowEnd][i]=k++;
            }
            rowEnd--;
            for (int i=rowEnd;i>=rowStart;i--){
                ret[i][colStart]=k++;
            }
            colStart++;
        }
        return ret;
    }
}
