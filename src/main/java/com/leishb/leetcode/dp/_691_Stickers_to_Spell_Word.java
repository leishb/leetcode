package com.leishb.leetcode.dp;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by me on 2019/10/11.
 */
public class _691_Stickers_to_Spell_Word {

    public int minStickers(String[] stickers, String target) {
        int ans = 0;
        int[] count = new int[26];
        for (char c : target.toCharArray()){
            count[c-'a'] +=1;
        }
        int total = target.length();
        while (total>0){
            int k = 0;
            String take = "";
            for (String s : stickers){
                int m = compare(s, count);
                if (m > k){
                    k = m;
                    take = s;
                }
            }
            if (take.equals("")) return -1;
            reduce(take, count);
            ans ++;
            total-=k;
        }
        return ans;
    }


    private void reduce(String s, int[] count){
        int[] sc = new int[26];
        for (char c : s.toCharArray()){
            sc[c-'a'] +=1;
        }
        for (int i=0;i<26;i++){
            if (count[i]==0) continue;
            count[i] -= count[i]>=sc[i] ? sc[i] : count[i];
        }
    }


    private int compare(String s, int[] count){
        int[] sc = new int[26];
        for (char c : s.toCharArray()){
            sc[c-'a'] +=1;
        }
        int k = 0;
        for (int i=0;i<26;i++){
            k += count[i]>=sc[i] ? sc[i] : count[i];
        }
        return k;
    }


    /**
     * Accepted
     * @param stickers
     * @param target
     * @return
     */
    public int minStickers2(String[] stickers, String target) {
        int[] count = new int[26];
        for (char c : target.toCharArray()){
            count[c-'a'] +=1;
        }
        int[][] map = new int[stickers.length][26];
        for (int i=0;i<stickers.length;i++){
            for (char c : stickers[i].toCharArray()){
                map[i][c-'a'] +=1;
            }
        }
        int ans= helper(map, count, target.length()+1, new HashMap<>());
        return ans > target.length() ?-1:ans;
    }


    private int helper(int[][] map, int[] count, int total, Map<String, Integer> memo){
        if (Arrays.equals(count, new int[26])){
            return 0;
        }
        if (memo.containsKey(Arrays.toString(count))){
            return memo.get(Arrays.toString(count));
        }
        int min = total;
        for (int[] sc : map){
            int[] newCount = newCount(sc, count);
            if (Arrays.equals(newCount, count))continue;
            int next = helper(map, newCount, total, memo);
            min = Math.min(min, next+1);
        }
        memo.put(Arrays.toString(count), min);
        return min;
    }

    private int[] newCount(int[] sc, int[] count){
        int[] newCount = new int[26];
        for (int i=0;i<26;i++){
            if (count[i]==0) continue;
            newCount[i] = count[i]>=sc[i] ? count[i]-sc[i] : 0;
        }
        return newCount;
    }


    @Test
    public void test(){
        Assert.assertTrue(minStickers2(new String[]{"with", "example", "science"}, "thehat")==3);
        Assert.assertTrue(minStickers2(new String[]{"notice", "possible"}, "basicbasic")==-1);
        Assert.assertTrue(minStickers2(new String[]{"these","guess","about","garden","him"}, "atomher")==3);
    }
}
