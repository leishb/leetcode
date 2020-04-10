package com.leishb.leetcode.array;

import java.util.List;

/**
 * Created by me on 2020/1/21.
 */
public class _624_Maximum_Distance_in_Arrays {


    /**
     * Accepted
     * @param arrays
     * @return
     */
    public int maxDistance(List<List<Integer>> arrays) {
        int maxId1 = -1 , maxId2 = -1;
        int minId1 = -1, minId2 = -1;
        for(int i=0;i<arrays.size();i++){
            List<Integer> list = arrays.get(i);
            if(maxId1 == -1){
                maxId1 = i;
            }else if (list.get(list.size()-1) >= arrays.get(maxId1).get(arrays.get(maxId1).size()-1)){
                maxId2 = maxId1;
                maxId1 = i;
            }else if (maxId2==-1){
                maxId2 = i;
            }else if (list.get(list.size()-1) > arrays.get(maxId2).get(arrays.get(maxId2).size()-1)){
                maxId2 = i;
            }
            if(minId1 == -1){
                minId1 = i;
            }else if (list.get(0) <= arrays.get(minId1).get(0)){
                minId2 = minId1;
                minId1 = i;
            }else if (minId2==-1){
                minId2 = i;
            }else if (list.get(0) < arrays.get(minId2).get(0)){
                minId2 = i;
            }
        }
        return minId1!=maxId1?Math.abs(arrays.get(maxId1).get(arrays.get(maxId1).size()-1) - arrays.get(minId1).get(0))
                : Math.max(Math.abs(arrays.get(maxId1).get(arrays.get(maxId1).size()-1) - arrays.get(minId2).get(0)),
                Math.abs(arrays.get(maxId2).get(arrays.get(maxId2).size()-1) - arrays.get(minId1).get(0)));
    }


    /**
     * Accepted
     * @param arrays
     * @return
     */
    public int maxDistance2(List<List<Integer>> arrays) {
        int max = arrays.get(0).get(arrays.get(0).size()-1);
        int min = arrays.get(0).get(0);
        int res = Integer.MIN_VALUE;
        for (int i=1;i<arrays.size();i++){
            res = Math.max(res, Math.abs(arrays.get(i).get(0) - max ));
            res = Math.max(res, Math.abs(arrays.get(i).get(arrays.get(i).size()-1) - min ));
            max = Math.max(max, arrays.get(i).get(arrays.get(i).size()-1));
            min = Math.min(min, arrays.get(i).get(0));
        }
        return res;
    }
}
