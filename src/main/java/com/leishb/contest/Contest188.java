package com.iqiyi.pay.cashier.httpTest;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Contest188 {


    public List<String> buildArray(int[] target, int n) {
        List<String> ans = new ArrayList<>();
        String push = "Push", pop = "Pop";
        int i = 0, j = 1;
        while (i < target.length){
            while (target[i] > j){
                ans.add(push);
                ans.add(pop);
                j++;
            }
            ans.add(push);
            i++;
            j++;
        }
        return ans;
    }


    public int countTriplets(int[] arr) {
        int ans = 0;
        for (int i=0;i<arr.length;i++){
            int num = arr[i];
            for (int j = i+1;j<arr.length;j++){
                num ^= arr[j];
                if ( num == 0) ans+=j-i;
            }
        }
        return ans;
    }



    public int minTime(int n, int[][] edges, List<Boolean> hasApple) {
        List<Integer>[] adj = new List[n];
        for (int i=0;i<n;i++){
            adj[i] = new ArrayList<>();
        }
        for (int[] edge : edges){
            adj[edge[0]].add(edge[1]);
        }
        return dfs(adj, 0, hasApple)[1];
    }


    private int dfs(int[] arr, List<Integer>[] adj, int start, boolean[] has){
        int ans = has[start] ? 1 : 0;
        for (int next : adj[start]){
            ans += dfs(arr, adj, next, has);
        }
        return arr[start] = ans;
    }

    private int dfs(List<Integer>[] adj, int start,  int[] apples, boolean[] has){
        int steps = 0;
        if (has[start])apples[start]--;
        if (apples[start] ==0) return 0;
        for (int next : adj[start]){
            if ( apples[next] > 0){
                steps += dfs(adj, next, apples, has) + 2;
            }
        }
        return steps;
    }

    private int[] dfs(List<Integer>[] adj, int start, List<Boolean> hasApples){
        int[] ans = new int[]{0, 0};
        if (hasApples.get(start)) ans[0] +=1;
        for (int i :adj[start]){
            int[] next =dfs(adj, i, hasApples);
            if (next[0] > 0){
                ans[0] += next[0];
                ans[1] += next[1] + 2;
            }
        }
        return ans;
    }



    public int ways(String[] pizza, int k) {
        int m = pizza.length, n = pizza[0].length();
        int[][] sums = new int[m+1][n+1];
        for (int i=0;i<m;i++){
            for (int j =0;j<n;j++){
                sums[i+1][j+1] = sums[i+1][j] + sums[i][j+1] - sums[i][j] + (pizza[i].charAt(j)!='.' ? 1 : 0);
            }
        }
        return dfs(sums, 0, 0, m-1, n-1, k, new HashMap<>());
    }

    int M = 1_000_000_007;

    private int dfs(int[][] sums, int rs, int cs, int re, int ce, int k, Map<String,Integer> memo){
        if (sums[re+1][ce+1] - sums[re+1][cs] - sums[rs][ce+1] + sums[rs][cs] < k) return 0;
        if (k==1) return 1;
        String key = Arrays.toString(new int[]{rs, cs, re, ce, k});
        if (memo.containsKey(key)) return memo.get(key);
        int ans = 0;
        for (int i=rs;i<re;i++){
            if (sums[i+1][ce+1] - sums[i+1][cs] - sums[rs][ce+1] + sums[rs][cs] > 0){
                ans = (ans + dfs(sums, i+1, cs, re, ce, k-1, memo)) % M;
            }
        }
        for (int i=cs;i<ce;i++){
            if (sums[re+1][i+1] - sums[re+1][cs] - sums[rs][i+1] + sums[rs][cs] > 0){
                ans = (ans + dfs(sums, rs, i+1, re, ce, k-1, memo)) % M;
            }
        }
        memo.put(key, ans);
        return ans;
    }


    private int dfs(int[][] sums, int rs, int cs, int re, int ce, int k, Integer[][][] memo){
        if (sums[re+1][ce+1] - sums[re+1][cs] - sums[rs][ce+1] + sums[rs][cs] < k) return 0;
        if (k==1) return 1;
        if (memo[rs][cs][k] != null) return memo[rs][cs][k];
        int ans = 0;
        for (int i=rs;i<re;i++){
            if (sums[i+1][ce+1] - sums[i+1][cs] - sums[rs][ce+1] + sums[rs][cs] > 0){
                ans = (ans + dfs(sums, i+1, cs, re, ce, k-1, memo)) % M;
            }
        }
        for (int i=cs;i<ce;i++){
            if (sums[re+1][i+1] - sums[re+1][cs] - sums[rs][i+1] + sums[rs][cs] > 0){
                ans = (ans + dfs(sums, rs, i+1, re, ce, k-1, memo)) % M;
            }
        }
        return memo[rs][cs][k] = ans;
    }


    //N person, M fruits
    public int poll(int[][] votes){
        int N = votes.length, M = votes[0].length;
        boolean[] remove = new boolean[M+1];
        int[] curor = new int[N];
        for (int i=1;i<M;i++){
            int[] count = new int[M+1];
            for (int j=0;j<N;j++){
                if (remove[votes[j][curor[j]]]){
                    curor[j]++;
                }
                count[votes[j][curor[j]]]++;
            }
            int candidate = 1;
            for (int j=1;j<=M;j++) if (!remove[j]) {
                candidate = j;
                break;
            }
            for (int j=1;j<=M;j++)if (count[j] < count[candidate] && !remove[j]) candidate = j;
            remove[candidate] = true;
        }
        for (int i=1;i<=M;i++) if (!remove[i]) return i;
        return 0;
    }



    public int search(int[] nums){
        int n = nums.length;
        int l = 0, r = n - 1;
        while (l <= r){
            int m = (l + r) / 2;
            if (m==0 || m==n-1 || (nums[m] != nums[m-1] && nums[m] != nums[m+1])) return nums[m];
            if ((m-l+1) % 2 == 0){
                if (nums[m] == nums[m-1]){
                    l = m + 1;
                }else {
                    r = m - 1;
                }
            }else {
                if (nums[m] == nums[m-1]){
                    r = m - 2;
                }else {
                    l = m + 1;
                }
            }
        }
        return -1;
    }

    @Test
    public void test(){
        System.out.println(poll(new int[][]{{1,2,3}, {2,3,1}}));
        System.out.println(poll(new int[][]{{1,2,3, 4}, {1,2 ,3, 4}, {2, 4, 3, 1}, {3, 4, 2, 1}, {4, 3, 2, 1}}));
    }
}

