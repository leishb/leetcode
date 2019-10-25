package com.leishb.leetcode.stack;

import org.junit.Assert;
import org.junit.Test;

import java.util.Stack;

/**
 * Created by me on 2019/10/18.
 */
public class _388_Longest_Absolute_File_Path {



    public int lengthLongestPath(String input) {
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        int max = 0;
        for (String s : input.split("\n")){
            int numOfTabs = s.lastIndexOf('\t')+1;
            while (numOfTabs+1 < stack.size()) {
                stack.pop();
            }
            int curLen = stack.peek() + s.length() - numOfTabs + 1;
            if (s.indexOf(".")!=-1){
                max = Math.max(max, curLen-1);
            }
            stack.push(curLen);
        }
        return max;
    }

    @Test
    public void test(){
        String input = "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext";
        String input2 = "a\n\tb1\n\t\tf1.txt\n\taaaaa\n\t\tf2.txt";
        Assert.assertTrue(lengthLongestPath(input)==20);
        Assert.assertTrue(lengthLongestPath(input2)==14);
    }
}
