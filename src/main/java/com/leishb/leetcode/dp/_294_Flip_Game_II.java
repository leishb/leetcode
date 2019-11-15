package com.leishb.leetcode.dp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by me on 2019/11/7.
 */
public class _294_Flip_Game_II {

    /**
     * Accepted
     * @param s
     * @return
     */
    public boolean canWin(String s) {
        return canWin(s, new HashMap<>());
    }

    private boolean canWin(String s, Map<String, Boolean> memo){
        if (memo.containsKey(s)) return memo.get(s);
        List<String> nexts = nextStates(s);
        for (String next : nexts){
            if (!canWin(next, memo)){
                memo.put(s, true);
                return true;
            }
        }
        memo.put(s, false);
        return false;
    }

    private List<String> nextStates(String s){
        List<String> list = new ArrayList<>();
        for (int i=0;i<s.length()-1;i++){
            if (s.charAt(i)=='+' && s.charAt(i+1)=='+'){
                list.add(s.substring(0, i)+"--"+s.substring(i+2));
            }
        }
        return list;
    }
}
