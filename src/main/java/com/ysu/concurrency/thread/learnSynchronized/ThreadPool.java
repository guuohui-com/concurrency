package com.ysu.concurrency.thread.learnSynchronized;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @功能职责:
 * @描述：
 * @作者: 郭辉
 * @创建时间: 2020-12-02
 * @copyright Copyright (c) 2020 中国软件与技术服务股份有限公司
 * @company 中国软件与技术服务股份有限公司
 */
public class ThreadPool {
    public static void main(String[] args) {
        //创建服务，创建线程池
        ExecutorService service = Executors.newFixedThreadPool(10);
        //执行任务
        service.execute(new MyThread());
        service.execute(new MyThread());
        service.execute(new MyThread());
        service.execute(new MyThread());
        service.execute(new MyThread());
        //关闭连接
        service.shutdown();
    }
}

class MyThread implements Runnable{

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName()+i);
        }
    }
}
