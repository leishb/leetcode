package com.leishb.leetcode.tree;

/**
 * Created by me on 2019/7/4.
 */
public class _449_Serialize_and_Deserialize_BST {

    public static String spliter = "'";

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuffer sb = new StringBuffer();
        preOrderTrav(root, sb);
        return sb.toString();
    }

    private void preOrderTrav(TreeNode root, StringBuffer sb){
        if(root==null){
            return;
        }
        sb.append(root.val).append(spliter);
        preOrderTrav(root.left, sb);
        preOrderTrav(root.right, sb);
    }



    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if ("".equals(data)){
            return null;
        }
        String[] ss = data.split(spliter);
        int[] arr = new int[ss.length];
        for (int i=0;i<arr.length;i++){
            arr[i] = Integer.parseInt(ss[i]);
        }
        return buildTree(arr, 0, arr.length-1);
    }


    private TreeNode buildTree(int[] arr, int start, int end){
        if (start>end){
            return null;
        }
        TreeNode root = new TreeNode(arr[start]);
        int leftLen = 0;
        for (int i=start+1;i<=end;i++){
            if (arr[i] > root.val){
                break;
            }
            leftLen++;
        }
        root.left = buildTree(arr, start+1, start+leftLen);
        root.right = buildTree(arr, start+leftLen+1, end);
        return root;
    }



    private TreeNode reBuildTree(int[] pre, int preStart, int preEnd,  int[]in, int inStart, int inEnd){
        if (inStart>inEnd || preStart > preEnd){
            return null;
        }
        TreeNode root = new TreeNode(pre[preStart]);
        int leftLen = 0;
        for (int i=inStart;i<=inEnd;i++){
            if (in[i] == pre[preStart]){
                break;
            }
            leftLen++;
        }
        root.left = reBuildTree(pre, preStart+1, preStart+leftLen, in, inStart, inStart+leftLen-1);
        root.right = reBuildTree(pre, preStart+leftLen+1, preEnd, in, inStart+leftLen+1, inEnd);
        return root;
    }


    public static void main(String[] args){
        TreeNode root = new TreeNode(3);
        root.left=new TreeNode(1);
        root.right=new TreeNode(4);
        root.left.right=new TreeNode(2);
        _449_Serialize_and_Deserialize_BST ss = new _449_Serialize_and_Deserialize_BST();
        System.out.println(ss.serialize(root));
        ss.deserialize(ss.serialize(root));
        ss.deserialize(ss.serialize(null));
    }
}
