package com.leishb.leetcode.array;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by me on 2019/11/8.
 */
public class _51_N_Queens {


    /**
     * Accepted
     * @param n
     * @return
     */
    public List<List<String>> solveNQueens(int n) {
        List<StringBuffer> list = new ArrayList<>();
        for (int i=0;i<n;i++){
            StringBuffer sb = new StringBuffer();
            for (int j=0;j<n;j++){
                sb.append('.');
            }
            list.add(sb);
        }
        List<List<String>> res = new ArrayList<>();
        backtrack(res, list, 0, n);
        return res;
    }

    private void backtrack(List<List<String>> res, List<StringBuffer> list, int row, int n){
        if (row==n){
            List<String> l = new ArrayList<>();
            for (StringBuffer s : list){
                l.add(s.toString());
            }
            res.add(l);
            return;
        }
        for (int i=0;i<n;i++){
            if (valid(list, i, row)){
                list.get(row).setCharAt(i, 'Q');
                backtrack(res, list, row+1, n);
                list.get(row).setCharAt(i, '.');
            }
        }
    }


    private boolean valid(List<StringBuffer> list, int col, int row){
        int j = col-1, k = col+1;
        for (int i=row-1;i>=0;i--){
            if (list.get(i).charAt(col)=='Q') return false;
            if (j>=0 && list.get(i).charAt(j)=='Q') return false;
            if (k<list.size() && list.get(i).charAt(k)=='Q') return false;
            j--;
            k++;
        }
        return true;
    }



    private boolean checkCol(List<StringBuffer> list, int col, int row){
        for (int i=0;i<row;i++){
            if (list.get(i).charAt(col)=='Q') return false;
        }
        return true;
    }

    private boolean checkDiag(List<StringBuffer> list, int col, int row){
        int j = col-1, i = row-1;
        while (j>=0 && i>=0){
            if (list.get(i).charAt(j)=='Q') return false;
            i--;
            j--;
        }
        j = col+1;
        i = row-1;
        while (j<list.size() && i>=0){
            if (list.get(i).charAt(j)=='Q') return false;
            i--;
            j++;
        }
        return true;
    }
}
