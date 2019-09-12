package com.leishb.leetcode.tree;

/**
 * Created by me on 2019/9/10.
 */
public class _1008_Construct_Binary_Search_Tree_from_Preorder_Traversal {

    public TreeNode bstFromPreorder(int[] preorder) {
        return buildTree(preorder, 0, preorder.length-1);
    }


    private TreeNode buildTree(int[] preorder, int start, int end){
        if (start>end){
            return null;
        }
        TreeNode cur = new TreeNode(preorder[start]);
        int largeIndex = end+1;
        for (int i=start+1;i<=end;i++){
            if (preorder[i] > preorder[start]){
                largeIndex = i;
                break;
            }
        }
        cur.left = buildTree(preorder, start+1, largeIndex-1);
        cur.right = buildTree(preorder, largeIndex, end);
        return cur;
    }
}
