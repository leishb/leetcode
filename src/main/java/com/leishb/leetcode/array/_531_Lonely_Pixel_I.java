package com.leishb.leetcode.array;

/**
 * Created by me on 2019/11/25.
 */
public class _531_Lonely_Pixel_I {


    /**
     * Accepted
     * @param picture
     * @return
     */
    public int findLonelyPixel(char[][] picture) {
        int m = picture.length, n = picture[0].length;
        int[] rows = new int[m];
        int[] cols = new int[n];
        for (int i=0;i<m;i++){
            for (int j=0;j<n;j++){
                if (picture[i][j]=='B') {
                    rows[i]++;
                    cols[j]++;
                }
            }
        }
        int ans = 0;
        for (int i=0;i<m;i++){
            for (int j=0;j<n;j++){
                if (picture[i][j]=='B' && rows[i]==1 && cols[j]==1){
                    ans++;
                }
            }
        }
        return ans;
    }
}
