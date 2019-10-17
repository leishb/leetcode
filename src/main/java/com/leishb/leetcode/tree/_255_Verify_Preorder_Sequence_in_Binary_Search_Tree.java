package com.leishb.leetcode.tree;

/**
 * Created by me on 2019/10/12.
 */
public class _255_Verify_Preorder_Sequence_in_Binary_Search_Tree {

    public boolean verifyPreorder(int[] preorder) {
        return helper(preorder, 0, preorder.length-1, Integer.MAX_VALUE, Integer.MIN_VALUE);
    }

    private boolean helper(int[] preorder, int start, int end, int upper, int lower){
        if (start > end) return true;
        int i = start;
        for (;i<=end;i++){
            if (preorder[i] < lower) return false;
            if (preorder[i] > upper) return false;
            if (preorder[i] > preorder[start]){
                break;
            }
        }
        return helper(preorder, start+1, i-1, preorder[start], lower) && helper(preorder, i, end, upper, preorder[start]);
    }

    private boolean helper(int[] preorder, int start, int end){
        if (start > end) return true;
        int i = start;
        for (;i<=end;i++){
            if (preorder[i] > preorder[start]){
                break;
            }
        }
        for (int j = i;j<=end;j++){
            if (preorder[j] < preorder[start]){
                return false;
            }
        }
        return helper(preorder, start+1, i-1) && helper(preorder, i, end);
    }
}
