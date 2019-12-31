package com.leishb.leetcode.dp;


import java.util.TreeMap;

/**
 * Created by me on 2019/12/19.
 */
public class _975_Odd_Even_Jump {


    /**
     * Accepted
     * @param A
     * @return
     */
    public int oddEvenJumps(int[] A) {
        boolean[] odd = new boolean[A.length];
        boolean[] even = new boolean[A.length];
        odd[A.length-1] = true;
        even[A.length-1] = true;
        int ans = 1;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(A[A.length-1], A.length-1);
        for (int i=A.length-2;i>=0;i--){
            Integer oddItem = map.ceilingKey(A[i]);
            Integer evenItem = map.floorKey(A[i]);
            if (oddItem!=null && even[map.get(oddItem)]){
                odd[i] = true;
            }
            if (evenItem!=null && odd[map.get(evenItem)]){
                even[i] = true;
            }
            map.put(A[i], i);
            if (odd[i]) ans++;
        }
        return ans;
    }
}
