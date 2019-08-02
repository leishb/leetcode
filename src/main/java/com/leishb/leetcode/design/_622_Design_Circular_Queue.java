package com.leishb.leetcode.design;

/**
 * Created by me on 2019/8/2.
 */
public class _622_Design_Circular_Queue {


    int capacity = 0;

    int size = 0;
    int head = 0;
    int tail = 0;

    int[] arr;
    /** Initialize your data structure here. Set the size of the queue to be k. */
    public _622_Design_Circular_Queue(int k) {
        this.capacity = k;
        this.arr = new int[capacity];
    }

    /** Insert an element into the circular queue. Return true if the operation is successful. */
    public boolean enQueue(int value) {
        if (isFull()){
            return false;
        }
        size++;
        arr[tail] = value;
        tail = (tail+1)%capacity;
        return true;
    }

    /** Delete an element from the circular queue. Return true if the operation is successful. */
    public boolean deQueue() {
        if (isEmpty()){
            return false;
        }
        size--;
        head = (head+1)%capacity;
        return true;
    }

    /** Get the front item from the queue. */
    public int Front() {
        if (isEmpty()){
            return -1;
        }
        return arr[head];
    }

    /** Get the last item from the queue. */
    public int Rear() {
        if (isEmpty()){
            return -1;
        }
        return arr[tail==0?capacity-1:tail-1];
    }

    /** Checks whether the circular queue is empty or not. */
    public boolean isEmpty() {
        return size==0;
    }

    /** Checks whether the circular queue is full or not. */
    public boolean isFull() {
        return size==capacity;
    }
}
