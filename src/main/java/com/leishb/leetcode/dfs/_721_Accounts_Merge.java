package com.leishb.leetcode.dfs;

import org.junit.Test;

import java.util.*;

/**
 * Created by me on 2019/8/5.
 */
public class _721_Accounts_Merge {


    /**
     * Accepted
     * @param accounts
     * @return
     */
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, Set<String>> graph = new HashMap<>();
        Map<String, String> nameMap = new HashMap<>();
        for (List<String> account : accounts){
            String name = account.get(0);
            for (int i=1;i<account.size();i++){
                graph.putIfAbsent(account.get(1), new HashSet<>());
                graph.putIfAbsent(account.get(i), new HashSet<>());
                graph.get(account.get(1)).add(account.get(i));
                graph.get(account.get(i)).add(account.get(1));
                nameMap.put(account.get(i), name);
            }
        }
        Set<String> set = new HashSet<>();
        List<List<String>> ans = new ArrayList<>();
        for (String key : graph.keySet()){
            if (!set.contains(key)){
                List<String> list = new ArrayList<>();
                dfs(graph, set, list, key);
                Collections.sort(list);
                list.add(0, nameMap.get(key));
                ans.add(list);
            }
        }
        return ans;
    }


    private void dfs(Map<String, Set<String>> graph, Set<String> set, List<String> list, String s){
        list.add(s);
        set.add(s);
        for (String next : graph.get(s)){
            if (!set.contains(next)){
                dfs(graph, set, list, next);
            }
        }
    }


    /**
     * Accepted
     * @param accounts
     * @return
     */
    public List<List<String>> accountsMerge2(List<List<String>> accounts) {
        Map<String, Set<String>> graph = new HashMap<>();
        Map<String, String> nameMap = new HashMap<>();
        Map<String, Integer> account2Id = new HashMap<>();
        Map<Integer, String> id2Account = new HashMap<>();
        int k = 0;
        for (List<String> account : accounts){
            String name = account.get(0);
            for (int i=1;i<account.size();i++){
                graph.putIfAbsent(account.get(1), new HashSet<>());
                graph.putIfAbsent(account.get(i), new HashSet<>());
                graph.get(account.get(1)).add(account.get(i));
                graph.get(account.get(i)).add(account.get(1));
                nameMap.put(account.get(i), name);
                if (!account2Id.containsKey(account.get(i))){
                    account2Id.put(account.get(i), k);
                    id2Account.put(k, account.get(i));
                    k++;
                }
            }
        }
        int[] parents = new int[k];
        for (int i=0;i<k;i++){
            parents[i] = i;
        }
        for (String key : graph.keySet()){
            int id1 = account2Id.get(key);
            for (String account : graph.get(key)){
                int id2 = account2Id.get(account);
                int p1 = find(parents, id1);
                int p2 = find(parents, id2);
                if (p1!=p2){
                    union(p1, p2, parents);
                }
            }
        }
        Map<Integer, List<String>> map = new HashMap<>();
        for (int i=0;i<parents.length;i++){
            int p = find(parents, i);
            map.putIfAbsent(p, new ArrayList<>());
            map.get(p).add(id2Account.get(i));
        }
        List<List<String>> ans = new ArrayList<>();
        for (List<String> list : map.values()){
            Collections.sort(list);
            list.add(0, nameMap.get(list.get(0)));
            ans.add(list);
        }
        return ans;
    }


    private int find(int[] parent, int x){
        if (parent[x]!=x){
            return find(parent, parent[x]);
        }
        return parent[x];
    }

    private void union(int rootX, int rootY, int[] parent){
        parent[rootY] = rootX;
    }

    public List<List<String>> accountsMerge3(List<List<String>> accounts) {
        Map<String, String> names = new HashMap();
        Map<String, String> parents = new HashMap();
        for (List<String> account: accounts) {
            for (int i=1;i<account.size();i++ ) {
                parents.put(account.get(i), account.get(i));
                names.put(account.get(i), account.get(0));
            }
        }
        for (List<String> account: accounts) {
            for (int i=1;i<account.size();i++ ) {
                for (int j=i+1;j<account.size() ;j++ ) {
                    unoin(account.get(i), account.get(j), parents);
                }
            }
        }
        Map<String, Set<String>> emails = new HashMap();
        for (String key :parents.keySet()) {
            String p = find(key, parents);
            emails.putIfAbsent(p, new HashSet());
            emails.get(p).add(key);
        }
        List<List<String>> res = new ArrayList();
        for (String k : emails.keySet()) {
            String name = names.get(k);
            List<String> list = new ArrayList();
            list.addAll(emails.get(k));
            Collections.sort(list);
            list.add(0, name);
            res.add(list);
        }
        return res;
    }


    public String find(String key, Map<String, String> parents){
        if (key.equals(parents.get(key))) {
            return key;
        }
        parents.put(key, find(parents.get(key), parents));
        return parents.get(key);
    }


    private void unoin(String k1, String k2, Map<String, String> parents){
        String p1 = find(k1, parents);
        String p2 = find(k2, parents);
        if (p1.equals(p2)) return;
        parents.put(p1, p2);
    }


    public List<List<String>> accountsMerge4(List<List<String>> accounts) {
        Map<String, String> name = new HashMap<>();
        Map<String, Integer> emailToId = new HashMap<>();
        int k = 0;
        UF uf = new UF(10001);
        for (List<String> account : accounts){
            for (int i=1;i<account.size();i++){
                name.put(account.get(i), account.get(0));
                if (!emailToId.containsKey(account.get(i))){
                    emailToId.put(account.get(i), k++);
                }
                uf.union(emailToId.get(account.get(i)), emailToId.get(account.get(1)));
            }
        }
        Map<Integer, List<String>> map = new HashMap<>();
        for (String email : name.keySet()){
            int p = uf.find(emailToId.get(email));
            map.putIfAbsent(p, new ArrayList<>());
            map.get(p).add(email);
        }

        for (List<String> list : map.values()){
            Collections.sort(list);
            list.add(0, name.get(list.get(0)));
        }
        return new ArrayList<>(map.values());
    }


    class UF {
        int[] parents;

        public UF(int N){
            this.parents = new int[N];
            for (int i=0;i<N ;i++ ) {
                parents[i] = i;
            }
        }

        public int find(int x){
            if(x==parents[x]) return x;
            return parents[x] = find(parents[x]);
        }

        public void union(int x, int y){
            int rx = find(x);
            int ry = find(y);
            if (rx!=ry) {
                parents[rx] = ry;
            }
        }
    }

    @Test
    public void test(){
        List<List<String>> accounts = new ArrayList<>();
        accounts.add(new ArrayList<>(Arrays.asList("John", "johnsmith@mail.com", "john00@mail.com")));
        accounts.add(new ArrayList<>(Arrays.asList("John", "johnnybravo@mail.com")));
        accounts.add(new ArrayList<>(Arrays.asList("John", "johnsmith@mail.com", "john_newyork@mail.com")));
        accounts.add(new ArrayList<>(Arrays.asList("Mary", "mary@mail.com")));
        System.out.println(accountsMerge3(accounts));
    }
}
