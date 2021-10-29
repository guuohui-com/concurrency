package com.ysu.concurrency.thread.juc.JUC;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @功能职责: 可重复锁学习
 * @描述：
 * @作者: 郭辉
 * @创建时间: 2020-12-02
 * @copyright Copyright (c) 2020 中国软件与技术服务股份有限公司
 * @company 中国软件与技术服务股份有限公司
 */
public class TestReentrantLock {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        new Thread(()-> { for (int i = 0; i < 40; i++) ticket.sale();}).start();
        new Thread(()-> { for (int i = 0; i < 40; i++) ticket.sale();}).start();
        new Thread(()-> { for (int i = 0; i < 40; i++) ticket.sale();}).start();
    }
}
class Ticket{

    private int tickets = 30;
    Lock lock = new ReentrantLock();
    public synchronized void sale(){
        lock.lock();
        try {
            if (tickets > 0) System.out.println(Thread.currentThread().getName()+"卖出了"+tickets--+"票，剩余"+tickets);
        }finally {
            lock.unlock();
        }
    }
}