package com.leishb.leetcode.tree;

import org.junit.Test;

/**
 * Created by me on 2019/8/23.
 */
public class _889_Construct_Binary_Tree {


    /**
     * Accepted
     * @param pre
     * @param post
     * @return
     */
    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        TreeNode root = buildTree(pre, post, 0, pre.length-1, 0, post.length-1);
        return root;
    }


    private TreeNode buildTree(int[] pre, int[] post, int preStart, int preEnd, int postStart, int postEnd){
        if (preStart>preEnd || postStart>postEnd){
            return null;
        }
        TreeNode root = new TreeNode(pre[preStart]);
        if (preStart==preEnd){
            return root;
        }
        int leftLen = 0;
        for (int i=postStart;i<=postEnd;i++){
            leftLen++;
            if (post[i]==pre[preStart+1]){
                break;
            }
        }
        root.left = buildTree(pre, post, preStart+1, preStart+leftLen, postStart, postStart+leftLen-1);
        root.right = buildTree(pre, post, preStart+leftLen+1, preEnd, postStart+leftLen, postEnd-1);
        return root;
    }


    @Test
    public void test(){
        constructFromPrePost(new int[]{}, new int[]{});
        constructFromPrePost(new int[]{1,2,4,5,3,6,7}, new int[]{4,5,2,6,7,3,1});
        constructFromPrePost(new int[]{1,2,4,5,3,6}, new int[]{4,5,2,6,3,1});
        constructFromPrePost(new int[]{1}, new int[]{1});
    }
}
