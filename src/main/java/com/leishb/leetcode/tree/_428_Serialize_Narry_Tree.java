package com.leishb.leetcode.tree;

import java.util.*;

/**
 * Created by me on 2019/10/12.
 */
public class _428_Serialize_Narry_Tree {

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
        List<String> list = new ArrayList<>();
        serialize(root, list);
        return String.join(",", list);
    }


    public void serialize(Node root, List<String> list) {
        if (root==null){
            return;
        }
        list.add(String.valueOf(root.val));
        list.add(String.valueOf(root.children.size()));
        for (Node child : root.children){
            serialize(child, list);
        }
    }

    // Decodes your encoded data to tree.
    public Node deserialize(String data) {
        if (data.length()==0) return null;
        Queue<String> queue = new LinkedList<>(Arrays.asList(data.split(",")));
        return deserialize(queue);
    }

    public Node deserialize( Queue<String> queue) {
        if (queue.isEmpty()) return null;
        Node root = new Node(Integer.parseInt(queue.poll()), new ArrayList<>());
        int size = Integer.parseInt(queue.poll());
        for (int i=0;i<size;i++){
            root.children.add(deserialize(queue));
        }
        return root;
    }
}
