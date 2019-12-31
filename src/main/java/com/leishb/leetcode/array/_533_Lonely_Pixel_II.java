package com.leishb.leetcode.array;

/**
 * Created by me on 2019/11/25.
 */
public class _533_Lonely_Pixel_II {


    public int findBlackPixel(char[][] picture, int N) {
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
                if (picture[i][j]=='B' && rows[i]==N && cols[j]==N){
                    int k=0;
                    for (;k<m;k++){
                        if (picture[k][j]!='B')continue;
                        if (rows[k]!=rows[i]){
                            break;
                        }
                    }
                    if (k==m) ans++;
                }
            }
        }
        return ans;
    }
}
