package com.leishb.leetcode.array;

import org.junit.Test;

/**
 * Created by me on 2019/12/2.
 */
public class _1274_Number_of_Ships_in_a_Rectangle {


   interface   Sea {
         public boolean hasShips(int[] topRight, int[] bottomLeft);
    }


    /**
     * Accepted
     * @param sea
     * @param topRight
     * @param bottomLeft
     * @return
     */
    public int countShips(Sea sea, int[] topRight, int[] bottomLeft) {
        if (topRight[0] < bottomLeft[0] || topRight[1] < bottomLeft[1]){
            return 0;
        }
        if (topRight[0] == bottomLeft[0] && topRight[1] == bottomLeft[1]){
            return sea.hasShips(new int[]{topRight[0], topRight[1]}, new int[]{topRight[0], topRight[1]}) ? 1 : 0;
        }
        int midx = (topRight[0] + bottomLeft[0])/2;
        int midy = (topRight[1] + bottomLeft[1])/2;
        int count = 0;
        if (sea.hasShips(new int[]{midx, midy}, bottomLeft)){
            count += countShips(sea,  new int[]{midx, midy}, bottomLeft);
        }
        if (sea.hasShips(new int[]{topRight[0], midy}, new int[]{midx+1, bottomLeft[1]})){
            count += countShips(sea,  new int[]{topRight[0], midy}, new int[]{midx+1, bottomLeft[1]});
        }
        if (sea.hasShips( new int[]{midx, topRight[1]}, new int[]{bottomLeft[0], midy+1})){
            count += countShips(sea, new int[]{midx, topRight[1]},  new int[]{bottomLeft[0], midy+1});
        }
        if (sea.hasShips(topRight, new int[]{midx+1, midy+1})){
            count += countShips(sea, topRight,  new int[]{midx+1, midy+1});
        }
        return count;
    }



    @Test
    public void test(){
        Sea sea = (topRight, bottomLeft) -> true;
        countShips(sea , new int[]{4,4}, new int[]{0, 0});
    }
}
