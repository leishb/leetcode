package com.leishb.leetcode.array;

import org.junit.Test;

/**
 * Created by me on 2019/11/11.
 */
public class _1053_Previous_Permutation_With_One_Swap {


    /**
     * Accepted
     * @param A
     * @return
     */
    public int[] prevPermOpt1(int[] A) {
        int id = -1;
        for (int i=A.length-1;i>=1;i--){
            if (A[i-1] > A[i]){
                id = i-1;
                break;
            }
        }
        if (id==-1) return A;
        int max = id+1;
        for (int i=A.length-1;i>id;i--){
            if (A[i] > A[max] && A[i] < A[id]){
                max = i;
            }
        }
        int tmp = A[max];
        A[max] = A[id];
        A[id] = tmp;
        return A;
    }


    @Test
    public void test(){
        prevPermOpt1(new int[]{3,1,1,3});
    }
}
