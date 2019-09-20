package com.leishb.leetcode.bfs;

import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by me on 2019/9/19.
 */
public class _1138_Alphabet_Board_Path {


    String[] bords = new String[]{"abcde", "fghij", "klmno", "pqrst", "uvwxy", "z"};

    public String alphabetBoardPath(String target) {
        StringBuffer sb = new StringBuffer();
        for (int i=0;i<target.length();i++){
            if (i==0){
                sb.append(bfs(0,0,target.charAt(i))).append("!");
            }else {
                int[] p = getPosition(target.charAt(i-1));
                sb.append(bfs(p[0],p[1],target.charAt(i))).append("!");
            }
        }
        return sb.toString();
    }


    private int[] getPosition(char c){
        for (int i=0;i<bords.length;i++){
            for (int j=0;j<bords[i].length();j++){
                if (bords[i].charAt(j)==c){
                    return new int[]{i,j};
                }
            }
        }
        return new int[2];
    }


    private String bfs(int i, int j, char t){
        boolean[][] visited = new boolean[6][5];
        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(i, j , ""));
        visited[i][j] = true;
        while (!queue.isEmpty()){
            Pair cur = queue.poll();
            if (bords[cur.i].charAt(cur.j)==t){
                return cur.path;
            }
            if (isValid(cur.i+1, cur.j) && !visited[cur.i+1][cur.j]){//D
                visited[cur.i+1][cur.j] = true;
                queue.offer(new Pair(cur.i+1, cur.j, cur.path+"D"));
            }
            if (isValid(cur.i, cur.j+1) && !visited[cur.i][cur.j+1]){//R
                visited[cur.i][cur.j+1] = true;
                queue.offer(new Pair(cur.i, cur.j+1, cur.path+"R"));
            }
            if (isValid(cur.i-1, cur.j) && !visited[cur.i-1][cur.j]){//U
                visited[cur.i-1][cur.j] = true;
                queue.offer(new Pair(cur.i-1, cur.j, cur.path+"U"));
            }
            if (isValid(cur.i, cur.j-1) && !visited[cur.i][cur.j-1]){//L
                visited[cur.i][cur.j-1] = true;
                queue.offer(new Pair(cur.i, cur.j-1, cur.path+"L"));
            }
        }
        return "";
    }


    private boolean isValid(int i, int j){
        if (i==5 ){
            return j==0;
        }
        return i>=0 && i<5 && j>=0 && j<5;
    }


    class Pair{
        int i;
        int j;
        String path;

        Pair(int i, int j, String path){
            this.i = i;
            this.j = j;
            this.path = path;
        }
    }


    public String alphabetBoardPath2(String target) {
        StringBuffer sb = new StringBuffer();
        int previ = 0, prevj = 0;
        for (char c : target.toCharArray()){
            int curi = (c-'a')/5;
            int curj = (c-'a')%5;
            //LU befor RD
            while (prevj > curj){
                sb.append("L");
                prevj--;
            }
            while (previ > curi){
                sb.append("U");
                previ--;
            }
            while (prevj < curj){
                sb.append("R");
                prevj++;
            }
            while (previ < curi){
                sb.append("D");
                previ++;
            }
            sb.append("!");
            previ = curi;
            prevj = curj;
        }
        return sb.toString();
    }


    @Test
    public void test(){
        Assert.assertTrue(alphabetBoardPath2("leet").equals("DDR!RRRUU!!DDD!"));
    }
}
