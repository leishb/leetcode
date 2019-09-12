package com.leishb.leetcode.array;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by me on 2019/9/9.
 */
public class _986_Interval_List_Intersections {



    public int[][] intervalIntersection(int[][] A, int[][] B) {
        List<int[]> list = new ArrayList<>();
        int i=0, j=0;
        while (i<A.length && j<B.length){
            if (A[i][1] < B[j][0]){
                i++;
            }else if (A[i][0] > B[j][1]){
                j++;
            }else if (A[i][1]==B[j][1]){
                list.add(new int[]{Math.max(A[i][0], B[j][0]), A[i][1]});
                i++;
                j++;
            }else if (A[i][1] >= B[j][0] && A[i][1] < B[j][1]){
                list.add(new int[]{Math.max(A[i][0], B[j][0]), Math.min(A[i][1], B[j][1])});
                B[j][0] = A[i][1]+1;
                i++;
            }else if (A[i][1] >= B[j][0] && A[i][1] > B[j][1]){
                list.add(new int[]{Math.max(A[i][0], B[j][0]), Math.min(A[i][1], B[j][1])});
                A[i][0] = B[j][1]+1;
                j++;
            }else if (A[i][0] <= B[j][1] && A[i][1] > B[j][1]){
                list.add(new int[]{Math.max(A[i][0], B[j][0]), Math.min(A[i][1], B[j][1])});
                A[i][0] = B[j][1]+1;
                j++;
            }else if (A[i][0] <= B[j][1] && A[i][1] < B[j][1]){
                list.add(new int[]{Math.max(A[i][0], B[j][0]), Math.min(A[i][1], B[j][1])});
                B[j][0] = A[i][1]+1;
                i++;
            }
        }
        int[][] ans = new int[list.size()][2];
        for (int k=0;k<list.size();k++){
            ans[k] = list.get(k);
        }
        return ans;
    }


    public int[][] intervalIntersection2(int[][] A, int[][] B) {
        int i=0,j=0;
        List<int[]> list = new ArrayList<>();
        while (i<A.length && j<B.length){
            int start = Math.max(A[i][0], B[j][0]);
            int end= Math.min(A[i][1], B[j][1]);
            if (start<=end)list.add(new int[]{start, end});
            if (A[i][1] >= B[j][1])j++;
            else i++;
        }
        int[][] ans = new int[list.size()][2];
        for (int k=0;k<list.size();k++){
            ans[k] = list.get(k);
        }
        return ans;
    }
    @Test
    public void test(){
        intervalIntersection(new int[][]{{0,2},{5,10},{13,23},{24,25}}, new int[][]{{1,5},{8,12},{15,24},{25,26}});
    }
}
