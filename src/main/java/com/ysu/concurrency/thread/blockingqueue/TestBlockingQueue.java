package com.ysu.concurrency.thread.blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @功能职责:
 * @描述：
 * @作者: 郭辉
 * @创建时间: 2020-12-02
 * @copyright Copyright (c) 2020 中国软件与技术服务股份有限公司
 * @company 中国软件与技术服务股份有限公司
 */
public class TestBlockingQueue {
    public static void main(String[] args) throws InterruptedException {
        test4();
    }

    /**
     * 抛出异常
     * 队列满了 再 添加报异常 add()
     * 队列空了 再 删除抛异常 remove()
     * */
    public static void test1(){
        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue(2);
        System.out.println(blockingQueue.add(1));
        System.out.println(blockingQueue.add(2));

        System.out.println(blockingQueue.element()); // 查看队首元素
//        System.out.println(blockingQueue.add(3));
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
    }

    /**
     * 不抛出异常，有返回结果
     * 队列满了 再 添加 返回false offer()
     * 队列空了 再 删除 返回null poll()
     * */
    public static void test2(){
        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue(2);
        System.out.println(blockingQueue.offer(1));
        System.out.println(blockingQueue.offer(2));
        System.out.println(blockingQueue.offer(3));

        System.out.println(blockingQueue.peek());//检测队首元素

        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
    }

    /**
     * 操作失败时阻塞等待
     * 队列满了 再 添加  线程阻塞等待
     * 队列空了 再 删除  线程阻塞等待
     * */
    public static void test3() throws InterruptedException {
        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue(2);
        blockingQueue.put(1);
        blockingQueue.put(2);
        blockingQueue.put(3);
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
    }


    /**
     * 操作失败时阻塞等待，超时退出
     * 队列满了 再 添加  线程阻塞等待
     * 队列空了 再 删除  线程阻塞等待
     * */
    public static void test4() throws InterruptedException {
        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue(2);
        blockingQueue.offer(1,10, TimeUnit.SECONDS);
        blockingQueue.offer(2,10, TimeUnit.SECONDS);
        blockingQueue.offer(3,10, TimeUnit.SECONDS);
        System.out.println(blockingQueue.poll(10, TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(10, TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(10, TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(10, TimeUnit.SECONDS));
    }
}
