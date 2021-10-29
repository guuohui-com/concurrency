package com.ysu.concurrency.thread.juc.countDownLatch;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @功能职责:
 * @描述：
 * @作者: 郭辉
 * @创建时间: 2020-12-02
 * @copyright Copyright (c) 2020 中国软件与技术服务股份有限公司
 * @company 中国软件与技术服务股份有限公司
 */
public class TestCyclicBarrier {
    public static void main(String[] args) {
        //集齐七颗龙珠 召唤神龙

        CyclicBarrier cyclicBarrier = new CyclicBarrier(7,()-> System.out.println("召唤神龙"));
        for (int i = 1; i <= 70; i++) {
            final int tem = i;
            new Thread(()-> {
                System.out.println(Thread.currentThread().getName()+"收集了第"+tem+"颗龙珠");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
