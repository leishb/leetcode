package com.leishb.leetcode.design;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by me on 2020/1/7.
 */
public class _519_Random_Flip_Matrix {

    /**
     * Accepted
     */
    class Solution {

        int rows, cols, total;
        Map<Integer, Integer> map;
        Random random ;

        public Solution(int n_rows, int n_cols) {
            this.rows = n_rows;
            this.cols = n_cols;
            map = new HashMap<>();
            random = new Random();
            this.total = n_rows*n_cols;
        }

        public int[] flip() {
            int r = random.nextInt(total--);
            int x = map.getOrDefault(r, r);
            map.put(r, map.getOrDefault(total, total));
            return new int[]{x/cols, x%cols};
        }

        public void reset() {
            map.clear();
            this.total = rows * cols;
        }
    }
}
