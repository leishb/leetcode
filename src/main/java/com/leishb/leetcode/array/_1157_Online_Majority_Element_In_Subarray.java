package com.leishb.leetcode.array;

import java.util.*;

/**
 * Created by me on 2019/12/24.
 */
public class _1157_Online_Majority_Element_In_Subarray {


    class MajorityChecker {


        int[] arr;
        int tryBound ;
        Map<Integer, List<Integer>> map ;

        public MajorityChecker(int[] arr) {
            this.arr = arr;
            this.tryBound = 20;
            map = new HashMap<>();
            for (int i=0;i<arr.length;i++){
                map.putIfAbsent(arr[i], new ArrayList<>());
                map.get(arr[i]).add(i);
            }
        }

        public int query(int left, int right, int threshold) {
            for (int i=0;i<tryBound;i++){
                int major = arr[new Random().nextInt(right-left+1)];
                int counts = getOccurnce(left, right, major);
                if (counts>=threshold) return major;
            }
            return -1;
        }


        private int getOccurnce(int left, int right, int major){
            List<Integer> list = map.get(major);
            int i = Collections.binarySearch(list, left);
            if (i<0) i = -(i+1);
            if (i==list.size()) return 0;
            int j = Collections.binarySearch(list, right);
            if (j<0) j = -(j+1)-1;
            return j-i+1;
        }
    }
}
