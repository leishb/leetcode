package com.leishb.leetcode.design;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by me on 2019/8/29.
 */
public class _900_RLEIterator {


    class Pair{
        int k;
        int v;
        Pair(int k, int v){
            this.k = k;
            this.v = v;
        }
    }
    List<Pair> list = new ArrayList<>();
    int skip = 0;

    public _900_RLEIterator(int[] A) {
        for (int i=0;i+1<A.length;i+=2){
            if (A[i]!=0){
                list.add(new Pair(A[i], A[i+1]));
            }
        }
    }

    public int next(int n) {
        int k = 0;
        int ans = -1;
        for (int i=skip;i<list.size();i++){
            Pair p = list.get(i);
            if (k+p.k >=n){
                ans = p.v;
                p.k = p.k - (n-k);
                if (p.k==0)skip+=1;
                break;
            }
            k += p.k;
            skip+=1;
        }
        return ans;
    }


    public static void main(String[] args){
        _900_RLEIterator iterator = new _900_RLEIterator(new int[]{784,303,477,583,909,505});
        iterator.next(133);
        iterator.next(333);
        iterator.next(238);
        iterator.next(87);
        iterator.next(301);
        iterator.next(276);
    }
}
