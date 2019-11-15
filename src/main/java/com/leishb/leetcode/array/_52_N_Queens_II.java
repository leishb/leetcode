package com.leishb.leetcode.array;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by me on 2019/11/8.
 */
public class _52_N_Queens_II {


    int count = 0;

    /**
     * Accepted
     * @param n
     * @return
     */
    public int totalNQueens(int n) {
        backtrack(0, n, new HashSet(), new HashSet(), new HashSet());
        return count;
    }

    private void backtrack(int row, int n, Set<Integer> cols, Set<Integer> diag1, Set<Integer> diag2){
        if (row==n){
            count++;
            return;
        }
        for (int i = 0;i<n;i++){
            int col = i, d1 = row + col, d2 = row-col;
            if (cols.contains(col)||diag1.contains(d1) || diag2.contains(d2)) continue;
            cols.add(col);
            diag1.add(d1);
            diag2.add(d2);
            backtrack(row+1, n, cols, diag1, diag2);
            cols.remove(col);
            diag1.remove(d1);
            diag2.remove(d2);
        }
    }
}
