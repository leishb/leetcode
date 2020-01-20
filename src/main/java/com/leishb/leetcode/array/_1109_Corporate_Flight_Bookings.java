package com.leishb.leetcode.array;

import java.util.Arrays;
import java.util.TreeMap;

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


    /**
     * Accepted
     * @param bookings
     * @param n
     * @return
     */
    public int[] corpFlightBookings2(int[][] bookings, int n) {
        int[] res = new int[n];
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int[] booking : bookings){
            map.put(booking[0], map.getOrDefault(booking[0], 0)+booking[2]);
            map.put(booking[1]+1, map.getOrDefault(booking[1]+1, 0)-booking[2]);
        }
        int prev = 0;
        for (int k : map.keySet()){
            map.put(k, map.get(k)+prev);
            prev = map.get(k);
        }
        for (int i=1;i<=n;i++){
            Integer booking = map.floorKey(i);
            if (booking!=null){
                res[i-1] = map.get(booking);
            }
        }
        return res;
    }


    /**
     * Accepted
     * @param bookings
     * @param n
     * @return
     */
    public int[] corpFlightBookings3(int[][] bookings, int n) {
        int[] res = new int[n];
        for (int[] booking : bookings){
            res[booking[0]-1] += booking[2];
            if (booking[1]<n){
                res[booking[1]] -= booking[2];
            }
        }
        for (int i=1;i<n;i++){
            res[i] += res[i-1];
        }
        return res;
    }
}
