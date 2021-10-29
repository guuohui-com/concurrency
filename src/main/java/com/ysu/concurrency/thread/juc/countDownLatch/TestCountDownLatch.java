package com.ysu.concurrency.thread.juc.countDownLatch;

import java.util.concurrent.CountDownLatch;

/**
 * @功能职责:
 * @描述：
 * @作者: 郭辉
 * @创建时间: 2020-12-02
 * @copyright Copyright (c) 2020 中国软件与技术服务股份有限公司
 * @company 中国软件与技术服务股份有限公司
 */
public class TestCountDownLatch {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        countDownLatch.countDown();
        for (int i = 0; i <  6; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"Go Out");
                countDownLatch.countDown();
            }).start();
        }
        countDownLatch.await();//等待计数器归零再向下执行
        System.out.println("shut down");
    }
}
