package com.leishb.leetcode.string;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by me on 2019/10/24.
 */
public class _843_Guess_the_Word {


    interface Master {
        public int guess(String word);
    }


    public void findSecretWord(String[] wordlist, Master master) {
        Arrays.sort(wordlist);
        Map<String, Integer> badWords = new HashMap<>();
        for (String word : wordlist){
            if (validateWord(word, badWords)){
                int x = master.guess(word);
                if (x==6){
                    return;
                }
                badWords.put(word, x);
            }
        }
    }


    private boolean validateWord(String w, Map<String, Integer> badWords){
        for (String key : badWords.keySet()){
            int similar = similar(w, key);
            int score = badWords.get(key);
            if (similar < score || (score==0 && similar>0)){
                return false;
            }
        }
        return true;
    }


    private int similar(String w1, String w2){
        int count = 0;
        for (int i=0;i<w1.length();i++)if (w1.charAt(i)==w2.charAt(i)) count++;
        return count;
    }
}
