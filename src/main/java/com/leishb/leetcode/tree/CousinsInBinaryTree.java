package com.leishb.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by me on 2019/4/25.
 */
public class CousinsInBinaryTree {


    int xDepth = -1;
    int yDepth = -2;
    int xParent = 0;
    int yParent = 0;


    /**
     * Accepted
     * @param root
     * @param x
     * @param y
     * @return
     */
    public boolean isCousins(TreeNode root, int x, int y){
        dfs(null, root, x, y, 0);
        return xDepth==yDepth && xParent!=yParent;
    }


    public void dfs(TreeNode parent, TreeNode root, int x, int y, int depth){
        if (root==null){
            return;
        }
        if (root.val==x){
            xDepth = depth;
            if (parent!=null){
                xParent = parent.val;
            }
        }
        if (root.val==y){
            yDepth = depth;
            if (parent!=null){
                yParent = parent.val;
            }
        }
        dfs(root,root.left,x,y,depth+1);
        dfs(root,root.right,x,y,depth+1);
    }


    /**
     * Accepted
     * @param root
     * @param x
     * @param y
     * @return
     */
    public boolean isCousins2(TreeNode root, int x, int y){
        if (root==null){
            return false;
        }
        int depth=0;
        int xd = -1;
        int yd = -2;
        Queue<TreeNode> queue = new LinkedList<>();
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i=0;i<size;i++){
                TreeNode node = queue.poll();
                if (node.left!=null){
                    queue.offer(node.left);
                }
                if (node.right!=null){
                    queue.offer(node.right);
                }
                if (node.val==x){
                    xd = depth;
                }
                if (node.val==y){
                    yd = depth;
                }
                if (node.left!=null && node.right!=null){
                    if (node.left.val==x && node.right.val==y){
                        return false;
                    }
                    if (node.left.val==y && node.right.val==x){
                        return false;
                    }
                }
            }
            depth++;
        }
        return xd == yd;
    }
}
