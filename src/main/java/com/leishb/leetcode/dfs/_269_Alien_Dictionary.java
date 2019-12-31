package com.leishb.leetcode.dfs;

import org.junit.Test;

import java.util.*;

/**
 * Created by me on 2019/9/24.
 */
public class _269_Alien_Dictionary {


    public String alienOrder(String[] words) {
        int[][] orders = new int[26][26];
        Set<Character> chars = new HashSet<>();
        for (String word : words){
            for (char c : word.toCharArray()){
                chars.add(c);
            }
        }
        for (int i=1;i<words.length;i++){
            if (!compare(words[i-1], words[i], orders)){
                return "";
            }
        }
        StringBuffer sb = new StringBuffer();
        boolean[] visited = new boolean[26];
        boolean[] onStack = new boolean[26];
        for (char c : chars){
            if ( !visited[c-'a']){
                if (!dfs(orders, c-'a', sb, visited, onStack)){
                    return "";
                }
            }
        }
        return sb.toString();
    }

    private boolean dfs(int[][] orders, int cur, StringBuffer sb, boolean[] visited, boolean[] onStack){
        onStack[cur] = true;
        visited[cur] = true;
        for (int i=0;i<26;i++){
            if (orders[i][cur]==-1){
                if (!visited[i]){
                    if (!dfs(orders, i, sb, visited, onStack)){
                        return false;
                    }
                }else if (onStack[i]){
                    return false;
                }
            }
        }
        onStack[cur] =false;
        sb.append((char)('a'+cur));
        return true;
    }


    private boolean compare(String w1, String w2, int[][] orders){
        int len = Math.min(w1.length(), w2.length());
        for (int i=0;i<len;i++){
            if (w1.charAt(i) != w2.charAt(i)){
                if (orders[w1.charAt(i)-'a'][w2.charAt(i)-'a']==1 || orders[w2.charAt(i)-'a'][w1.charAt(i)-'a']==-1) return false;
                orders[w1.charAt(i)-'a'][w2.charAt(i)-'a'] = -1;
                orders[w2.charAt(i)-'a'][w1.charAt(i)-'a'] = 1;
                return true;
            }
        }
        return w2.length()-w1.length()>=0;
    }


    public String alienOrder2(String[] words) {
        List<Integer>[] adj = new List[26];
        for (int i=0;i<26;i++){
            adj[i] = new ArrayList<>();
        }
        buildGraph(words, adj);
        int[] dict = new int[26];
        for (String w : words){
            for (char c : w.toCharArray()){
                dict[c-'a'] = 1;
            }
        }
        boolean[] visited = new boolean[26];
        boolean[] onStack = new boolean[26];
        StringBuffer sb = new StringBuffer();
        for (int i=0;i<26;i++){
            if (dict[i]==0) continue;
            if (!visited[i]){
                if (!dfs(adj, i, sb, visited, onStack)){
                    return "";
                }
            }
        }
        return sb.toString();
    }

    private boolean dfs(List<Integer>[] adj, int cur,StringBuffer sb, boolean[] visited, boolean[] onStack){
        visited[cur] = true;
        onStack[cur] = true;
        for (int i : adj[cur]){
            if (onStack[i]){
                return false;
            }
            if (!visited[i]){
                if (!dfs(adj, i, sb, visited, onStack)){
                    return false;
                }
            }
        }
        onStack[cur] = false;
        sb.append((char)('a'+cur));
        return true;
    }

    private void buildGraph(String[] words, List<Integer>[] adj){
        for (int i=1;i<words.length;i++){
            int len = Math.min(words[i-1].length(), words[i].length());
            for (int j=0;j<len;j++){
                if (words[i-1].charAt(j)!=words[i].charAt(j)){
                    adj[words[i].charAt(j)-'a'].add(words[i-1].charAt(j)-'a');
                    break;
                }
            }
        }
    }


    /**
     * Accepted
     * @param words
     * @return
     */
    public String alienOrder3(String[] words) {
        Map<Character, Set<Character>> graph = new HashMap<>();
        Map<Character, Integer> inDegree = new HashMap<>();
        for (String word : words){
            for (char c : word.toCharArray()){
                graph.putIfAbsent(c, new HashSet<>());
                inDegree.putIfAbsent(c, 0);
            }
        }
        boolean order = buildGraph(words, graph, inDegree);
        if (!order) return "";
        Queue<Character> queue = new LinkedList<>();
        for (char c : inDegree.keySet()){
            if (inDegree.get(c)==0){
                queue.offer(c);
            }
        }
        StringBuffer sb = new StringBuffer();
        while (!queue.isEmpty()){
            char c = queue.poll();
            sb.append(c);
            for (char next : graph.get(c)){
                inDegree.put(next, inDegree.get(next)-1);
                if (inDegree.get(next)==0){
                    queue.offer(next);
                }
            }
        }
        return sb.length()==graph.size()?sb.toString():"";
    }



    private boolean buildGraph(String[] words, Map<Character, Set<Character>> graph, Map<Character, Integer> inDegree){
        for (int i=1;i<words.length;i++){
            String w1 = words[i-1];
            String w2 = words[i];
            int j = 0;
            while (j<Math.min(w1.length(), w2.length())){
                if (w1.charAt(j)!=w2.charAt(j)){
                    if (graph.get(w1.charAt(j)).add(w2.charAt(j))){
                        inDegree.put(w2.charAt(j), inDegree.get(w2.charAt(j))+1);
                    }
                    break;
                }
                j++;
            }
            if (j==Math.min(w1.length(), w2.length()) && w1.length() > w2.length()) return false;
        }
        return true;
    }

    @Test
    public void test(){
        System.out.println(alienOrder3(new String[]{"za","zb","ca","cb"}));
        System.out.println(alienOrder2(new String[]{"bsusz","rhn","gfbrwec","kuw","qvpxbexnhx","gnp","laxutz","qzxccww"}));
        System.out.println(alienOrder2(new String[]{"wrt", "wrf", "er", "ett", "rftt"}));
        System.out.println(alienOrder2(new String[]{"z","x","z"}));
        System.out.println(alienOrder2(new String[]{"zx","z"}));
    }
}
