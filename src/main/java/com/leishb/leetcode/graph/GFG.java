package com.leishb.leetcode.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by me on 2018/1/19.
 */
public class GFG {

    static int MAX_N = 11;
    static int count = 0;
    static int S;
    static int D;
    static List<Integer>[] adjList = new List[MAX_N];
    static boolean[] visited = new boolean[MAX_N];


    static void dfs(int s){
        visited[s] = true;
        if (s == D){
            count++;
            return;
        }
        for (int i : adjList[s]){
            if (!visited[i]){
                dfs(i);
                visited[i]=false;
            }
        }
    }

    static void initial(int n){
        for (int i=0;i<n;i++){
            adjList[i] = new ArrayList<>();
            visited[i] = false;
        }
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();
        int n ,e, u, v;
        while (T-->0){
            n = scanner.nextInt();
            e = scanner.nextInt();
            initial(n);
            while (e-->0){
                u = scanner.nextInt();
                v = scanner.nextInt();
                adjList[u].add(v);
            }
            S = scanner.nextInt();
            D = scanner.nextInt();
            count = 0;
            dfs(S);
            System.out.println(count);
        }
    }


}
