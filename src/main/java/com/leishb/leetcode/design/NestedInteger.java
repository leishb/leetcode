package com.leishb.leetcode.design;


import java.util.ArrayList;
import java.util.List;

public class NestedInteger {

        List<NestedInteger> list;

    int num;

        public NestedInteger(){
            this.list = new ArrayList<>();
        }

        public NestedInteger(int value){
            this.num = value;
        }
                 // @return true if this NestedInteger holds a single integer, rather than a nested list.
                 public  boolean isInteger(){
                     return this.list==null;
                 }
    
                 // @return the single integer that this NestedInteger holds, if it holds a single integer
                 // Return null if this NestedInteger holds a nested list
                 public Integer getInteger(){
                     return num;
                 }

                    public void setInteger(int value){
                        this.num = value;
                    }
    
            // @return the nested list that this NestedInteger holds, if it holds a nested list
                 // Return null if this NestedInteger holds a single integer
                 public  List<NestedInteger> getList(){
                     return this.list;
                 }

                public void add(NestedInteger ni){
                    this.list.add(ni);
                }
}
