package com.ysu.concurrency.thread.juc;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @功能职责: 测试lock锁
 * @描述：
 * @作者: 郭辉
 * @创建时间: 2020-12-02
 * @copyright Copyright (c) 2020 中国软件与技术服务股份有限公司
 * @company 中国软件与技术服务股份有限公司
 */
public class TestLock {
    public static void main(String[] args) {
        Lock2 lock2 = new Lock2();
        new Thread(lock2).start();
        new Thread(lock2).start();
        new Thread(lock2).start();
    }
}

class Lock2 implements Runnable{

    int tickets = 10;

    //定义lock锁
    private final ReentrantLock lock = new ReentrantLock();
    @Override
    public void run() {
        while (true){
            lock.lock();
            try {
                if (tickets > 0){
                    Thread.sleep(1000);
                    System.out.println(tickets--);
                }else {
                    break;
                }
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }
    }
}
