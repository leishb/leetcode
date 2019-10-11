package com.leishb.leetcode.dfs;

import com.leishb.leetcode.design.NestedInteger;

import java.util.List;

/**
 * Created by me on 2019/10/8.
 */
public class _339_Nested_List_Weight_Sum {

    public int depthSum(List<NestedInteger> nestedList) {
        return dfs(nestedList, 1);
    }


    private int dfs(List<NestedInteger> nestedList , int depth){
        int sum = 0;
        for (NestedInteger ni : nestedList){
            if (ni.isInteger()){
                sum += ni.getInteger() * depth;
            }else {
                sum += dfs(ni.getList(), depth+1);
            }
        }
        return sum;
    }

    public int depthSumInverse(List<NestedInteger> nestedList) {
        int depth = 0;
        for (NestedInteger ni : nestedList){
            depth = Math.max(depth, getDepth(ni));
        }
        return depthSumInverse(nestedList, depth);
    }


    private int depthSumInverse(List<NestedInteger> nestedList, int depth){
        int sum = 0;
        for (NestedInteger ni : nestedList){
            if (ni.isInteger()){
                sum += ni.getInteger() * depth;
            }else {
                sum += depthSumInverse(ni.getList(), depth-1);
            }
        }
        return sum;
    }


    private int getDepth(NestedInteger ni){
        if (ni.isInteger()){
            return 1;
        }
        int depth = 0;
        for (NestedInteger sub : ni.getList()){
            depth = Math.max(depth, getDepth(sub)+1);
        }
        return depth;
    }
}
