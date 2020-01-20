package com.leishb.other;

/**
 * Created by me on 2020/1/16.
 */
public class Find_nth_node_in_inorder_traversal {


    class TreeNode {
        TreeNode left;
        TreeNode right;
        int val;
        int childrens;
    }


    public int findKth(TreeNode root, int K){
        if (root==null) return 0;
        if (root.left==null){
            if (K==0) return root.val;
            return findKth(root.right, K-1);
        }
        int leftChilds = root.left.childrens;
        if (leftChilds+1 >= K+1){
            return findKth(root.left, K);
        }else if (K+1-leftChilds+1==1){
            return root.val;
        }else {
            return findKth(root.right, K-leftChilds-2);
        }
    }
}
