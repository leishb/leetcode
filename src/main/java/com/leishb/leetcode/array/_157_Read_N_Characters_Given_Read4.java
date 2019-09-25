package com.leishb.leetcode.array;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by me on 2019/9/23.
 */
public class _157_Read_N_Characters_Given_Read4 {


    public int read4(char[] buf) {

        return 0;
    }


    public int read(char[] buf, int n) {
        boolean eof = false;
        int total = 0;
        while (total < n && !eof){
            char[] buffer = new char[4];
            int t = read4(buffer);
            eof = t<4;
            int count = Math.min(t, n-total);
            for (int i=0;i<count ;i++){
                buf[total++] = buffer[i];
            }
        }
        return total;
    }


    List<Character> list = new ArrayList<>();

    int p = 0;


    public int read2(char[] buf, int n) {
        boolean endOfFile = false;
        while (list.size()-p<n && !endOfFile){
            char[] buffer = new char[4];
            int t = read4(buffer);
            if (t<4) endOfFile = true;
            for (int i=0;i<t;i++){
                list.add(buffer[i]);
            }
        }
        int i=0;
        int ans = 0;
        int end = Math.min(list.size(), n+p);
        while (p<end){
            buf[i++] = list.get(p);
            p++;
            ans++;
        }
        return ans;
    }

    char[] prevBuf = new char[4];
    int prevSize = 0;
    int prevIndex = 0;

    public int read3(char[] buf, int n) {
        int count = 0;
        while (count < n){
            if (prevIndex < prevSize){
                buf[count++] = prevBuf[prevIndex++];
            }else {
                prevSize = read4(prevBuf);
                prevIndex = 0;
                if (prevSize==0)break;
            }
        }
        return count;
    }
}
