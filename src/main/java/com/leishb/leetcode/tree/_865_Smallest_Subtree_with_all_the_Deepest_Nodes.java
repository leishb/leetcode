package com.leishb.leetcode.tree;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by me on 2019/8/21.
 */
public class _865_Smallest_Subtree_with_all_the_Deepest_Nodes {


    /**
     * Accepted
     * @param root
     * @return
     */
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        Map<TreeNode, TreeNode> parentMap = new HashMap<>();
        Map<TreeNode, Integer> depthMap = new HashMap<>();
        deepestNodes(parentMap, depthMap, root, null, 0);
        Set<TreeNode> deepestNodes = new HashSet<>();
        TreeNode cur = null;
        for (TreeNode node : depthMap.keySet()){
            if (depthMap.get(node)==maxDepth){
                deepestNodes.add(node);
                cur = node;
            }
        }
        while (cur!=null){
            if (containsAllDeepestNodes(cur, deepestNodes)==deepestNodes.size()){
                return cur;
            }
            cur = parentMap.get(cur);
        }
        return cur;
    }

    int maxDepth = 0;

    private void deepestNodes(Map<TreeNode, TreeNode> parentMap, Map<TreeNode, Integer> depthMap, TreeNode root, TreeNode parent,  int depth){
        if (root!=null){
            parentMap.put(root, parent);
            depthMap.put(root, depth);
            maxDepth = Math.max(maxDepth, depth);
            deepestNodes(parentMap, depthMap, root.left, root, depth+1);
            deepestNodes(parentMap, depthMap, root.right, root, depth+1);
        }
    }


    private int containsAllDeepestNodes(TreeNode cur, Set<TreeNode> nodes){
        if (cur!=null){
            return (nodes.contains(cur)?1:0) + containsAllDeepestNodes(cur.left, nodes) +containsAllDeepestNodes(cur.right, nodes);
        }
        return 0;
    }


    TreeNode cur = null;
    int deepestLevel = 0;


    private int dfs(TreeNode root, int level){
        if (root==null) return level;
        int leftLevel = dfs(root.left, level+1);
        int rightLevel = dfs(root.right, level+1);

        int curLevel = Math.max(leftLevel, rightLevel);
        deepestLevel = Math.max(deepestLevel, curLevel);
        if (leftLevel==deepestLevel && rightLevel == deepestLevel){
            cur = root;
        }
        return curLevel;
    }
}
