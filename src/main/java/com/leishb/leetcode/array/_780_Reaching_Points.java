package com.leishb.leetcode.array;

/**
 * Created by me on 2019/12/12.
 */
public class _780_Reaching_Points {


    /**
     * Accepted
     * @param sx
     * @param sy
     * @param tx
     * @param ty
     * @return
     */
    public boolean reachingPoints(int sx, int sy, int tx, int ty) {
        while (tx>=sx && ty>=sy){
            if (sx==tx && sy==ty) return true;
            if (tx > ty){
                if (ty > sy) tx %=ty;
                else return (tx-sx)%ty==0;
            }else {
                if (tx > sx) ty %=tx;
                else return (ty-sy)%tx==0;
            }
        }
        return false;
    }




    private boolean dfs(int sx, int sy, int tx, int ty){
        if (sx==tx && sy==ty){
            return true;
        }
        if (sx > tx || sy > ty){
            return false;
        }
        if (dfs(sx+sy, sy, tx, ty)){
            return true;
        }
        if (dfs(sx, sy+sx, tx, ty)){
            return true;
        }
        return false;
    }
}
