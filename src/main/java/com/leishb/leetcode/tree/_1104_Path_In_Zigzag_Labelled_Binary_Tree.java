package com.leishb.leetcode.tree;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by me on 2019/9/17.
 */
public class _1104_Path_In_Zigzag_Labelled_Binary_Tree {

    public List<Integer> pathInZigZagTree(int label) {
        LinkedList<Integer> ans = new LinkedList<>();
        int parent = label;
        ans.addFirst(parent);
        while (parent!=1){
            int depth = (int) (Math.log(parent)/Math.log(2));
            int offset = (int) (Math.pow(2, depth+1) -1 - parent);
            parent = (int) ((Math.pow(2, depth)+offset)/2);
            ans.addFirst(parent);
        }
        return ans;
    }
}
