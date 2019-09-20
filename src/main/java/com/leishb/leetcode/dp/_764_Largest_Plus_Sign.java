package com.leishb.leetcode.dp;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * Created by me on 2019/8/8.
 */
public class _764_Largest_Plus_Sign {


    /**
     * Accepted
     * @param N
     * @param mines
     * @return
     */
    public int orderOfLargestPlusSign(int N, int[][] mines) {
        Map<Integer, List<Integer>> rowMap = new HashMap<>();
        Map<Integer, List<Integer>> colMap = new HashMap<>();
        for (int[] mine : mines){
            rowMap.putIfAbsent(mine[0], new ArrayList<>());
            rowMap.get(mine[0]).add(mine[1]);
            colMap.putIfAbsent(mine[1], new ArrayList<>());
            colMap.get(mine[1]).add(mine[0]);
        }
        for (List<Integer> rows : rowMap.values()){
            Collections.sort(rows);
        }
        for (List<Integer> cols : colMap.values()){
            Collections.sort(cols);
        }
        int max = 0;
        for (int i=0;i<N;i++){
            for (int j=0;j<N;j++){
                List<Integer> rowList = rowMap.get(i);
                int left = 0;
                int right = 0;
                if (rowList==null){
                    left = j;
                    right = N-j-1;
                }else {
                    int pos = searchInsertPos(rowList, j);
                    if (pos==-1){
                        continue;
                    }else if (pos == 0) {
                        left = j;
                        right = rowList.get(pos)-1-j;
                    }else if (pos==rowList.size()){
                        left = j-rowList.get(pos-1)-1;
                        right = N-j-1;
                    }else {
                        left = j-rowList.get(pos-1)-1;
                        right = rowList.get(pos)-1-j;
                    }
                }
                List<Integer> colList = colMap.get(j);
                int up = 0;
                int down = 0;
                if (colList==null){
                    up = i;
                    down = N-i-1;
                }else {
                    int pos = searchInsertPos(colList, i);
                    if (pos==-1){
                        continue;
                    }else if (pos==0){
                        up = i;
                        down = colList.get(pos)-1-i;
                    }else if (pos==colList.size()){
                        up = i-colList.get(pos-1)-1;
                        down = N-i-1;
                    }else {
                        up = i-colList.get(pos-1)-1;
                        down = colList.get(pos)-1-i;
                    }
                }
                max = Math.max(max, Math.min(Math.min(left, right), Math.min(up, down))+1);
            }
        }
        return max;
    }

    private int searchInsertPos(List<Integer> list, int tartet){
        int low = 0;
        int high = list.size()-1;
        while (low<=high){
            int mid = (low+high)/2;
            if (list.get(mid)==tartet){
                return -1;
            }else if (list.get(mid) > tartet){
                high=mid-1;
            }else {
                low = mid + 1;
            }
        }
        return low;
    }


    public int orderOfLargestPlusSign2(int N, int[][] mines) {
        int[][] dp = new int[N][N];
        Set<Integer> set = new HashSet<>();
        for (int[] mine :mines){
            set.add(mine[0]*N+mine[1]);
        }
        int ans = 0;
        int count = 0;
        for (int i=0;i<N;i++){
            count = 0;
            for (int j=0;j<N;j++){
                count = set.contains(i*N+j)?0:count+1;
                dp[i][j] = count;
            }
            count = 0;
            for (int j=N-1;j>=0;j--){
                count = set.contains(i*N+j)?0:count+1;
                dp[i][j] = Math.min(dp[i][j], count);
            }
        }
        for (int i=0;i<N;i++){
            count = 0;
            for (int j=0;j<N;j++){
                count = set.contains(j*N+i)?0:count+1;
                dp[j][i] = Math.min(dp[j][i], count);
            }
            count = 0;
            for (int j=N-1;j>=0;j--){
                count = set.contains(j*N+i)?0:count+1;
                dp[j][i] = Math.min(dp[j][i], count);
                ans = Math.max(dp[j][i], ans);
            }
        }
        return ans;
    }


    public int orderOfLargestPlusSign3(int N, int[][] mines) {
        Set<Integer> set = new HashSet<>();
        for (int[] mine :mines){
            set.add(mine[0]*N+mine[1]);
        }
        int[][] left = new int[N][N];
        int[][] top = new int[N][N];
        for (int i=0;i<N;i++){
            for (int j=0;j<N;j++){
                if (set.contains(i*N+j)){
                    continue;
                }
                top[i][j] = i>0?top[i-1][j]+1:1;
                left[i][j] = j>0?left[i][j-1]+1:1;
            }
        }
        int len = N%2!=0?N/2+1:N/2;
        for (int l=len;l>=1;l--){
            for (int i=l-1;i+l-1<N;i++){
                for (int j=l-1;j+l-1<N;j++){
                    if (left[i][j] >=l && top[i][j] >=l && left[i][j+l-1]-left[i][j]==l-1
                            && top[i+l-1][j]-top[i][j]==l-1){
                        return l;
                    }
                }
            }
        }
        return 0;
    }

    @Test
    public void test(){
        Assert.assertTrue(orderOfLargestPlusSign3(5, new int[][]{{4,2}})==2);
        Assert.assertTrue(orderOfLargestPlusSign3(1, new int[][]{{0,0}})==0);
        Assert.assertTrue(orderOfLargestPlusSign3(2, new int[][]{})==1);
        Assert.assertTrue(orderOfLargestPlusSign3(3, new int[][]{{0,2},{1,0},{2,0}})==1);
        Assert.assertTrue(orderOfLargestPlusSign3(2, new int[][]{{0,0},{1,1}})==1);
    }
}
