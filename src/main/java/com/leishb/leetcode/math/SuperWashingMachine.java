package com.leishb.leetcode.math;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by me on 2017/10/14.
 */
public class SuperWashingMachine {

    @Test
    public void test(){
        Assert.assertTrue(findMinMOvies(new int[]{1,0,5})==3);
    }



    /**
     * Accepted
     *
     * 如果衣服总数不能被洗衣机总数整除，表明不能均分所有衣服，返回-1；如果能整除，拿avg表示最终每个洗衣机中衣服数。
     如果我们能够得到任意位置k上洗衣机最少需要的操作数，则遍历整个数组即可得到最终需要的最小操作数，因为所有位置互不相关，可以同时进行操作。
     ​对位置k上的洗衣机来说，如果左边k个洗衣机中（下标从0开始）原有衣服总数小于avg*k，表明左边k个洗衣机作为整体最终需要从右边洗衣机（包含位置k）中获取衣服，而获取衣服必定需要通过位置k的洗衣机，右边同理。这里拿lCnt表示位置k左边所有洗衣机最终向右边洗衣机（包含位置k）输送的衣服数，如果lCnt小于0，表示左边洗衣机最终需要从右边洗衣机中获取衣服，同理拿rCnt表示位置k右边所有洗衣机最终向左边洗衣机（包含位置k）中输送的衣服数。lCnt和rCnt在知道了avg之后很容易计算，这样通过判断lCnt和rCnt的正负即可得出位置k上洗衣机的最小操作数。
     如果lCnt>0 && rCnt>0
     ​表明位置k需要同时从两侧获取衣服，两侧可以同时进行，所以位置k上最小操作数为max(lCnt, rCnt)；
     如果lCnt<0 && rCnt<0
     表明位置k同时向两侧输出衣服，两侧不能同时进行，所以位置k上最小操作数为-lCnt-rCnt；
     其他情况
     表明位置k需要从一侧获取衣服，然后向另一侧输出衣服，两侧可以同时进行，所以位置k上最小操作数为max(abs(lCnt), abs(rCnt))。
     * @param machines
     * @return
     */
    public int findMinMOvies(int[] machines){
        int[] sum = new int[machines.length+1];
        for (int i=0;i<machines.length;i++){
            sum[i+1] = sum[i] + machines[i];
        }
        if (sum[machines.length]%machines.length != 0){
            return -1;
        }
        int avg = sum[machines.length]/machines.length;
        int moves = 0;
        for (int i=0;i<machines.length;i++){
            int lct = sum[i] - avg * i;//左边向右边输送
            int rct = sum[machines.length] - sum[i] - machines[i] - avg * (machines.length-i-1);//右边向左边输送
            if (lct >0 && rct >0){//left ->right && right ->left 不能同时向i输送
                moves = Math.max(moves, Math.max(lct, rct));
            }else if (lct <0 && rct <0){//i ->left && i-> right
                moves = Math.max(moves, -lct-rct);
            }else { //(left ->i && i->right) || (right->i&&i->left)
                moves = Math.max(moves, Math.max(Math.abs(lct), Math.abs(rct)));
            }
        }
        return moves;
    }
}
