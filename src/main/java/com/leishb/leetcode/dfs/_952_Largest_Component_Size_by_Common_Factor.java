package com.leishb.leetcode.dfs;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by me on 2019/12/16.
 */
public class _952_Largest_Component_Size_by_Common_Factor {


    /**
     * Accepted
     * @param A
     * @return
     */
    public int largestComponentSize(int[] A) {
        int N = A.length;
        UF uf = new UF(N);
        Map<Integer, Integer> map = new HashMap<>();
        for (int i=0;i<N;i++){
            int num = A[i];
            for (int j=2;j*j<=num;j++){
                if (num%j!=0) continue;
                if (!map.containsKey(j)){
                    map.put(j, i);
                }else {
                    uf.union(i, map.get(j));
                }

                if (!map.containsKey(num/j)){
                    map.put(num/j, i);
                }else {
                    uf.union(i, map.get(num/j));
                }
            }
            if(!map.containsKey(num)){
                map.put(num, i);
            }else {
                uf.union(map.get(num), i);
            }
        }
        return uf.max;
    }


    class  UF{
        int[] parents;
        int[] size;
        int max;


        UF(int N){
            this.parents = new int[N];
            this.size = new int[N];
            max = 0;
            for (int i=0;i<N;i++){
                parents[i] = i;
                size[i] = 1;
            }
        }


        public int find(int x){
            if (x==parents[x]) return x;
            parents[x] = find(parents[x]);
            return parents[x];
        }


        public void union(int x, int y){
            int rx = find(x);
            int ry = find(y);
            if (rx!=ry){
                parents[rx] = ry;
                size[ry] += size[rx];
                max = Math.max(max, size[ry]);
            }
        }


    }
}
