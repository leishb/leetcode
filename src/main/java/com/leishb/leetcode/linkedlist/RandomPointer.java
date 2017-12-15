package com.leishb.leetcode.linkedlist;

import com.leishb.leetcode.tag.DFS;
import org.junit.Test;

import java.util.*;

/**
 * Created by me on 2017/12/12.
 */
@DFS
public class RandomPointer {


    @Test
    public void test(){
        RandomListNode head = new RandomListNode(-1);
        head.next = new RandomListNode(-1);
//        head.random = head.next;
        copyRandomList(head);
    }


    /**
     * https://leetcode.com/problems/copy-list-with-random-pointer/description/
     * Accepted
     * @param head
     * @return
     */
    public RandomListNode copyRandomList(RandomListNode head) {
        if(head==null){
            return null;
        }
        List<RandomListNode> list1 = new ArrayList();
        List<RandomListNode> list2 = new ArrayList();
        RandomListNode cur = head;
        while(cur!=null){
            list1.add(cur);
            list2.add(new RandomListNode(cur.label));
            cur = cur.next;
        }
        for(int i=1;i<list2.size();i++){
            list2.get(i-1).next = list2.get(i);
        }
        Map<RandomListNode, Integer> nodeMap = new HashMap<>();
        for (int i=0;i<list1.size();i++){
            nodeMap.put(list1.get(i), i);
        }
        Map<Integer, Integer> map = new HashMap();
        for(int i=0;i<list1.size();i++){
            RandomListNode n = list1.get(i);
            if (nodeMap.containsKey(n.random)){
                map.put(i, nodeMap.get(n.random));
            }
        }
        for(int i=0;i<list2.size();i++){
            RandomListNode n = list2.get(i);
            if(map.containsKey(i)){
                n.random = list2.get(map.get(i));
            }
        }
        return list2.get(0);
    }


    /**
     * https://leetcode.com/problems/copy-list-with-random-pointer/description/
     * Accepted
     * @param head
     * @return
     */
    public RandomListNode copyRandomList2(RandomListNode head) {
        if(head==null){
            return null;
        }
        Map<RandomListNode, RandomListNode> map = new HashMap();
        RandomListNode node = head;
        while(node != null){
            map.put(node, new RandomListNode(node.label));
            node = node.next;
        }

        node = head;

        while(node != null){
            if(node.next != null){
                map.get(node).next = map.get(node.next);
            }
            if(node.random != null){
                map.get(node).random = map.get(node.random);
            }
            node = node.next;
        }

        return map.get(head);
    }

     class RandomListNode {
         int label;
         RandomListNode next, random;
         RandomListNode(int x) { this.label = x; }
     }


    /**
     * https://leetcode.com/problems/clone-graph/description/
     * Accepted
     * @param node
     * @return
     */
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null){
            return null;
        }
        Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap();
        Queue<UndirectedGraphNode> queue = new LinkedList<>();
        Set<UndirectedGraphNode> visit = new HashSet<>();
        map.put(node, new UndirectedGraphNode(node.label));
        visit.add(node);
        queue.add(node);
        while (!queue.isEmpty()){
            UndirectedGraphNode nn = queue.poll();
            List<UndirectedGraphNode> neighours = nn.neighbors;
            for (UndirectedGraphNode ugn : neighours){
                if (!visit.contains(ugn)){
                    map.put(ugn, new UndirectedGraphNode(ugn.label));
                    visit.add(ugn);
                    queue.add(ugn);
                }
            }
        }
        for (UndirectedGraphNode key : map.keySet()){
            for (UndirectedGraphNode ugn : key.neighbors){
                map.get(key).neighbors.add(map.get(ugn));
            }
        }
        return map.get(node);
    }



    public UndirectedGraphNode cloneGraph2(UndirectedGraphNode node) {
        if (node == null) {
            return null;
        }
        Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap();
        dfs(node, map);
        for (UndirectedGraphNode key : map.keySet()){
            for (UndirectedGraphNode ugn : key.neighbors){
                map.get(key).neighbors.add(map.get(ugn));
            }
        }
        return map.get(node);
    }


    private void dfs(UndirectedGraphNode node ,Map<UndirectedGraphNode, UndirectedGraphNode> map ){
        map.put(node, new UndirectedGraphNode(node.label));
        List<UndirectedGraphNode> neighours = node.neighbors;
        for (UndirectedGraphNode ugn : neighours){
            if (!map.containsKey(ugn)){
                dfs(ugn, map);
            }
        }
    }


    class UndirectedGraphNode {
        int label;
        List<UndirectedGraphNode> neighbors;
        UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
    }
}
