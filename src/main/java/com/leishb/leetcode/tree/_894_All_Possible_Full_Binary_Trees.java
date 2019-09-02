package com.leishb.leetcode.tree;

import java.util.*;

/**
 * Created by me on 2019/8/29.
 */
public class _894_All_Possible_Full_Binary_Trees {


    public List<TreeNode> allPossibleFBT2(int N) {
        if (N%2==0) return new ArrayList<>();
        if (N==1){
            return new ArrayList<>(Arrays.asList(new TreeNode(0)));
        }
        List<TreeNode> nodes = allPossibleFBT2(N-2);
        Set<String> set = new HashSet<>();
        List<TreeNode> ans = new ArrayList<>();
        for (TreeNode node : nodes){
            List<TreeNode> leaves = findLeaves(node);
            for (TreeNode leaf : leaves){
                leaf.left = new TreeNode(0);
                leaf.right = new TreeNode(0);
                TreeNode copy = copy(node);
                String s = serialize(copy);
                if (!set.contains(s)){
                    set.add(s);
                    ans.add(copy);
                }
                leaf.left=null;
                leaf.right=null;
            }
        }
        return ans;
    }


    private List<TreeNode> findLeaves(TreeNode node){
        List<TreeNode> leaves = new ArrayList<>();
        if (node.left==null && node.right==null){
            leaves.add(node);
            return leaves;
        }
        leaves.addAll(findLeaves(node.left));
        leaves.addAll(findLeaves(node.right));
        return leaves;
    }


    private TreeNode copy(TreeNode node){
        if (node==null){
            return null;
        }
        TreeNode root = new TreeNode(node.val);
        root.left = copy(node.left);
        root.right = copy(node.right);
        return root;
    }


    private String serialize(TreeNode node){
        if (node==null){
            return "N";
        }
        String s = node.val + ","+serialize(node.left) + serialize(node.right);
        return s;
    }


    Map<Integer, List<TreeNode>> memo = new HashMap<>();

    public List<TreeNode> allPossibleFBT(int N) {
        if (N%2==0) return new ArrayList<>();
        if (memo.containsKey(N)){
            return memo.get(N);
        }
        if (N==1){
            return new ArrayList<>(Arrays.asList(new TreeNode(0)));
        }
        List<TreeNode> ans = new ArrayList<>();
        for (int i=1;i<N;i+=2){
            List<TreeNode> leftNodes = allPossibleFBT(i);
            List<TreeNode> rightNodes = allPossibleFBT(N-1-i);
            for (TreeNode left : leftNodes){
                for (TreeNode right : rightNodes){
                    TreeNode node = new TreeNode(0);
                    node.left = left;
                    node.right = right;
                    ans.add(node);
                }
            }
        }
        memo.put(N, ans);
        return ans;
    }
}
