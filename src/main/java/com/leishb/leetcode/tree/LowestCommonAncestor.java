package com.leishb.leetcode.tree;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by me on 2019/5/16.
 */
public class LowestCommonAncestor {


    /**
     * Accepted
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        Map<TreeNode, TreeNode> map = new HashMap<>();
        inOrder(null, root, map);
        Set<TreeNode> pSet = new HashSet<>();
        TreeNode node = p;
        while (node!=null){
            pSet.add(node);
            node = map.get(node);
        }
        node = q;
        while (node!=null){
            if (pSet.contains(node)){
                return node;
            }
            node = map.get(node);
        }
        return null;
    }


    private void inOrder(TreeNode parent , TreeNode root, Map<TreeNode, TreeNode> map){
        if (root!=null){
            inOrder(root, root.left, map);
            if (parent!=null){
                map.put(root, parent);
            }
            inOrder(root, root.right, map);
        }
    }


    /**
     * Accepted
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestorOfBST(TreeNode root, TreeNode p, TreeNode q) {
        if (root==null){
            return root;
        }
        if (p.val < root.val && q.val < root.val){
            return lowestCommonAncestorOfBST(root.left, p, q);
        }else if(p.val > root.val && q.val > root.val){
            return lowestCommonAncestorOfBST(root.right, p, q);
        }else {
            return root;
        }
    }
}
