package com.leishb.leetcode.dfs;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by me on 2019/6/28.
 */
public class _417_Pacific_Atlantic_Water_Flow {


    /**
     * Accepted
     * @param matrix
     * @return
     */
    public List<List<Integer>> pacificAtlantic(int[][] matrix) {
        List<List<Integer>> result = new ArrayList();
        if(matrix==null || matrix.length==0){
            return result;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] reachPacific = new int[m][n];
        int[][] reachAtlantic = new int[m][n];

        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                boolean[][] v1 = new boolean[m][n];
                if(canReachPacific(matrix, reachPacific, i, j, v1) ){
                    boolean[][] v2 = new boolean[m][n];
                    if( canReachAtlantic(matrix, reachAtlantic, i, j, v2)){
                        result.add(Arrays.asList(i, j));
                    }
                }
            }
        }
        return result;
    }



    private boolean canReachPacific(int[][] matrix, int[][] reachPacific, int i, int j, boolean[][] v1 ){
        if(i==0 || j==0){
            return true;
        }
        if(v1[i][j]){
            return false;
        }
        v1[i][j] = true;
        int m = matrix.length;
        int n = matrix[0].length;

        if(matrix[i][j] >= matrix[i-1][j] &&reachPacific[i-1][j]!=2 && (reachPacific[i-1][j]==1 || canReachPacific(matrix, reachPacific, i-1, j,v1))){
            reachPacific[i][j]=1;
            return true;
        }

        if(matrix[i][j] >= matrix[i][j-1] &&reachPacific[i][j-1]!=2 && (reachPacific[i][j-1]==1 || canReachPacific(matrix, reachPacific, i, j-1,v1))){
            reachPacific[i][j]=1;
            return true;
        }

        if(i<m-1 && matrix[i][j] >= matrix[i+1][j] &&reachPacific[i+1][j]!=2 && (reachPacific[i+1][j]==1 || canReachPacific(matrix, reachPacific, i+1, j, v1))){
            reachPacific[i][j]=1;
            return true;
        }

        if(j<n-1 && matrix[i][j] >= matrix[i][j+1] &&reachPacific[i][j+1]!=2 && (reachPacific[i][j+1]==1 || canReachPacific(matrix, reachPacific, i, j+1, v1))){
            reachPacific[i][j]=1;
            return true;
        }
        reachPacific[i][j]=2;
        return false;
    }


    private boolean canReachAtlantic(int[][] matrix, int[][] reachAtlantic, int i, int j,boolean[][] v2){
        int m = matrix.length;
        int n = matrix[0].length;

        if(i==m-1 || j==n-1){
            return true;
        }
        if(v2[i][j]){
            return false;
        }
        v2[i][j] = true;
        if(i>0 && matrix[i][j] >= matrix[i-1][j] &&reachAtlantic[i-1][j]!=2 && (reachAtlantic[i-1][j]==1 || canReachAtlantic(matrix, reachAtlantic, i-1, j, v2))){
            reachAtlantic[i][j] = 1;
            return true;
        }

        if(j>0 && matrix[i][j] >= matrix[i][j-1] &&reachAtlantic[i][j-1]!=2 && (reachAtlantic[i][j-1]==1 || canReachAtlantic(matrix, reachAtlantic, i, j-1, v2))){
            reachAtlantic[i][j] = 1;
            return true;
        }

        if(i<m-1 && matrix[i][j] >= matrix[i+1][j] &&reachAtlantic[i+1][j]!=2 && (reachAtlantic[i+1][j]==1 || canReachAtlantic(matrix, reachAtlantic, i+1, j, v2))){
            reachAtlantic[i][j] = 1;
            return true;
        }

        if(j<n-1 && matrix[i][j] >= matrix[i][j+1] &&reachAtlantic[i][j+1]!=2 && (reachAtlantic[i][j+1]==1 || canReachAtlantic(matrix, reachAtlantic, i, j+1, v2))){
            reachAtlantic[i][j] = 1;
            return true;
        }
        reachAtlantic[i][j] = 2;
        return false;
    }

    @Test
    public void test(){
        System.out.println(pacificAtlantic(new int[][]{{1,2,2,3,5},{3,2,3,4,4},{2,4,5,3,1},{6,7,1,1,5},{5,1,1,2,4}}));
    }
}
