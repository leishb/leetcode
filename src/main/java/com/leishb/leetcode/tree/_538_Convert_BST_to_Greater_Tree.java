package com.leishb.leetcode.tree;

import org.junit.Test;

/**
 * Created by me on 2019/6/27.
 */
public class _538_Convert_BST_to_Greater_Tree {




    int pv = 0;

    /**
     * Accepted
     * @param root
     * @return
     */
    public TreeNode convertBST(TreeNode root) {
        if (root==null){
            return root;
        }
        convertBST(root.right);
        System.out.print(root.val + "->");
        root.val += pv;
        pv = root.val;
        convertBST(root.left);
        return root;
    }



    @Test
    public void test(){
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(2);
        root.right = new TreeNode(13);

        convertBST(root);
    }
}
