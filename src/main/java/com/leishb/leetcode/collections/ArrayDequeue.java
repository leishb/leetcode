package com.leishb.leetcode.collections;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by me on 2019/4/9.
 */
public class ArrayDequeue<E> {

    private Object[] items;

    public static final int INITIAL_CAPACITY = 16;

    private int head;

    private int tail;

    public ArrayDequeue(){
        items = new Object[INITIAL_CAPACITY];
    }

    public void addFirst(E e){
        head = (head-1)&(items.length-1);
        items[head] = e;
        if (head==tail){
            doubleCapacity();
        }
    }

    public void addLast(E e){
        items[tail] = e;
        tail = tail+1;
        if ((tail & (items.length-1)) == head){
            doubleCapacity();
        }
    }

    public E removeFirst(){
        int h = head;
        Object e = items[h];
        if (e==null){
            throw new NullPointerException();
        }
        items[h]=null;
        head = (h+1) & (items.length-1);
        return (E) e;
    }

    public E removeLast(){
        int t  = (tail-1) & (items.length-1);
        Object e = items[t];
        if (e==null){
            throw new NullPointerException();
        }
        items[t] = null;
        tail = t;
        return (E) e;
    }

    public Iterator iterator(){
        return new DeqIterator();
    }

    private void doubleCapacity(){
        int n = items.length;
        int newCapacity = n << 1;
        Object[] newItems = new Object[newCapacity];
        int p = n-head;
        System.arraycopy(items, head, newItems, 0, p);
        System.arraycopy(items, 0, newItems, p, head);
        items = newItems;
        head=0;
        tail=n;
    }

    private class DeqIterator implements Iterator<E> {

        private int cur = head;

        private int fence = tail;

        @Override
        public boolean hasNext() {
            return cur!=fence;
        }

        @Override
        public E next() {
            if (cur==fence){
                throw new NoSuchElementException();
            }
            int c = cur;
            E e = (E) items[c];
            cur = (c+1) & (items.length-1);
            return  e;
        }
    }
}
