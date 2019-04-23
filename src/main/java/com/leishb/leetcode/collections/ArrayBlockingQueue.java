package com.leishb.leetcode.collections;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by me on 2019/4/9.
 */
public class ArrayBlockingQueue<E> {

    private int capacity;

    private Object[] elements;

    private int putIndex;

    private int takeIndex;

    private int count;

    private ReentrantLock lock;

    private Condition notFull;

    private Condition notEmpty;

    public ArrayBlockingQueue(int capacity){
        this(capacity, false);
    }

    public ArrayBlockingQueue(int capacity, boolean fair){
        this.capacity = capacity;
        elements = new Object[capacity];
        lock = new ReentrantLock(fair);
        notEmpty = lock.newCondition();
        notFull = lock.newCondition();
    }

    public void put(E e) throws InterruptedException {
        checkNotNull(e);
        final ReentrantLock lock = this.lock;
        lock.lockInterruptibly();
        try{
            while (count == this.capacity){
                notFull.await();
            }
            enqueue(e);
        }finally {
            lock.unlock();
        }
    }

    public E take() throws InterruptedException {
        final ReentrantLock lock = this.lock;
        lock.lockInterruptibly();
        try{
            while (count==0){
                notEmpty.await();
            }
            return dequeue();
        }finally {
            lock.unlock();
        }
    }

    private void enqueue(E e){
        elements[putIndex] = e;
        if (++putIndex==this.capacity){
            putIndex=0;
        }
        count++;
        notEmpty.signal();
    }

    private E dequeue(){
        final Object[] items = this.elements;
        E e = (E) items[takeIndex];
        items[takeIndex]=null;
        if (++takeIndex==this.capacity){
            takeIndex=0;
        }
        count--;
        notFull.signal();
        return e;
    }

    public boolean offer(E e, long timeout, TimeUnit unit)
            throws InterruptedException {
        checkNotNull(e);
        long nanos = unit.toNanos(timeout);
        final ReentrantLock lock = this.lock;
        lock.lockInterruptibly();
        try {
            while (count==elements.length){
                if (nanos<=0){
                    return false;
                }
                nanos = notFull.awaitNanos(nanos);
            }
            enqueue(e);
            return true;
        }finally {
            lock.unlock();
        }
    }

    public E poll(long timeout, TimeUnit unit) throws InterruptedException {
        long nanos = unit.toNanos(timeout);
        final ReentrantLock lock = this.lock;
        lock.lockInterruptibly();
        try {
            while (count==0){
                if (nanos<=0){
                    return null;
                }
                nanos = notEmpty.awaitNanos(nanos);
            }
            return dequeue();
        }finally {
            lock.unlock();
        }
    }

    private static void checkNotNull(Object v) {
        if (v == null)
            throw new NullPointerException();
    }
}
