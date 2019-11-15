package com.leishb.leetcode.tree;

import java.util.*;

/**
 * Created by me on 2019/7/5.
 */
public class _297_Serialize_and_Deserialize_Binary_Tree_2 {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root==null) return "";
        StringBuffer sb = new StringBuffer();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            TreeNode cur = queue.poll();
            if (cur==null){
                sb.append("n,");
                continue;
            }
            sb.append(cur.val).append(",");
            queue.offer(cur.left);
            queue.offer(cur.right);
        }
        return sb.substring(0, sb.length()-1);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.equals("")){
            return null;
        }
        String[] ss = data.split(",");
        TreeNode root = new TreeNode(Integer.parseInt(ss[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        for (int i=1;i<ss.length;){
            TreeNode cur = queue.poll();
            if (!ss[i].equals("n")){
                cur.left = new TreeNode(Integer.parseInt(ss[i]));
                queue.offer(cur.left);
            }
            if (i+1< ss.length && !ss[i+1].equals("n")){
                cur.right = new TreeNode(Integer.parseInt(ss[i+1]));
                queue.offer(cur.right);
            }
            i+=2;
        }
        return root;
    }


    public static void main(String[] args){
        TreeNode root = new TreeNode(-1);
        root.left=new TreeNode(0);
        root.right=new TreeNode(1);
        root.left.right=new TreeNode(2);
        _297_Serialize_and_Deserialize_Binary_Tree_2 ss = new _297_Serialize_and_Deserialize_Binary_Tree_2();
        System.out.println(ss.serialize(root));
        ss.deserialize(ss.serialize(root));
    }
}
