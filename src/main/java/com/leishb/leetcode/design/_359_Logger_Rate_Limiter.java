package com.leishb.leetcode.design;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by me on 2020/1/3.
 */
public class _359_Logger_Rate_Limiter {

    class Logger {


        Map<String, Integer> map;

        /** Initialize your data structure here. */
        public Logger() {
            map = new HashMap<>();
        }

        /** Returns true if the message should be printed in the given timestamp, otherwise returns false.
         If this method returns false, the message will not be printed.
         The timestamp is in seconds granularity. */
        public boolean shouldPrintMessage(int timestamp, String message) {
            boolean print = timestamp - map.getOrDefault(message, timestamp-10) >=10;
            if (print) map.put(message, timestamp);
            return print;
        }
    }
}
