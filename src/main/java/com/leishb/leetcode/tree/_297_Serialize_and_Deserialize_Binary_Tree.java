package com.leishb.leetcode.tree;

import java.util.*;

/**
 * Created by me on 2019/7/5.
 */
public class _297_Serialize_and_Deserialize_Binary_Tree {


    public static String SPLITER = "#";
    public static String NULL = "NULL";

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root==null){
            return "";
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        StringBuffer ss = new StringBuffer(root.val+"");
        while (!queue.isEmpty()){
            int size = queue.size();
            boolean hasNextLevel = false;
            ss.append(SPLITER);
            for (int i=0;i<size;i++){
                TreeNode node = queue.poll();
                if (node!=null){
                    queue.offer(node.left);
                    queue.offer(node.right);
                    if (node.left!=null || node.right!=null){
                        hasNextLevel = true;
                    }
                    ss.append(node.left!=null?node.left.val:NULL).append(",");
                    ss.append(node.right!=null?node.right.val:NULL).append(",");
                }
            }
            if (!hasNextLevel){
                break;
            }
        }
        int lastSpliter = ss.lastIndexOf(SPLITER);
        return ss.substring(0, lastSpliter);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.equals("")){
            return null;
        }
        String[] levels = data.split(SPLITER);
        Queue<Queue<String>> queues = new LinkedList<>();
        for (String level : levels){
            queues.offer(new LinkedList<>(Arrays.asList(level.split(","))));
        }
        String rootVal = queues.poll().poll();
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode root = new TreeNode(Integer.parseInt(rootVal));
        queue.offer(root);
        while (!queue.isEmpty() && !queues.isEmpty()){
            int size = queue.size();
            Queue<String> levelQueue = queues.poll();
            for (int i=0;i<size;i++){
                TreeNode node = queue.poll();
                if (node!=null) {
                    String leftValue = levelQueue.poll();
                    String rightValue = levelQueue.poll();
                    node.left = NULL.equals(leftValue)?null:new TreeNode(Integer.parseInt(leftValue));
                    node.right =  NULL.equals(rightValue)?null:new TreeNode(Integer.parseInt(rightValue));
                    queue.offer(node.left);
                    queue.offer(node.right);
                }
            }
        }
        return root;
    }


    // Encodes a tree to a single string.
    public String serialize2(TreeNode root) {
        StringBuffer sb = new StringBuffer();
        preOrderTrav(root, sb);
        return sb.toString();
    }

    private void preOrderTrav(TreeNode root, StringBuffer sb){
        if(root==null){
            sb.append(NULL).append(SPLITER);
            return;
        }
        sb.append(root.val).append(SPLITER);
        preOrderTrav(root.left, sb);
        preOrderTrav(root.right, sb);
    }



    // Decodes your encoded data to tree.
    public TreeNode deserialize2(String data) {
        LinkedList<String> list = new LinkedList();
        list.addAll(Arrays.asList(data.split(SPLITER)));
        return buildTree(list);
    }


    private TreeNode buildTree(LinkedList<String> list){
        String val = list.remove();
        if(val.equals(NULL)){
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(val));
        root.left = buildTree(list);
        root.right = buildTree(list);
        return root;
    }


    public static void main(String[] args){
        TreeNode root = new TreeNode(-1);
        root.left=new TreeNode(0);
        root.right=new TreeNode(1);
        root.left.right=new TreeNode(2);
        _297_Serialize_and_Deserialize_Binary_Tree ss = new _297_Serialize_and_Deserialize_Binary_Tree();
        System.out.println(ss.serialize(root));
        ss.deserialize(ss.serialize(root));
    }
}
