package com.leishb.leetcode.array;

/**
 * Created by me on 2017/11/7.
 */
public class ConstructBinaryTree {


      class TreeNode {
          int val;
          TreeNode left;
          TreeNode right;
          TreeNode(int x) { val = x; }
      }

    /**
     *
     * Accepted
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTree(preorder, inorder, 0, 0 , inorder.length-1);
    }


    private TreeNode buildTree(int[] preorder, int[] inorder, int preStart,  int inStart, int inEnd){
        if (preStart>= preorder.length || inStart > inEnd){
            return null;
        }
        TreeNode root = new TreeNode(preorder[preStart]);
        int inIndex = 0;
        for (int i=inStart;i<=inEnd;i++){
            if (inorder[i]==preorder[preStart]){
                inIndex=i;
            }
        }
        root.left = buildTree(preorder, inorder, preStart+1, inStart,  inIndex-1);
        root.right = buildTree(preorder, inorder, preStart+inIndex-inStart +1, inIndex+1, inEnd);
        return root;
    }


    /**
     * Accepted
     * @param inorder
     * @param postorder
     * @return
     */
    public TreeNode buildTree2(int[] inorder, int[] postorder) {
        return helper(inorder,postorder, 0, inorder.length-1, 0, postorder.length-1);
    }


    private TreeNode helper(int[] inorder, int[] postorder, int inStart, int inEnd, int postStart, int postEnd){
        if (inStart > inEnd || postStart > postEnd){
            return null;
        }
        TreeNode root = new TreeNode(postorder[postEnd]);
        int inIndex = 0;
        for (int i=inStart;i<=inEnd;i++){
            if (inorder[i]==root.val){
                inIndex = i;
                break;
            }
        }
        root.left = helper(inorder, postorder, inStart, inIndex-1, postStart, postStart + inIndex - inStart -1);
        root.right = helper(inorder, postorder, inIndex+1, inEnd, postStart + inIndex - inStart , postEnd-1);
        return root;
    }
}
