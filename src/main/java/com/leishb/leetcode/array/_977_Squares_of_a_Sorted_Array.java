package com.leishb.leetcode.array;

import org.junit.Test;

/**
 * Created by me on 2019/10/28.
 */
public class _977_Squares_of_a_Sorted_Array {

    public int[] sortedSquares(int[] A) {
        int rightStart = search(A);
        int[] res = new int[A.length];
        int i=rightStart-1, j=rightStart;
        int k=0;
        while (i>=0 && j<A.length){
            if (A[i] * A[i] <= A[j]*A[j]){
                res[k++] = A[i] * A[i];
                i--;
            }else {
                res[k++] = A[j] * A[j];
                j++;
            }
        }
        while (i>=0){
            res[k++] = A[i] * A[i];
            i--;
        }
        while (j<A.length){
            res[k++] = A[j] * A[j];
            j++;
        }
        return res;
    }


    private int search(int[] A){
        int low = 0;
        int high = A.length-1;
        int ans = A.length;
        while (low<=high){
            int mid = (low+high)/2;
            if (A[mid] >=0 ){
                ans = Math.min(ans, mid);
                high = mid-1;
            }else {
                low = mid+1;
            }
        }
        return ans;
    }


    @Test
    public void test(){
        search(new int[] {-7,-3,2,3,11});
        sortedSquares(new int[] {-7,-3,2,3,11});
    }
}
