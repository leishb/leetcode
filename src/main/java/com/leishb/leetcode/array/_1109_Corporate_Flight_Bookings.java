package com.leishb.leetcode.array;

import java.util.Arrays;

/**
 * Created by me on 2019/9/17.
 */
public class _1109_Corporate_Flight_Bookings {


    /**
     * TLE
     * @param bookings
     * @param n
     * @return
     */
    public int[] corpFlightBookings(int[][] bookings, int n) {
        Arrays.sort(bookings, (b1, b2)->b1[0]-b2[0]);
        int[] ans = new int[n];
        for (int i=1;i<=n;i++){
            int p = 0;
            for (int[] book : bookings) {
                if (book[0]<=i && book[1]>=i){
                    p += book[2];
                }else if (book[0] > i){
                    break;
                }
            }
            ans[i-1] = p;
        }
        return ans;
    }

}
