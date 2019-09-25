package com.leishb.leetcode.array;

import java.util.*;

/**
 * Created by me on 2019/9/25.
 */
public class _350_Intersection_of_Two_Arrays_II {

    public int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums1){
            map.put(num, map.getOrDefault(num, 0)+1);
        }
        List<Integer> list = new ArrayList<>();
        for (int num : nums2){
            if (map.getOrDefault(num, 0) > 0){
                list.add(num);
                map.put(num, map.get(num)-1);
            }
        }
        int[] ans = new int[list.size()];
        for (int i=0;i<ans.length;i++){
            ans[i]= list.get(i);
        }
        return ans;
    }


    public int[] intersect2(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        List<Integer> list = new ArrayList<>();
        int i=0,j=0;
        while (i< nums1.length && j<nums2.length){
            if (nums1[i]==nums2[j]){
                list.add(nums1[i]);
                i++;
                j++;
            }else if (nums1[i] < nums2[j]) {
                i++;
            }else {
                j++;
            }
        }
        int[] ans = new int[list.size()];
        for (i=0;i<ans.length;i++){
            ans[i]= list.get(i);
        }
        return ans;
    }
}
