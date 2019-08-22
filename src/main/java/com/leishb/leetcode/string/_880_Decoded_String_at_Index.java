package com.leishb.leetcode.string;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by me on 2019/8/22.
 */
public class _880_Decoded_String_at_Index {


    /**
     * Acceped
     * @param S
     * @param K
     * @return
     */
    public String decodeAtIndex(String S, int K) {
        long prevLen =0;
        char lastChar = '0';
        for (int i=0;i<S.length();i++){
            char c = S.charAt(i);
            if (c>='a' && c<='z'){
                prevLen+=1;
                lastChar = c;
                if (K==prevLen){
                    return c+"";
                }
            }else {
                long curLen = (c-'0') * prevLen;
                if (K == curLen){
                    return lastChar+"";
                }else if (K < curLen){
                    if (K%prevLen==0)return lastChar+"";
                    return decodeAtIndex(S, (int) (K%prevLen));
                }
                prevLen = curLen;
            }
        }
        return "";
    }



    /**
     * Acceped
     * @param S
     * @param K
     * @return
     */
    public String decodeAtIndex2(String S, int K) {
        long N = 0L;
        int i;
        for (i=0; N < K ; i++){
            char c = S.charAt(i);
            if (c>='0' && c<='9'){
                N = N * (c-'0');
            }else {
                N +=1;
            }
        }
        i--;
        while (true){
            if (S.charAt(i)>='0' && S.charAt(i)<='9'){
                N /=(S.charAt(i)-'0');
                K %=N;
            }else if (K%N==0) return ""+S.charAt(i);
            else N--;
            i--;
        }
    }


    @Test
    public void test(){
        Assert.assertEquals(decodeAtIndex2("leet2code3", 10), "o");
        Assert.assertEquals(decodeAtIndex("leet2code3", 6), "e");
        Assert.assertEquals(decodeAtIndex("leet2code3", 8), "t");
        Assert.assertEquals(decodeAtIndex2("ha22", 5), "h");
        Assert.assertEquals(decodeAtIndex("a2345678999999999999999", 1), "a");
        Assert.assertEquals(decodeAtIndex("a2b3c4d5e6f7g8h9", 40), "c");
        Assert.assertEquals(decodeAtIndex("vk6u5xhq9v", 554), "k");
    }
}
