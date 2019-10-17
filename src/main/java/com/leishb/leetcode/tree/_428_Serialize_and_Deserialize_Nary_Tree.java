package com.leishb.leetcode.tree;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by me on 2019/10/12.
 */
public class _428_Serialize_and_Deserialize_Nary_Tree {


    class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val,List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    // Encodes a tree to a single string.
    public String serialize(Node root) {
        StringBuffer sb = new StringBuffer();
        serialize(root, sb, 0);
        return sb.toString();
    }


    public void serialize(Node root, StringBuffer sb, int level) {
        sb.append(root==null?"NULL":root.val);
        if (root==null) return;
        sb.append("(");
        for (Node child : root.children){
            serialize(child, sb, level+1);
        }
        sb.append(")");
        if (level!=0)sb.append("#-"+level+"-#");
    }

    // Decodes your encoded data to tree.
    public Node deserialize(String data) {
        if ("NULL".equals(data)) return null;
        return deserialize(data, 1);
    }

    private Node deserialize(String data, int level){
        int left = data.indexOf("(");
        if (left==-1){
            return "NULL".equals(data)?null:new Node(Integer.parseInt(data), new ArrayList<>());
        }
        Node root = new Node(Integer.parseInt(data.substring(0, left)), new ArrayList<>());
        for (String s : data.substring(left+1, data.lastIndexOf(")")).split("#-"+level+"-#")){
            if (s.isEmpty())continue;
            root.children.add(deserialize(s, level+1));
        }
        return root;
    }


    @Test
    public void test(){
        Node root = new Node(1, new ArrayList<>());
        root.children.add(new Node(3, new ArrayList<>()));
        root.children.add(new Node(2, new ArrayList<>()));
        root.children.add(new Node(4, new ArrayList<>()));
        root.children.get(0).children.add(new Node(5, new ArrayList<>()));
        root.children.get(0).children.add(new Node(6, new ArrayList<>()));

        String s = serialize(root);
        Node node = deserialize(s);

        System.out.println(s);
    }
}
