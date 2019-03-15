package com.leishb.leetcode.bit;

/**
 * Created by me on 2019/3/13.
 */
public class BitMap {

    private char[] chars;

    private static int CHAR_BITS = 16;

    public BitMap(int nbits){
        chars = new char[nbits/CHAR_BITS+1];
    }

    public void set(int bit){
        int index = bit/CHAR_BITS;
        chars[index] |= 1 << (bit%CHAR_BITS);
    }


    public boolean get(int bit){
        int index = bit/CHAR_BITS;
        return (chars[index] & (1<< (bit%CHAR_BITS))) != 0;
    }
}
