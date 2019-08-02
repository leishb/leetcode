package com.leishb.leetcode.string;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * Created by me on 2019/7/31.
 */
public class _636_Exclusive_Time_of_Functions {


    /**
     * Accepted
     * @param n
     * @param logs
     * @return
     */
    public int[] exclusiveTime(int n, List<String> logs) {
        int[] ans = new int[n];
        Stack<Integer> stack = new Stack<>();
        int prev = 0;
        for (String log : logs){
            String[] parts = log.split(":");
            if (!stack.isEmpty()){
                ans[stack.peek()] += Integer.parseInt(parts[2]) - prev;
            }
            prev = Integer.parseInt(parts[2]);
            if ("start".equals(parts[1])){
                stack.push(Integer.parseInt(parts[0]));
            }else {
                ans[stack.pop()]++;
                prev++;
            }
        }
        return ans;
    }

    @Test
    public void test(){
        exclusiveTime(8, Arrays.asList(new String[]{"0:start:0","1:start:5","2:start:6","3:start:9","4:start:11","5:start:12","6:start:14","7:start:15","1:start:24","1:end:29","7:end:34","6:end:37","5:end:39","4:end:40","3:end:45","0:start:49","0:end:54","5:start:55","5:end:59","4:start:63","4:end:66","2:start:69","2:end:70","2:start:74","6:start:78","0:start:79","0:end:80","6:end:85","1:start:89","1:end:93","2:end:96","2:end:100","1:end:102","2:start:105","2:end:109","0:end:114"}));
    }
}
