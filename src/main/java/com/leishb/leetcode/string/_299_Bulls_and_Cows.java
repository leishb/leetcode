package com.leishb.leetcode.string;

/**
 * Created by me on 2020/1/6.
 */
public class _299_Bulls_and_Cows {



    public String getHint(String secret, String guess) {
        int bulls = 0;
        int[] secretArr = new int[10];
        int[] guessArr = new int[10];
        for (int i=0;i<secret.length();i++){
            if (secret.charAt(i) == guess.charAt(i)){
                bulls++;
            }else {
                secretArr[secret.charAt(i)-'0']++;
                guessArr[guess.charAt(i)-'0']++;
            }
        }
        int cows = 0;
        for (int i=0;i<10;i++){
            cows += Math.min(secretArr[i], guessArr[i]);
        }
        return bulls + "A" + cows + "B";
    }
}
