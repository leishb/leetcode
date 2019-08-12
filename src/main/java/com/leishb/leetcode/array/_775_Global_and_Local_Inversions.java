package com.leishb.leetcode.array;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by me on 2019/8/9.
 */
public class _775_Global_and_Local_Inversions {


    /**
     * Accepted
     * @param A
     * @return
     */
    public boolean isIdealPermutation(int[] A) {
        int n = A.length;
        if (n==0)return true;
        int local = 0;
        for (int i=1;i<n;i++){
            if (A[i]<A[i-1]){
                local++;
            }
        }
        mergeSort(A, 0, n-1);
        return local==globals;
    }

    /**
     * Accepted
     * @param A
     * @return
     */
    public boolean isIdealPermutation2(int[] A) {
        int n = A.length;
        if (n==0)return true;
        int local = 0;
        for (int i=1;i<n;i++){
            if (A[i]<A[i-1]){
                local++;
            }
        }
        int global = 0;
        for (int i=0;i<A.length;i++){
            for (int j=i+1;j<A.length;j++){
                if (A[j]<A[i]){
                    global++;
                }
            }
        }
        return local==global;
    }

    public boolean isIdealPermutation3(int[] A) {
        int n = A.length;
        int max = 0;
        for (int i=0;i<n-2;i++){
            max = Math.max(max, A[i]);
            if (max>A[i+2])return false;
        }
        return true;
    }

    int globals = 0;


    private void mergeSort(int[] A, int start, int end){
        if (start<end){
            int mid = (start+end)/2;
            mergeSort(A, start, mid);
            mergeSort(A, mid+1, end);
            merge(A, start, mid, end);
        }
    }


    private void merge(int[] A, int start, int mid, int end){
        int[] temp = new int[end-start+1];
        int i = start;
        int j = mid+1;
        int k = 0;
        while (i<=mid && j<=end){
            int skips = 0;
            while (j<=end && A[i]>A[j]){
                temp[k++] = A[j++];
                skips++;
            }
            while (i<=mid && j<=end && A[i]<=A[j]){
                temp[k++] = A[i++];
                globals += skips;
            }
        }
        while (i<=mid){
            temp[k++] = A[i++];
            globals += (end-mid);
        }
        while (j<=end){
            temp[k++] = A[j++];
        }
        for (int x=0;x<temp.length;x++){
            A[x+start]=temp[x];
        }
    }


    @Test
    public void test(){
        Assert.assertTrue(isIdealPermutation(new int[]{1,0}));
        Assert.assertFalse(isIdealPermutation(new int[]{0,2,3,1}));
    }
}
