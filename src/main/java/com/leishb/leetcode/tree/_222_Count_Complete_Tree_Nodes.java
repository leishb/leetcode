package com.leishb.leetcode.tree;

/**
 * Created by me on 2019/11/13.
 */
public class _222_Count_Complete_Tree_Nodes {


    /**
     * Accepted
     * @param root
     * @return
     */
    public int countNodes(TreeNode root) {
        return helper(root, -1, -1);
    }


    private int helper(TreeNode root, int ld, int rd){
        if (root==null) return 0;
        if (ld==rd && ld!=-1){
            return (int) (Math.pow(2, ld)-1);
        }
        if (ld==-1){
            ld = 0;
            TreeNode cur = root;
            while (cur!=null) {
                cur = cur.left;
                ld+=1;
            }
        }
        if (rd==-1){
            rd = 0;
            TreeNode cur = root;
            while (cur!=null){
                cur = cur.right;
                rd++;
            }
        }
        return helper(root.left, ld-1, -1) + helper(root.right, -1, rd-1) + 1;
    }
}
