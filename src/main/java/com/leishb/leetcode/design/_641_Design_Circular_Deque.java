package com.leishb.leetcode.design;

/**
 * Created by me on 2019/8/2.
 */
public class _641_Design_Circular_Deque {


    int capacity = 0;
    int head = 0;
    int tail = 0;
    int size = 0;

    int[] arr;

    /** Initialize your data structure here. Set the capacity of the deque to be k. */
    public _641_Design_Circular_Deque(int k) {
        this.arr = new int[k];
        this.capacity = k;
        this.tail = k-1;
    }

    /** Adds an item at the front of Deque. Return true if the operation is successful. */
    public boolean insertFront(int value) {
        if (isFull()){
            return false;
        }
        arr[head] = value;
        head = (head+1)% capacity;
        size++;
        return true;
    }

    /** Adds an item at the rear of Deque. Return true if the operation is successful. */
    public boolean insertLast(int value) {
        if (isFull()){
            return false;
        }
        arr[tail] = value;
        if (tail==0){
            tail = capacity -1;
        }else {
            tail-=1;
        }
        size++;
        return true;
    }

    /** Deletes an item from the front of Deque. Return true if the operation is successful. */
    public boolean deleteFront() {
        if (isEmpty()){
            return false;
        }
        if (head==0){
            head = capacity -1;
        }else {
            head-=1;
        }
        size--;
        return true;
    }

    /** Deletes an item from the rear of Deque. Return true if the operation is successful. */
    public boolean deleteLast() {
        if (isEmpty()){
            return false;
        }
        tail = (tail+1)% capacity;
        size--;
        return true;
    }

    /** Get the front item from the deque. */
    public int getFront() {
        if (isEmpty()){
            return -1;
        }
        return arr[head==0? capacity -1:(head-1)];
    }

    /** Get the last item from the deque. */
    public int getRear() {
        if (isEmpty()){
            return -1;
        }
        return arr[(tail+1)% capacity];
    }

    /** Checks whether the circular deque is empty or not. */
    public boolean isEmpty() {
        return size==0;
    }

    /** Checks whether the circular deque is full or not. */
    public boolean isFull() {
        return size==capacity;
    }


    public static void main(String[] args){
        _641_Design_Circular_Deque deque = new _641_Design_Circular_Deque(4);
        deque.insertFront(9);
        deque.deleteLast();

        System.out.println(deque.getRear());
        System.out.println(deque.getFront());
        System.out.println(deque.getFront());

    }
}
