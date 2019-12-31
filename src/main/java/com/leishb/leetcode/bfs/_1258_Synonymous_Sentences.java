package com.leishb.leetcode.bfs;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by me on 2019/12/5.
 */
public class _1258_Synonymous_Sentences {


    /**
     * Accepted
     * @param synonyms
     * @param text
     * @return
     */
    public List<String> generateSentences(List<List<String>> synonyms, String text) {
        Map<String, List<String>> graph = new HashMap<>();
        for (List<String> list : synonyms){
            String w1 = list.get(0), w2 = list.get(1);
            graph.putIfAbsent(w1, new ArrayList<>());
            graph.putIfAbsent(w2, new ArrayList<>());
            graph.get(w1).add(w2);
            graph.get(w2).add(w1);
        }
        List<String> ans = new ArrayList<>();
        Set<String> set = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.offer(text);
        set.add(text);
        while (!queue.isEmpty()){
            String cur = queue.poll();
            ans.add(cur);
            String[] words = cur.split(" ");
            for (int i=0;i<words.length;i++){
                String w = words[i];
                if (!graph.containsKey(w)) continue;
                for (String next : graph.get(w)){
                    words[i] = next;
                    String s = Arrays.stream(words).collect(Collectors.joining(" "));
                    if (!set.contains(s)){
                        set.add(s);
                        queue.offer(s);
                    }
                }
                words[i] =w;
            }
        }
        Collections.sort(ans);
        return ans;
    }
}
