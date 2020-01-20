package com.leishb.leetcode.design;

import java.util.*;

/**
 * Created by me on 2020/1/3.
 */
public class _359_Logger_Rate_Limiter_2 {


    class Logger {
        class Log {
            String message;
            int timestamp;

            Log(String message, int timestamp){
                this.message = message;
                this.timestamp = timestamp;
            }
        }

        Deque<Log> msgQ ;
        Set<String> set;


        /** Initialize your data structure here. */
        public Logger() {
            msgQ = new LinkedList<>();
            set = new HashSet<>();
        }

        /** Returns true if the message should be printed in the given timestamp, otherwise returns false.
         If this method returns false, the message will not be printed.
         The timestamp is in seconds granularity. */
        public boolean shouldPrintMessage(int timestamp, String message) {
            while (!msgQ.isEmpty() && timestamp - msgQ.getFirst().timestamp >= 10){
                Log log = msgQ.removeFirst();
                set.remove(log.message);
            }
            if (set.contains(message)) {
                return false;
            }
            msgQ.add(new Log(message, timestamp));
            set.add(message);
            return true;
        }
    }
}
