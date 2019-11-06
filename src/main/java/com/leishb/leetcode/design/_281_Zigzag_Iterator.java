package com.leishb.leetcode.design;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by me on 2019/11/5.
 */
public class _281_Zigzag_Iterator {


    List<Iterator<Integer>> iterators;

    int id = 0;


    /**
     * Accepted
     * @param v1
     * @param v2
     */
    public _281_Zigzag_Iterator(List<Integer> v1, List<Integer> v2) {
        iterators = new ArrayList<>();
        iterators.add(v1.iterator());
        iterators.add(v2.iterator());
    }

    public int next() {
        if (iterators.size()==0) return -1;
        if (iterators.get(id).hasNext()){
            int res = iterators.get(id).next();
            id = (id+1)%iterators.size();
            return res;
        }else {
            iterators.remove(id);
            id = (id+1)%iterators.size();
            return next();
        }
    }

    public boolean hasNext() {
        for (Iterator<Integer> it : iterators){
            if (it.hasNext()) return true;
        }
        return false;
    }
}
