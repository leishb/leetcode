package com.leishb.leetcode.dfs;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by me on 2019/7/12.
 */
public class _464_Can_I_Win {


    /**
     * TLE
     * @param maxChoosableInteger
     * @param desiredTotal
     * @return
     */
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        if( (maxChoosableInteger+1)*maxChoosableInteger/2 < desiredTotal){
            return false;
        }
        return win(maxChoosableInteger, 0,  desiredTotal, new boolean[maxChoosableInteger+1]);
    }


    private boolean win(int max, int total,  int desiredTotal, boolean[] used){
        for (int i=max;i>=1;i--){
            if (used[i]){
                continue;
            }
            if (total+i>=desiredTotal){
                return true;
            }
            used[i]=true;
            boolean win = win(max, total+i,  desiredTotal, used);
            used[i] = false;
            if (!win){
                return true;
            }
        }
        return false;
    }


    /**
     * Accepted
     * @param maxChoosableInteger
     * @param desiredTotal
     * @return
     */
    public boolean canIWin2(int maxChoosableInteger, int desiredTotal) {
        int sum = (maxChoosableInteger+1)*maxChoosableInteger/2;
        if( sum < desiredTotal){
            return false;
        }
        if (sum == desiredTotal){
            return sum%2==1;
        }
        if (maxChoosableInteger>=desiredTotal){
            return true;
        }
        return win(maxChoosableInteger, 0,  desiredTotal, 0,  new boolean[maxChoosableInteger+1], new HashMap<>());
    }


    private boolean win(int max, int total, int desiredTotal, int mask, boolean[] used, Map<String, Boolean> memo){
        String key = mask + "-" + total;
        if (memo.containsKey(key)){
            return memo.get(key);
        }
        if (total>=desiredTotal){
            memo.put(key, false);
            return false;
        }
        for (int i=max;i>=1;i--){
            if (used[i]){
                continue;
            }
            used[i]=true;
            boolean win = win(max, total+i,  desiredTotal, mask|(1<<i),  used, memo);
            used[i] = false;
            if (!win){
                memo.put(key, true);
                return true;
            }
        }
        memo.put(key, false);
        return false;
    }



    private boolean win2(int max, int total, int desiredTotal, int mask, boolean[] used, Map<Integer, Boolean> memo){
        if (memo.containsKey(mask)){
            return memo.get(mask);
        }
        if (total>=desiredTotal){
            memo.put(mask, false);
            return false;
        }
        for (int i=max;i>=1;i--){
            if (used[i]){
                continue;
            }
            used[i]=true;
            boolean win = win2(max, total+i,  desiredTotal, mask|(1<<i),  used, memo);
            used[i] = false;
            if (!win){
                memo.put(mask, true);
                return true;
            }
        }
        memo.put(mask, false);
        return false;
    }


    @Test
    public void test(){
        Assert.assertFalse(canIWin2(10, 11));
        Assert.assertTrue(canIWin2(10, 8));
        Assert.assertFalse(canIWin(5, 50));
        Assert.assertTrue(canIWin2(19, 190));
//        canIWin(20, 210);
        canIWin(18, 79);
    }
}
