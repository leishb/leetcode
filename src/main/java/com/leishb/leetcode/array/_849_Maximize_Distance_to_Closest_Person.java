package com.leishb.leetcode.array;

/**
 * Created by me on 2019/12/27.
 */
public class _849_Maximize_Distance_to_Closest_Person {



    public int maxDistToClosest(int[] seats) {
        int n = seats.length;
        int[] left = new int[seats.length];
        left[0] = seats[0]==0?n:0;
        for (int i=1;i<left.length;i++){
            left[i] = seats[i]==1?0:(left[i-1]+1);
        }
        int temp = seats[n-1]==0?n:0;
        int max = Math.min(temp, left[n-1]);
        for (int i=n-2;i>=0;i--){
            temp = seats[i]==1?0:(temp+1);
            max = Math.max(max, Math.min(temp, left[i]));
        }
        return max;
    }


    public int maxDistToClosest2(int[] seats) {
        int res = 0, n = seats.length, last = -1;
        for (int i=0;i<n;i++){
            if (seats[i]==1){
                res = last<0 ? i : Math.max(res, (i-last)/2);
                last = i;
            }
        }
        res = Math.max(res, n-1-last);
        return res;
    }
}
