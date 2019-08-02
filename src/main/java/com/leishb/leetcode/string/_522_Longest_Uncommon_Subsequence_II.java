package com.leishb.leetcode.string;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * Created by me on 2019/8/2.
 */
public class _522_Longest_Uncommon_Subsequence_II {

    /**
     * Accepted
     * @param strs
     * @return
     */
    public int findLUSlength(String[] strs) {
        Map<Integer, List<String>> map = new HashMap<>();
        Queue<Map.Entry<Integer, List<String>>> queue = new PriorityQueue<>((e1, e2)->e2.getKey()-e1.getKey());
        for (String str : strs){
            map.putIfAbsent(str.length(), new ArrayList<>());
            map.get(str.length()).add(str);
        }
        for (Map.Entry entry : map.entrySet()){
            queue.offer(entry);
        }
        List<String> tempList = new ArrayList<>();
        while (!queue.isEmpty()){
            Map.Entry<Integer, List<String>> entry = queue.poll();
            Map<String, Integer> mm = new HashMap<>();
            for (String s : entry.getValue()){
                mm.put(s, mm.getOrDefault(s, 0)+1);
            }
            for (String key : mm.keySet()){
                if (mm.get(key)==1 && (!isSubSequence(key, tempList) || tempList.isEmpty())){
                    return key.length();
                }
            }
            tempList.add(entry.getValue().get(0));
        }
        return -1;
    }


    /**
     * Accepted
     * @param strs
     * @return
     */
    public int findLUSlength2(String[] strs) {
        int ans = -1;
        for (int i=0;i<strs.length;i++){
            if (strs[i].length()<ans){
                continue;
            }
            boolean isSub = false;
            for (int j=0;j<strs.length;j++){
                if (i!=j){
                    if (isSubSequence(strs[i], strs[j])){
                        isSub = true;
                        break;
                    }
                }
            }
            if (!isSub){
                ans = Math.max(ans,strs[i].length());
            }
        }
        return ans;
    }



    private boolean isSubSequence(String source, List<String> list){
        for (String s : list){
            if (!isSubSequence(source, s)){
                return false;
            }
        }
        return true;
    }


    private boolean isSubSequence(String source, String target){
        int j = 0;
        for (int i=0;i<source.length();i++){
            char c = source.charAt(i);
            boolean found = false;
            while (j<target.length()){
                if (c==target.charAt(j)){
                    found = true;
                    j++;
                    break;
                }
                j++;
            }
            if (!found){
                return false;
            }
        }
        return true;
    }


    @Test
    public void test(){
        Assert.assertTrue(findLUSlength2(new String[]{"aaa","aaa","aa"})==-1);
        Assert.assertTrue(findLUSlength2(new String[]{"aba", "cdc", "eae"})==3);
    }
}
