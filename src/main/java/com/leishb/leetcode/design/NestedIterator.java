package com.leishb.leetcode.design;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by me on 2019/5/31.
 */
public class NestedIterator implements Iterator<Integer>{


    Iterator<Integer> iterator ;

    public NestedIterator(List<NestedInteger> nestedList) {
        List<Integer> list = new ArrayList<>();
        for (NestedInteger ni : nestedList){
            dfs(ni, list);
        }
        iterator = list.iterator();
    }

    private void dfs(NestedInteger ni , List<Integer> list){
        if (ni.isInteger()){
            list.add(ni.getInteger());
            return;
        }
        for (NestedInteger n : ni.getList()){
            dfs(n, list);
        }
    }


    @Override
    public Integer next() {
        return iterator.next();
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }
}
