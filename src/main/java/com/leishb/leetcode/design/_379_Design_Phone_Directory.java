package com.leishb.leetcode.design;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * Created by me on 2019/12/30.
 */
public class _379_Design_Phone_Directory {


    class PhoneDirectory {

        Set<Integer> used;
        int max ;
        LinkedList<Integer> released;
        /** Initialize your data structure here
         @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
        public PhoneDirectory(int maxNumbers) {
            used = new LinkedHashSet<>();
            released = new LinkedList<>();
            this.max = maxNumbers;
        }

        /** Provide a number which is not assigned to anyone.
         @return - Return an available number. Return -1 if none is available. */
        public int get() {
            if (used.size()==max) return -1;
            int num = released.isEmpty() ? used.size() : released.remove();
            used.add(num);
            return num;
        }

        /** Check if a number is available or not. */
        public boolean check(int number) {
            return !used.contains(number);
        }

        /** Recycle or release a number. */
        public void release(int number) {
            if (used.remove(number)){
                released.add(number);
            }
        }
    }

/**
 * Your PhoneDirectory object will be instantiated and called as such:
 * PhoneDirectory obj = new PhoneDirectory(maxNumbers);
 * int param_1 = obj.get();
 * boolean param_2 = obj.check(number);
 * obj.release(number);
 */
}
