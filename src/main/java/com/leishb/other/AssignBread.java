package com.leishb.other;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * https://leetcode.com/discuss/interview-question/480256/Google-or-Onsite-or-Find-assignment-of-bread-slice
 * Created by me on 2020/1/16.
 */
public class AssignBread {


    private int[] assignBread(int n , int[][] connections, int host){
        List<Integer>[] adj = new List[n];
        for (int i=0;i<adj.length;i++){
            adj[i] = new ArrayList<>();
        }
        for (int[] conn :connections){
            adj[conn[0]].add(conn[1]);
        }

        int[] res = new int[n];
        if (asign(res, adj, host,  0, n-1)){
            return res;
        }
        return new int[n];
    }


    private boolean asign(int[] res, List<Integer>[] adj, int start, int low, int high){
        if (low > high)return true;

        for (int next : adj[start]){
            int mid = (low+high)/2;
            asign(res, adj, next, low, mid);
            low = mid+1;
        }
        if (low==high){
            res[start] = low;
            return true;
        }
        return false;
    }

}
