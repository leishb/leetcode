package com.leishb.leetcode.tree;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by me on 2020/1/20.
 */
public class _105_Construct_Binary_Tree {


    Map<Integer, Integer> map = new HashMap<>();


    /**
     * Accepted
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        for (int i=0;i<inorder.length;i++){
            map.put(inorder[i], i);
        }
        return buildTree(preorder, inorder, 0, preorder.length-1, 0, inorder.length-1);
    }

    public TreeNode buildTree(int[] preorder, int[] inorder, int preStart, int preEnd, int inStart, int inEnd) {
        if (preStart>preEnd || inStart > inEnd) return null;
        TreeNode root = new TreeNode(preorder[preStart]);
        int leftLen = 0;
        for (int i=inStart;i<=inEnd;i++){
            if (inorder[i]==root.val)break;
            leftLen++;
        }
        root.left = buildTree(preorder, inorder, preStart+1, preStart+leftLen, inStart, inStart+leftLen-1);
        root.right = buildTree(preorder, inorder, preStart+leftLen+1, preEnd, inStart+leftLen+1, inEnd);
        return root;
    }


    public TreeNode buildTree(int[] preorder, int preStart, int preEnd, int inStart, int inEnd) {
        if (preStart>preEnd || inStart > inEnd) return null;
        TreeNode root = new TreeNode(preorder[preStart]);
        int leftLen = map.get(root.val) - inStart;
        root.left = buildTree(preorder, preStart+1, preStart+leftLen, inStart, inStart+leftLen-1);
        root.right = buildTree(preorder, preStart+leftLen+1, preEnd, inStart+leftLen+1, inEnd);
        return root;
    }


}
