package com.leishb.leetcode.array;

import org.junit.Test;

import java.util.*;

/**
 * Created by me on 2019/8/1.
 */
public class _638_Shopping_Offers {

    int count = 0;
    int cache = 0;

    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        int minPrice = dfs( price, special, needs, new HashMap<>());
        return minPrice;
    }


    private int dfs( List<Integer> price, List<List<Integer>> special, List<Integer> leftNeeds,  Map<List<Integer>, Integer> memo){
        if (hasBuyAll(leftNeeds)){
            return 0;
        }
        if (memo.containsKey(leftNeeds)){
            cache++;
            return memo.get(leftNeeds);
        }
        count++;
        int min = Integer.MAX_VALUE;
        for (int i=0;i<special.size();i++){
            List<Integer> sp = special.get(i);
            if (buySpecail(sp, leftNeeds)){
                int p = dfs( price, special, leftNeeds,  memo) + sp.get(sp.size()-1);
                min = Math.min(min, p);
                revertSpecail(sp, leftNeeds);
            }
        }
        min = Math.min(min, directPurchase(price, leftNeeds));
        memo.put(leftNeeds, min);
        return min;
    }


    private void revertSpecail(List<Integer> special, List<Integer> leftNeeds){
        for (int i=0;i<leftNeeds.size();i++){
            leftNeeds.set(i, leftNeeds.get(i)+special.get(i));
        }
    }

    private boolean buySpecail(List<Integer> special, List<Integer> leftNeeds){
        for (int i=0;i<leftNeeds.size();i++){
            if (leftNeeds.get(i) < special.get(i)){
                return false;
            }
        }
        for (int i=0;i<leftNeeds.size();i++){
            leftNeeds.set(i, leftNeeds.get(i)-special.get(i));
        }
        return true;
    }


    private boolean hasBuyAll(List<Integer> needs){
        for (int i : needs){
            if (i>0){
                return false;
            }
        }
        return true;
    }


    private int directPurchase(List<Integer> price, List<Integer> needs) {
        int total = 0;
        for (int i = 0; i < needs.size(); i++) {
            total += price.get(i) * needs.get(i);
        }

        return total;
    }


    @Test
    public void test(){
        int[][] matrix = new int[][]{{0,0,4,6,2,0,22},{1,4,3,5,5,3,10},{4,5,6,3,4,1,29},{0,3,2,2,4,2,4},{4,6,3,4,4,6,21},{5,6,3,6,3,4,23},{6,1,3,4,6,2,9},{3,3,6,1,5,1,16},{0,3,6,4,0,2,5},{5,1,2,3,5,5,7},{0,1,1,6,2,4,24},{1,5,2,2,6,1,3},{4,2,2,4,3,1,8},{3,1,0,6,1,2,30},{4,6,1,4,0,0,2},{0,4,5,6,2,5,1},{2,6,0,6,6,2,21},{2,1,3,4,0,2,2},{6,4,4,4,1,3,24},{6,3,1,6,5,5,12},{1,3,2,1,3,2,32},{2,2,0,3,1,2,16},{2,4,3,6,6,5,26},{1,6,3,5,0,4,2},{6,2,1,5,6,2,9},{0,4,2,2,5,3,3},{6,3,3,6,0,5,9},{4,3,2,5,3,3,29},{1,6,0,0,1,6,31},{5,6,0,5,4,3,31},{0,4,2,6,0,6,28},{5,4,3,2,5,3,32},{6,5,1,1,4,6,18},{3,3,3,2,3,3,2},{5,6,2,5,3,3,7},{1,2,6,4,4,0,18},{0,4,4,0,0,3,18},{4,2,0,0,3,3,19},{6,0,4,4,4,6,15},{6,2,3,0,2,2,4},{4,1,1,5,5,5,14},{3,6,4,0,6,2,27},{2,4,6,2,2,3,24},{6,0,5,3,1,6,7},{3,1,5,1,2,6,28},{5,2,2,1,1,4,25},{5,6,5,0,3,4,19},{3,5,3,3,5,1,31},{6,0,1,1,6,4,14},{0,3,4,3,3,4,10},{4,1,2,2,0,0,27},{2,2,1,3,5,2,24},{2,3,2,6,1,1,21},{6,6,5,6,2,2,12},{6,6,3,1,0,6,28},{6,4,1,6,5,0,8},{3,3,0,5,4,2,7},{4,3,3,3,0,2,25},{1,2,0,5,2,4,8},{0,1,6,6,5,5,27},{3,6,4,5,2,2,4},{4,4,6,1,5,3,30},{4,3,4,5,5,5,19},{0,0,0,6,1,0,27},{6,5,0,1,2,4,10},{2,6,0,0,1,0,13},{4,1,6,1,4,1,24},{2,4,0,1,4,1,25},{5,1,3,3,4,1,8},{5,5,1,0,2,1,25},{1,6,2,4,0,6,27},{4,0,3,0,5,3,30},{2,4,6,6,3,2,4},{6,4,2,2,0,3,27},{1,2,1,2,2,1,2},{2,0,3,0,5,4,4},{3,5,4,4,1,1,25},{2,1,1,6,3,3,28},{4,4,4,3,6,3,21},{1,4,1,4,2,2,27},{0,6,0,2,2,2,33},{3,3,5,6,4,6,9},{1,0,0,3,4,2,11},{1,3,0,3,0,1,16},{2,3,0,0,0,5,1},{3,5,6,4,1,4,3},{3,1,0,2,6,0,19},{3,0,0,5,3,1,6},{1,0,4,1,2,2,18},{0,0,4,3,5,1,31},{4,4,2,5,5,2,2},{5,0,2,6,5,3,4},{6,2,1,0,2,3,11},{4,5,1,5,3,3,23},{6,2,5,1,6,6,4},{5,6,6,1,5,6,6},{3,2,6,1,4,5,19},{0,2,6,2,5,0,26},{0,1,3,6,3,6,18},{3,5,4,6,5,3,6}};
        int[] prices = new int[]{4,7,9,9,3,2};
        List<List<Integer>> special = new ArrayList<>();
        for (int i=0;i<matrix.length;i++){
            List<Integer> list = new ArrayList<>();
            for(int j=0;j<matrix[0].length;j++){
                list.add(matrix[i][j]);
            }
            special.add(list);
        }
        long start = System.currentTimeMillis();
        shoppingOffers(new ArrayList<>(Arrays.asList(4,7,9,9,3,2)), special,new ArrayList<>(Arrays.asList(6,6,6,6,6,6)) );
        System.out.println("cost : " + (System.currentTimeMillis()-start));
        System.out.println("cache : " + cache + ", count : " + count);
    }
}
