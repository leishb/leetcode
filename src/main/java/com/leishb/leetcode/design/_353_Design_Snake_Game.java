package com.leishb.leetcode.design;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by me on 2019/10/14.
 */
public class _353_Design_Snake_Game {


    Queue<int[]> queue = new LinkedList<>();
    int width = 0;
    int height = 0;
    Queue<int[]> foods = new LinkedList<>();
    boolean[][] body ;
    int[] head;
    int score = 0;



    /** Initialize your data structure here.
     @param width - screen width
     @param height - screen height
     @param food - A list of food positions
     E.g food = [[1,1], [1,0]] means the first food is positioned at [1,1], the second is at [1,0]. */
    public _353_Design_Snake_Game(int width, int height, int[][] food) {
        this.width =width;
        this.height = height;
        body = new boolean[height][width];
        head = new int[]{0, 0};
        body[0][0] = true;
        queue.offer(new int[]{0, 0});
        for (int[] f : food){
            foods.offer(f);
        }
    }

    /** Moves the snake.
     @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down
     @return The game's score after the move. Return -1 if game over.
     Game over when snake crosses the screen boundary or bites its body. */
    public int move(String direction) {
        int ni = head[0];
        int nj = head[1];
        switch (direction){
            case "U":
                ni -=1;
                break;
            case "D":
                ni +=1;
                break;
            case "L":
                nj -=1;
                break;
            case "R":
                nj +=1;
                break;
        }
        body[queue.peek()[0]][queue.peek()[1]] = false;
        if (ni<0||nj<0||ni>=height||nj>=width || body[ni][nj] ){
            return -1;
        }
        body[ni][nj] = true;
        queue.offer(new int[]{ni, nj});
        head = new int[]{ni, nj};
        if (!foods.isEmpty() && foods.peek()[0] == ni && foods.peek()[1] == nj) {
            score++;
            foods.poll();
            body[queue.peek()[0]][queue.peek()[1]] = true;
        }else {
            queue.poll();
        }
        return score;
    }




    public static void main(String[] args){
        _353_Design_Snake_Game game = new _353_Design_Snake_Game(3,2, new int[][]{{1,2},{0,1}});
        game.move("R");
        game.move("D");
        game.move("R");

        game.move("U");
        game.move("L");
        game.move("U");

    }
}
