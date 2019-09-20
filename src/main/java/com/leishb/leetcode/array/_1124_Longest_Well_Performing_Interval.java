package com.leishb.leetcode.array;

import com.leishb.leetcode.tag.PrefixSum;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by me on 2019/9/17.
 */
@PrefixSum
public class _1124_Longest_Well_Performing_Interval {

    public int longestWPI(int[] hours) {
        int res = 0;
        Map<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        for (int i=0;i<hours.length;i++){
            sum += hours[i]>8?1:-1;
            if (sum>0){
                res = i+1;
            }else {
                if (map.containsKey(sum-1)){
                    res = Math.max(res, i-map.get(sum-1));
                }
                map.putIfAbsent(sum, i);
            }
        }
        return res;
    }


    @Test
    public void test(){
        Assert.assertTrue(longestWPI(new int[]{1,1,1,9,9})==3);
    }

}
