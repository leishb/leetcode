package com.leishb.leetcode.array;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * Created by me on 2019/8/21.
 */
public class _870_Advantage_Shuffle {


    /**
     * Accepted
     * @param A
     * @param B
     * @return
     */
    public int[] advantageCount(int[] A, int[] B) {
        TreeSet<Integer> treeSet = new TreeSet<>();
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : A){
            treeSet.add(num);
            freq.put(num, freq.getOrDefault(num, 0)+1);
        }
        int[] ans = new int[A.length];
        for (int i=0;i<B.length;i++){
            Integer k = treeSet.higher(B[i]);
            if (k==null){
                ans[i] = treeSet.pollFirst();
            }else {
                ans[i] = k;
                treeSet.remove(k);
            }
            freq.put(ans[i], freq.get(ans[i])-1);
            if (freq.get(ans[i])>0){
                treeSet.add(ans[i]);
            }
        }
        return ans;
    }
    /**
     * Accepted
     * @param A
     * @param B
     * @return
     */
    public int[] advantageCount2(int[] A, int[] B) {
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        for (int num : A){
            treeMap.put(num, treeMap.getOrDefault(num, 0)+1);
        }
        int[] ans = new int[A.length];
        for (int i=0;i<B.length;i++){
            Integer k = treeMap.higherKey(B[i]);
            if (k==null){
                int n = treeMap.firstKey();
                ans[i] = n;
            }else {
                ans[i] = k;
            }
            treeMap.put(ans[i], treeMap.get(ans[i])-1);
            if (treeMap.get(ans[i])==0){
                treeMap.remove(ans[i]);
            }
        }
        return ans;
    }


    /**
     * Accepted
     * @param A
     * @param B
     * @return
     */
    public int[] advantageCount3(int[] A, int[] B) {
        Arrays.sort(A);
        int[][] temp = new int[B.length][2];
        for (int i=0;i<B.length;i++){
            temp[i] = new int[]{B[i],i};
        }
        Arrays.sort(temp, (a1, a2)->a1[0]-a2[0]);
        int[] ans = new int[A.length];
        int l = 0, r = A.length-1;
        for (int i=temp.length-1;i>=0;i--){
            if (temp[i][0] < A[r]){
                ans[temp[i][1]] = A[r--];
            }else {
                ans[temp[i][1]] = A[l++];
            }
        }
        return ans;
    }

    @Test
    public void test(){
        Assert.assertArrayEquals(advantageCount3(new int[]{2,7,11,15}, new int[]{1,10,4,11}), new int[]{2,11,7,15});
        Assert.assertArrayEquals(advantageCount3(new int[]{12,24,8,32}, new int[]{13,25,32,11}), new int[]{24, 32, 8, 12});
        Assert.assertArrayEquals(advantageCount3(new int[]{12,24,8,32}, new int[]{8,25,32,11}), new int[]{12, 32, 8, 24});
        Assert.assertArrayEquals(advantageCount3(new int[]{2,0,4,1,2}, new int[]{1,3,0,0,2}), new int[]{2,4,1,2, 0});
    }
}
