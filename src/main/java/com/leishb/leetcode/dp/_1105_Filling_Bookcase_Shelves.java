package com.leishb.leetcode.dp;

/**
 * Created by me on 2019/12/31.
 */
public class _1105_Filling_Bookcase_Shelves {


    int[][] memo;


    /**
     * Accepted
     * @param books
     * @param shelf_width
     * @return
     */
    public int minHeightShelves(int[][] books, int shelf_width) {
        memo = new int[books.length+1][shelf_width+1];
        return dfs(books, shelf_width, 0, 0, 0);
    }


    /**
     *
     * @param books
     * @param shelf_width
     * @param index
     * @param width_left
     * @param curHeight 当前书架的高度
     * @return
     */
    private int dfs(int[][] books, int shelf_width, int index, int width_left, int curHeight){
        if (index==books.length) {
            return curHeight;
        }
        if (memo[index][width_left]!=0) {
            return memo[index][width_left];
        }
        int min = curHeight + dfs(books, shelf_width, index+1, shelf_width - books[index][0], books[index][1]);
        if (width_left >= books[index][0]){
            min = Math.min(min, dfs(books, shelf_width, index+1, width_left-books[index][0], Math.max(curHeight, books[index][1])));
        }
        memo[index][width_left] = min;
        return min;
    }

    public int minHeightShelves2(int[][] books, int shelf_width) {
        int[] dp = new int[books.length+1];
        for (int i=1;i<=books.length;i++){
            int width = books[i-1][0];
            int height = books[i-1][1];
            dp[i] = dp[i-1] + height;
            for (int j=i-1;j>0;j--){
                if (width + books[j-1][0] <=shelf_width){
                    width += books[j-1][0];
                    height = Math.max(height, books[j-1][1]);
                    dp[i] = Math.min(dp[i], dp[j-1] + height);
                }else {
                    break;
                }
            }
        }
        return dp[books.length];
    }
}
