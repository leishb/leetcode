package com.leishb.leetcode.bfs;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * Created by me on 2019/8/8.
 */
public class _752_Open_the_Lock {


    /**
     * Accepted
     * @param deadends
     * @param target
     * @return
     */
    public int openLock(String[] deadends, String target) {
        Set<String> set = new HashSet<>();
        for (String deadend : deadends){
            set.add(deadend);
        }
        String start = "0000";
        if (set.contains(start)){
            return -1;
        }
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.offer(start);
        visited.add(start);
        int steps = 0;
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i=0;i<size;i++){
                String state = queue.poll();
                if (state.equals(target)){
                    return steps;
                }
                List<String> nexts = next(state);
                for (String s : nexts){
                    if (!set.contains(s) && !visited.contains(s)){
                        queue.offer(s);
                        visited.add(s);
                    }
                }
            }
            steps+=1;
        }
        return -1;
    }


    private List<String> next(String state){
        char[] cs = state.toCharArray();
        List<String> list = new ArrayList<>();
        for (int i=0;i<cs.length;i++){
            char c = cs[i];
            cs[i] = c=='9'?'0':(char)(c+1);
            list.add(new String(cs));
            cs[i] = c=='0'?'9':(char) (c-1);
            list.add(new String(cs));
            cs[i] = c;
        }
        return list;
    }



    @Test
    public void test(){
        System.out.println(next("0009"));
        Assert.assertTrue(openLock(new String[]{"8887","8889","8878","8898","8788","8988","7888","9888"}, "8888")==-1);
        Assert.assertTrue(openLock(new String[]{"8888"}, "0009")==1);
    }
}
