package com.leishb.leetcode.tree;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by me on 2019/9/2.
 */
public class _919_Complete_Binary_Tree_Inserter {

    private TreeNode root;

    private Map<Integer, TreeNode> map ;

    private int lastKey = 0;

    public _919_Complete_Binary_Tree_Inserter(TreeNode root) {
        this.root = root;
        map = new HashMap<>();
        transf(root, 0);
    }


    private void transf(TreeNode node , int k){
        if (node!=null){
            map.put(k, node);
            transf(node.left, k*2+1);
            transf(node.right, k*2+2);
            lastKey = Math.max(k, lastKey);
        }
    }

    public int insert(int v) {
        if (lastKey==0){
            root.left = new TreeNode(v);
            map.put(1, root.left);
            lastKey = 1;
            return root.val;
        }
        int p = (lastKey-1)/2;
        TreeNode pNode = map.get(p);
        if(pNode.right==null){
            pNode.right = new TreeNode(v);
            map.put(2*p+2, pNode.right);
            lastKey = 2*p+2;
        }else {
            p = p+1;
            pNode=map.get(p);
            pNode.left = new TreeNode(v);
            map.put(2*p+1, pNode.left);
            lastKey = 2*p+1;
        }
        return pNode.val;
    }

    public TreeNode get_root() {
        return root;
    }


    public static void main(String[] args){
        _919_Complete_Binary_Tree_Inserter inserter = new _919_Complete_Binary_Tree_Inserter(new TreeNode(1));
        inserter.insert(2);
        inserter.get_root();
    }
}
