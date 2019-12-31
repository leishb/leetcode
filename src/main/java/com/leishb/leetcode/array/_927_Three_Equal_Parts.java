package com.leishb.leetcode.array;

/**
 * Created by me on 2019/12/5.
 */
public class _927_Three_Equal_Parts {


    /**
     * Accepted
     * @param A
     * @return
     */
    public int[] threeEqualParts(int[] A) {
        int ones = 0;
        for (int i : A){
            if (i==1)ones++;
        }
        if (ones==0) return new int[]{0, 2};
        if (ones%3!=0) return new int[]{-1,-1};
        int thridIdx = 0;
        int temp = 0;
        for (int i=A.length-1;i>=0;i--){
            if (A[i]==1)temp++;
            if (temp==ones/3){
                thridIdx = i;
                break;
            }
        }
        int res1 = match(A, 0, thridIdx);
        if (res1==-1) return new int[]{-1, -1};
        int res2 = match(A, res1+1, thridIdx);
        if (res2==-1) return new int[]{-1, -1};
        return new int[]{res1, res2+1};
    }


    private int match(int[] A, int left , int right){
        while (A[left]==0) left++;
        while (right<A.length){
            if (A[left]!=A[right]) return -1;
            left++;
            right++;
        }
        return left-1;
    }
}
