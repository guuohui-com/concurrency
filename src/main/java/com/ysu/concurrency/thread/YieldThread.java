package com.ysu.concurrency.thread;

/**
 * @功能职责: 线程礼让
 * @描述： 线程礼让不一定成功，看CPU怎么调度
 * @作者: 郭辉
 * @创建时间: 2020-12-02
 * @copyright Copyright (c) 2020 中国软件与技术服务股份有限公司
 * @company 中国软件与技术服务股份有限公司
 */
public class YieldThread {
    public static class Yield implements Runnable{

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+"======yield start======");
            Thread.yield();
            System.out.println(Thread.currentThread().getName()+"======yield end======");
        }
    }

    public static void main(String[] args) {
       new Thread(new Yield(),"thread1").start();
       new Thread(new Yield(),"thread2").start();
    }
}