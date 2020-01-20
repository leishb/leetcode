package com.leishb.leetcode.array;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by me on 2020/1/14.
 */
public class _969_Pancake_Sorting {


    /**
     * Accepted
     * @param A
     * @return
     */
    public List<Integer> pancakeSort(int[] A) {
        List<Integer> result = new ArrayList<>();
        int largest = A.length;
        for (int i=0;i<A.length;i++){
            int index = find(A, largest);
            reverse(A, index);
            reverse(A, largest-1);
            result.add(index+1);
            result.add(largest);
            largest--;
        }
        return result;
    }


    private int find(int[] A, int largest){
        for (int i=0;i<A.length;i++){
            if (i==largest){
                return i;
            }
        }
        return -1;
    }

    private void reverse(int[] A, int j){
        int i=0;
        while (i<j){
            int temp = A[i];
            A[i] = A[j];
            A[j]= temp;
            i++;j--;
        }
    }
}
