package com.leishb.leetcode.array;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by me on 2019/10/16.
 */
public class _135_Candy {

    public int candy(int[] ratings) {
        if (ratings.length<=1) return ratings.length;
        List<Integer> lowers = new ArrayList<>();
        for (int i=0;i<ratings.length;i++){
            if (i==0 && ratings[i]<=ratings[i+1]){
                lowers.add(i);
            }else if (i == ratings.length-1 && ratings[i] <= ratings[i-1]){
                lowers.add(i);
            }else if (i!=0 && i!=ratings.length-1 &&ratings[i] <= ratings[i-1] && ratings[i] <= ratings[i+1]){
                lowers.add(i);
            }
        }
        int[] candy = new int[ratings.length];
        for (int i : lowers){
            int cur = i;
            candy[i] = 1;
            while (cur>0 && ratings[cur] < ratings[cur-1]){
                if (candy[cur-1] < candy[cur]+1){
                    candy[cur-1] = candy[cur]+1;
                }
                cur-=1;
            }
            cur = i;
            while (cur<ratings.length-1 && ratings[cur] < ratings[cur+1]){
                if (candy[cur+1] < candy[cur]+1){
                    candy[cur+1] = candy[cur]+1;
                }
                cur+=1;
            }
        }
        int ans = 0;
        for (int v : candy){
            ans += v;
        }
        return ans;
    }
}
