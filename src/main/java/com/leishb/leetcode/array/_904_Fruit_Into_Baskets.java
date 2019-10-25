package com.leishb.leetcode.array;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by me on 2019/10/25.
 */
public class _904_Fruit_Into_Baskets {

    /**
     * Accepted
     * @param tree
     * @return
     */
    public int totalFruit(int[] tree) {
        int max = 0;
        Map<Integer, Integer> map = new HashMap<>();
        int j=0;
        for (int i=0;i<tree.length;i++){
            map.put(tree[i],map.getOrDefault(tree[i], 0)+1);
            while (map.size()>2){
                map.put(tree[j], map.get(tree[j])-1);
                if (map.get(tree[j])==0) map.remove(tree[j]);
                j++;
            }
            max = Math.max(max, i-j+1);
        }
        return max;
    }


    public int totalFruit2(int[] tree) {
        int max = 0;
        int[] fruits = new int[tree.length];
        int j=0;
        int types =0;
        for (int i=0;i<tree.length;i++){
            fruits[tree[i]] ++;
            if (fruits[tree[i]]==1){
                types++;
            }
            while (types>2){
                fruits[tree[j]]--;
                if (fruits[tree[j]]==0) types--;
                j++;
            }
            max = Math.max(max, i-j+1);
        }
        return max;
    }
}
