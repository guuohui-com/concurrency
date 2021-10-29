package com.ysu.concurrency.thread.juc.JUC;

/**
 * @功能职责: 线程之间通信问题：生产者消费者： 等待唤醒，通知唤醒
 * 线程交替执行，A B 操作同一个变量， num = 0
 * A num+1
 * B num-1
 * @描述：
 * @作者: 郭辉
 * @创建时间: 2020-12-02
 * @copyright Copyright (c) 2020 中国软件与技术服务股份有限公司
 * @company 中国软件与技术服务股份有限公司
 */
public class LockPC {
    public static void main(String[] args) {
       Source source = new Source();
       new Thread(()->{
           for (int i = 0; i < 10; i++) {
               try {
                       source.increment();
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
       },"Productor1").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    source.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"Productor2").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    source.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"Consumer1").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    source.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"Consumer2").start();
    }
}
/**
 * 资源类
 * */
class Source{
    private int num = 0;

    /**
     *
     * */
    public synchronized void increment() throws InterruptedException {
        while (num != 0){
            //等待操作
            this.wait();
        }
        num++;
        System.out.println(Thread.currentThread().getName()+"=>"+num);
        //唤醒消费者
        this.notifyAll();
    }
    public synchronized void decrement() throws InterruptedException {
        while (num == 0){
            // 等待操作
            this.wait();
        }
        num--;
        System.out.println(Thread.currentThread().getName()+"=>"+num);
        //唤醒生产者
        this.notifyAll();
    }

}