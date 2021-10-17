package com.ysu.concurrency.thread;

/**
 * @功能职责: 多线程操作同一个对象
 * @描述： 多线程出现的线程不安全
 * @作者: 郭辉
 * @创建时间: 2020-12-02
 * @copyright Copyright (c) 2020 中国软件与技术服务股份有限公司
 * @company 中国软件与技术服务股份有限公司
 */
public class AllThreadOperateOneObj implements Runnable{
    private int ticketNums = 10;

    @Override
    public void run() {
        while(true){
            if (ticketNums < 0){
                break;
            }
            System.out.println(Thread.currentThread().getName()+"拿到第"+ticketNums--+"张火车票");
        }
    }

    public static void main(String[] args) {
        AllThreadOperateOneObj ticket = new AllThreadOperateOneObj();
        new Thread(ticket,"thread1").start();
        new Thread(ticket,"thread2").start();
        new Thread(ticket,"thread3").start();
        new Thread(ticket,"thread4").start();
    }
}
