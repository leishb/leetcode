package com.leishb.leetcode.dfs;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by me on 2019/9/9.
 */
public class _990_Satisfiability_of_Equality_Equations {

    /**
     * Accepted
     * @param equations
     * @return
     */
    public boolean equationsPossible(String[] equations) {
        List<Integer>[] equal = new List[26];
        List<Integer>[] notEqual = new List[26];
        for (int i=0;i<26;i++){
            equal[i] = new ArrayList<>();
            notEqual[i] = new ArrayList<>();
        }
        for (String equation : equations){
            boolean equals = equation.charAt(1)=='=';
            if (equals){
                equal[equation.charAt(0)-'a'].add(equation.charAt(3)-'a');
            }else {
                notEqual[equation.charAt(0)-'a'].add(equation.charAt(3)-'a');
            }
        }
        DSU dsu = new DSU(26);
        for (int i=0;i<26;i++){
            for (int j : equal[i]){
                dsu.union(i, j);
            }
        }
        for (int i=0;i<26;i++){
            for (int j : notEqual[i]){
                if (dsu.find(i)==dsu.find(j)){
                    return false;
                }
            }
        }
        return true;
    }


    class DSU {
        int[] parent;

        DSU(int N){
            this.parent = new int[N];
            for (int i=0;i<N;i++){
                parent[i] = i;
            }
        }

        public int find(int x){
            while (x!=parent[x]){
                x = parent[x];
            }
            return x;
        }

        public void union(int i, int j){
            parent[find(i)] = parent[find(j)];
        }
    }


}
