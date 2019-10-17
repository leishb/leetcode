package com.leishb.leetcode.dfs;

import org.junit.Test;

import java.util.*;

/**
 * Created by me on 2019/10/12.
 */
public class _332_Reconstruct_Itinerary {


    LinkedList<String> ans = new LinkedList<>();
    Map<String, PriorityQueue<String>> map = new HashMap<>();

    public List<String> findItinerary(List<List<String>> tickets) {
        for (List<String> ticket : tickets){
            map.putIfAbsent(ticket.get(0), new PriorityQueue<>());
            map.get(ticket.get(0)).offer(ticket.get(1));
        }
        dfs("JFK");
        return ans;
    }

    private void dfs(String start){
        while (map.containsKey(start) && !map.get(start).isEmpty()){
            dfs(map.get(start).poll());
        }
        ans.addFirst(start);
    }


    @Test
    public void test(){
        List<List<String>> lists = new ArrayList<>();
        lists.add(Arrays.asList("JFK","SFO"));
        lists.add(Arrays.asList("JFK","ATL"));
        lists.add(Arrays.asList("SFO","ATL"));
        lists.add(Arrays.asList("ATL","JFK"));
        lists.add(Arrays.asList("ATL","SFO"));
        System.out.println(findItinerary(lists));
    }
}
