package com.leishb.leetcode.array;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by me on 2018/1/3.
 */
public class SmallestRange {

    @Test
    public void test(){
        List<List<Integer>> list = new ArrayList<>();
        list.add(Arrays.asList(4, 10, 15,24,26));
        list.add(Arrays.asList(0,9,12,20));
        list.add(Arrays.asList(5,18,22,30));

        int[] ranget = smallestRange2(list);
        System.out.println(ranget[0] + "-" + ranget[1]);
    }

    public int[] smallestRange2(List<List<Integer>> nums) {
        int left = 0, right = Integer.MAX_VALUE;
        for (int i = 0;i<nums.size();i++){
            for (int j=0;j<nums.get(i).size();j++){
                for (int k=i;k<nums.size();k++){
                    for (int l=(k==i?j:0);l<nums.get(k).size();l++){
                        int min = Math.min(nums.get(i).get(j), nums.get(k).get(l));
                        int max = Math.max(nums.get(i).get(j), nums.get(k).get(l));
                        boolean valid = true;
                        for (int m=0;m<nums.size();m++){
                            boolean flag = false;
                            for (int n=0;n<nums.get(m).size();n++){
                                if (nums.get(m).get(n) >= min && nums.get(m).get(n) <= max){
                                    flag = true;
                                    break;
                                }
                            }
                            if (!flag){
                                valid = false;
                                break;
                            }
                        }
                        if (!valid){
                            continue;
                        }
                        if (right-left > max-min || (right-left==max-min && left>min)){
                            left = min;
                            right = max;
                        }
                    }
                }
            }
        }
        return new int[]{left, right};
    }
}
