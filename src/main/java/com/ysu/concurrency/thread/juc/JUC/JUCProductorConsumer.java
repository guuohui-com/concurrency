package com.ysu.concurrency.thread.juc.JUC;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @功能职责:
 * @描述：
 * @作者: 郭辉
 * @创建时间: 2020-12-02
 * @copyright Copyright (c) 2020 中国软件与技术服务股份有限公司
 * @company 中国软件与技术服务股份有限公司
 */
public class JUCProductorConsumer {
    public static void main(String[] args) {

        JucSource source = new JucSource();

        new Thread(()->{
            for (int i = 0; i < 20; i++) {
                try {
                    source.min();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"消费者1").start();

        new Thread(()->{
            for (int i = 0; i < 20; i++) {
                try {
                    source.add();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"生产者1").start();

        new Thread(()->{
            for (int i = 0; i < 20; i++) {
                try {
                    source.min();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"消费者2").start();

        new Thread(()->{
            for (int i = 0; i < 20; i++) {
                try {
                    source.add();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"生产者2").start();
    }
}

class JucSource{

    private int num = 0;

    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    public void add() throws InterruptedException {
        lock.lock();
        try {
            while (num != 0){
//                wait();
                condition.await();
            }
            num ++;
            System.out.println(Thread.currentThread().getName()+"生产了第"+num+"个产品");
//        notifyAll();
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public void min() throws InterruptedException {
        lock.lock();
        try {
            while (num == 0){
//                wait();
                condition.await();
            }
            num --;
            System.out.println(Thread.currentThread().getName()+"消费了第"+num+"个产品");
//        notifyAll();
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }
}

