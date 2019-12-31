package com.leishb.leetcode.tree;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by me on 2019/11/21.
 */
public class _979_Distribute_Coins_in_Binary_Tree {


    /**
     * Accepted
     * @param root
     * @return
     */
    public int distributeCoins(TreeNode root) {
        Map<TreeNode, Integer> nodes = new HashMap<>();
        Map<TreeNode, Integer> coins = new HashMap<>();
        dfs(root, nodes, coins);
        return dfs2(root, nodes, coins);
    }

    private int dfs2(TreeNode root, Map<TreeNode, Integer> nodes, Map<TreeNode, Integer> coins){
        if (root==null) return 0;
        int ans = 0;
        if (root.left!=null){
            int move = coins.get(root.left)-nodes.get(root.left);
            ans += Math.abs(move);
        }
        if (root.right!=null){
            int move = coins.get(root.right)-nodes.get(root.right);
            ans += Math.abs(move);
        }
        int left = dfs2(root.left, nodes, coins);
        int right = dfs2(root.right, nodes, coins);
        return ans + left + right;
    }

    private int[] dfs(TreeNode root, Map<TreeNode, Integer> nodes, Map<TreeNode, Integer> coins){
        if (root==null) return new int[]{0,0};
        int[] left = dfs(root.left, nodes, coins);
        int[] right = dfs(root.right, nodes, coins);
        int[] ans = new int[]{left[0]+right[0]+root.val, left[1]+right[1]+1};
        nodes.put(root, ans[1]);
        coins.put(root, ans[0]);
        return ans;
    }

    int ans = 0;

    private int[] dfs(TreeNode root){
        if (root==null) return new int[]{0,0};
        int[] left = dfs(root.left);
        int[] right = dfs(root.right);
        int[] res = new int[]{left[0]+right[0]+root.val, left[1]+right[1]+1};
        ans += Math.abs(left[0]-left[1]) + Math.abs(right[0]-right[1]);
        return res;
    }


    /**
     * return the number of coins given to the parent.
     * @param root
     * @return
     */
    private int dfs_(TreeNode root){
        if (root==null) return 0;
        int left = dfs_(root.left);
        int right = dfs_(root.right);
        ans += Math.abs(left) + Math.abs(right);
        return root.val + left + right -1;
    }
}
