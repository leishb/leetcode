package com.leishb.leetcode.array;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by me on 2019/10/21.
 */
public class _957_Prison_Cells_After_N_Days {

    public int[] prisonAfterNDays(int[] cells, int N) {
        Set<String> set = new HashSet<>();
        int i=0;
        for (;i<N;i++){
            int[] next = nextDay(cells);
            String k = Arrays.toString(next);
            if (set.contains(k)){
                break;
            }
            set.add(k);
            cells = next;
        }
        if (i==N){
            return cells;
        }
        N = N%i;
        for (int j=0;j<N;j++){
            cells = nextDay(cells);
        }
        return cells;
    }

    private int[] nextDay(int[] cells){
        int[] temp = new int[cells.length];
        for (int i=1;i<cells.length-1;i++){
            temp[i] = cells[i-1]==cells[i+1]?1:0;
        }
        return temp;
    }


    @Test
    public void test(){
        prisonAfterNDays(new int[]{1,0,0,1,0,0,1,0}, 1000000000);
    }
}
